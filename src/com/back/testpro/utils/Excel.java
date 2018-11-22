package com.back.testpro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.back.testpro.utils.WriteExcelBean;

import jxl.write.WriteException;

public class Excel {

	// public List result = new ArrayList(); // 数据集合

	private String filename;

	private int begin_row_num = 0; // 打印内容的起始行

	protected WriteExcelBean web = null;

	

	/**
	 * 利用response下载数据流文件
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public boolean downExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
		response.setContentType(CONTENT_TYPE);
		String fileName = filename;
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		if ("doc".equals(extension)) {
			fileName = "stateReportDoc.doc";
		}
		fileName = URLEncoder.encode(fileName, "utf-8");
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			ServletOutputStream outputStream = response.getOutputStream();
			InputStream inputStream = null;
			inputStream = new FileInputStream(filename);
			int chunk = inputStream.available();
			byte[] buffer = new byte[chunk];
			int length = -1;
			// System.out.println(filename);
			if (inputStream == null) {
				System.out.println("输入流为空!!");
			} else {
				while ((length = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, length); // 读入流,保存在BYTe数组中
				}
			}
			inputStream.close();
			outputStream.flush();
			outputStream.close();
			return true;
		} catch (IOException ex1) {
			System.out.println("下载错误");

			try {
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head><title>Down</title></head>");
				out.println("<body bgcolor=\"#ffffff\">");
				out.println("<p> 下载文件错误!!</p>");
				out.println("</body></html>");
			} catch (Exception e) {
				// 不能重复抛出异常
			}
		}
		return false;
	}

}
