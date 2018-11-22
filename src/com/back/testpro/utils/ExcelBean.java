package com.back.testpro.utils;

import java.io.*;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.IOException;
import java.io.File;

/**
 * @author
 * <p>
 * Description: 读写Excel文件基础封装类。
 * </p>
 */
public abstract class ExcelBean {
   
	protected OutputStream os ;
	protected InputStream is;
	protected  jxl.write.WritableWorkbook wwb;
	protected  jxl.write.WritableSheet ws ;
	protected  jxl.Sheet sheet;
	protected jxl.Workbook  wb;
	protected File file;
	protected File desFile;
	protected int iColumns;
	protected int iRows;	
	
	/**
	 * 主要用于读写Excle文件
	 * @param filename 文件名或路径
	 * @param tag  tag=1 为Read Excel ;or is Write Excel
	 */
public ExcelBean(String filename,int tag){
		
	    try {
	    	if(tag == 1){
	    	       file = new File(filename);
	    	       is = new FileInputStream(file);
	    	}else {
	    		file = new File(filename);	    		
	    		os = new FileOutputStream(file);
	    	}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/**用于copy Excel
	 * @param soufilename 原文件名
	 * @param desfilename 目标文件名
	 */
	public ExcelBean(String soufilename,String desfilename){

		try {
			this.file = new File(soufilename);
			this.desFile = new File(desfilename);
			is = new FileInputStream(file);
			os = new FileOutputStream(desFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}
	
	public void createWorkbook() throws IOException{
		this.wwb = Workbook.createWorkbook(file);
	}
	/**
	 * 
	 * @param isheet 需要修改的sheet
	 * @throws IOException
	 * @throws BiffException
	 */
	public void copyWorkbook(int isheet) throws IOException, BiffException{
		wb = Workbook.getWorkbook(is);
		this.wwb = Workbook.createWorkbook(desFile,wb);
		ws = wwb.getSheet(isheet);
	}
	
	public void createSheet(String sheetName,int i){
		ws = wwb.createSheet(sheetName, i);
		ws.getSettings().setDefaultColumnWidth(3);//固定每个单元格宽度
		ws.getSettings().setDefaultRowHeight(20);//固定每个单元格高度
	}
	
	public void getWorkbook(){
		try {
			wb = Workbook.getWorkbook(is);
		} catch (BiffException e) {
			System.out.println("导入Excel文件类错误。");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("导入Excel文件错误。");
			e.printStackTrace();
		}
	}
	
	public void getSheet(int isheet){
		sheet = wb.getSheet(isheet);
	}
	
	public void write() throws IOException{
		wwb.write();
	}

	
	public void close() {
//		// 关闭Excel工作薄对象
		try {
			if (wwb != null)
				wwb.close();
			if(os != null)
				os.close();
			if(is != null)
				is.close();
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(wb != null)
				wb.close();
		}
	}

	public static void main(String[] args) throws Exception {

		jxl.Workbook rwb = null;
		try {
			//构建Workbook对象, 只读Workbook对象
			//直接从本地文件创建Workbook
			//从输入流创建Workbook
			InputStream is = new FileInputStream("e:\\基本统计表.xls");
			rwb = Workbook.getWorkbook(is);

			//Sheet(术语：工作表)就是Excel表格左下角的Sheet1,Sheet2,Sheet3但在程序中
			//Sheet的下标是从0开始
			//获取第一张Sheet表
			Sheet rs = rwb.getSheet(0);
			//获取Sheet表中所包含的总列数
			int rsColumns = rs.getColumns();
			//获取Sheet表中所包含的总行数
			int rsRows = rs.getRows();
			System.out.println("col=" + rsColumns + " row=" + rsRows);
			//获取指定单元格的对象引用
			for (int i = 0; i < rsRows; i++) {
				for (int j = 0; j < rsColumns; j++) {
					Cell cell = rs.getCell(j, i);
					System.out.print(cell.getContents() + " ");
				}
				System.out.println();
			}
			Cell cell = rs.getCell(7, 0);
			System.out.println(cell.getContents());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//操作完成时，关闭对象，释放占用的内存空间
			rwb.close();
		}
	}
}