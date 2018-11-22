package com.back.testpro.JfreeCharIntface;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.servlet.ServletUtilities;

public class barChart extends ServletUtilities {
	// 为生成的图片创建文件夹
	protected static void createTempDir(String url) {
		String tempDirName = url; // 配置存储路径
		if (tempDirName == null) {
			throw new RuntimeException("Temporary directory system property " + "(java.io.tmpdir) is null.");
		}

		// create the temporary directory if it doesn't exist
		File tempDir = new File(tempDirName);
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
	}

	// 覆盖父类的方法
	public static String saveChartAsPNG(JFreeChart chart, int width, int height, ChartRenderingInfo info,
			HttpSession session, String url) throws IOException {

		if (chart == null) {

			throw new IllegalArgumentException("Null 'chart' argument.");

		}
		createTempDir(url);

		String prefix = ServletUtilities.getTempFilePrefix();
		
		if (session == null) {

			prefix = ServletUtilities.getTempOneTimeFilePrefix();

		}
		File tempFile = File.createTempFile(prefix, ".png", new File(url));
		
		info = new ChartRenderingInfo(new StandardEntityCollection());
		
		ChartUtilities.saveChartAsPNG(tempFile, chart, width, height, info);

		if (session != null) {

			ServletUtilities.registerChartForDeletion(tempFile, session);

		}

		return tempFile.getName();
	}

	public static String newCreatChart(JFreeChart chart, int i, int j, Object object, HttpSession session, String url) {

		String name = "";
		
		chart.setBackgroundImageAlpha(0.0f);

		try {

			name = saveChartAsPNG(chart, i, j, null, session, url);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return name;
	}
}