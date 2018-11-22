package com.back.testpro.testNew;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import com.back.testpro.model.TxDataCount;

/**
 * 雷达图
 * 
 */
public class CalibrationSpiderWebPlotDemo extends ApplicationFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * 创建
	 * 
	 * 
	 */
	public CalibrationSpiderWebPlotDemo(String s) {
		super(s);
		// JPanel jpanel = createDemoPanel();
		// jpanel.setPreferredSize(new Dimension(600, 600));
		// setContentPane(jpanel);
	}

	/**
	 * 创建面板
	 * 
	 * 
	 */
	// public static JPanel createDemoPanel() {
	// JFreeChart jfreechart = createChart(createDataset());
	// return new ChartPanel(jfreechart);
	// }

	/**
	 * 构造数据集
	 * 
	 * 
	 */
	public static CategoryDataset createDataset(String num1, String num2, String num3, String num4, String num5,
			String num6, String num7, String num8, int i) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String group1 = "个人得分率";
		String group2 = "地区平均水平"; 

		double Dnum1 = Double.valueOf(num1);
		int inum1 = (int) Dnum1;
		double Dnum2 = Double.valueOf(num2);
		int inum2 = (int) Dnum2;
		double Dnum3 = Double.valueOf(num3);
		int inum3 = (int) Dnum3;
		double Dnum4 = Double.valueOf(num4);
		int inum4 = (int) Dnum4;
		double Dnum5 = Double.valueOf(num5);
		int inum5 = (int) Dnum5;
		double Dnum6 = Double.valueOf(num6);
		int inum6 = (int) Dnum6;

		if (i == 3) {

			dataset.addValue(inum1, group1, "自我认识");
			dataset.addValue(inum2, group1, "人际理解");
			dataset.addValue(inum3, group1, "逻辑分析");

			dataset.addValue(inum4, group2, "自我认识");
			dataset.addValue(inum5, group2, "人际理解");
			dataset.addValue(inum6, group2, "逻辑分析");

		} else {

			double Dnum7 = Double.valueOf(num7);
			int inum7 = (int) Dnum7;
			double Dnum8 = Double.valueOf(num8);
			int inum8 = (int) Dnum8;

			dataset.addValue(inum1, group1, "内容");
			dataset.addValue(inum2, group1, "表达");
			dataset.addValue(inum3, group1, "语法");
			dataset.addValue(inum4, group1, "书写");

			dataset.addValue(inum5, group2, "内容");
			dataset.addValue(inum6, group2, "表达");
			dataset.addValue(inum7, group2, "语法");
			dataset.addValue(inum8, group2, "书写");
		}

		return dataset;
	}
	
	
	/**
	 * 灵活性-构造数据集
	 * 
	 * 
	 */
	public static CategoryDataset createDatasetFlx(List<TxDataCount> list) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String group1 = "地区得分率";
		String group2 = "个人得分率"; 

		for(int i=0;i<list.size();i++){
			
			TxDataCount tc = list.get(i);
			
			String[] o = tc.getTxdcCount().split(";");
			
			if(o[1].equals("0")){
				
				continue;
				
			}
			// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
			dataset.addValue(Double.valueOf(o[4]),group1,tc.getTxdcName());
			dataset.addValue(Double.valueOf(o[2]),group2,tc.getTxdcName());
			
		}

		return dataset;
	}
	
	/**
	 * 构造数学成绩数据集
	 * 
	 * 
	 */
	public static CategoryDataset createDatasetMath(String num1, String num2, String num3, String num4, String num5,
			String num6, String num7, String num8,String num9, String num10, int i) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String group1 = "地区平均水平"; 
		String group2 = "个人得分率";

		double Dnum1 = Double.valueOf(num1);
		int inum1 = (int) Dnum1;
		double Dnum2 = Double.valueOf(num2);
		int inum2 = (int) Dnum2;
		double Dnum3 = Double.valueOf(num3);
		int inum3 = (int) Dnum3;
		double Dnum4 = Double.valueOf(num4);
		int inum4 = (int) Dnum4;
		double Dnum5 = Double.valueOf(num5);
		int inum5 = (int) Dnum5;
		double Dnum6 = Double.valueOf(num6);
		int inum6 = (int) Dnum6;
		double Dnum7 = Double.valueOf(num7);
		int inum7 = (int) Dnum7;
		double Dnum8 = Double.valueOf(num8);
		int inum8 = (int) Dnum8;

		if (i == 4) {

			dataset.addValue(inum1, group1, "数与代数");
			dataset.addValue(inum2, group1, "空间与图形");
			dataset.addValue(inum3, group1, "实践与综合");
			dataset.addValue(inum4, group1, "统计概率");

			dataset.addValue(inum5, group2, "数与代数");
			dataset.addValue(inum6, group2, "空间与图形");
			dataset.addValue(inum7, group2, "实践与综合");
			dataset.addValue(inum8, group2, "统计概率");

		} else if (i == 5){

			double Dnum9 = Double.valueOf(num9);
			int inum9 = (int) Dnum9;
			double Dnum10 = Double.valueOf(num10);
			int inum10 = (int) Dnum10;

			dataset.addValue(inum1, group1, "计算");
			dataset.addValue(inum2, group1, "推理");
			dataset.addValue(inum3, group1, "逻辑分析");
			dataset.addValue(inum4, group1, "空间想象");
			dataset.addValue(inum5, group1, "操作");

			dataset.addValue(inum6, group2, "计算");
			dataset.addValue(inum7, group2, "推理");
			dataset.addValue(inum8, group2, "逻辑分析");
			dataset.addValue(inum9, group2, "空间想象");
			dataset.addValue(inum10, group2, "操作");
		}

		return dataset;
	}

	/**
	 * 创建图表
	 * 
	 * 
	 */
	public static JFreeChart createChart(CategoryDataset categorydataset,int i) {
		CalibrationSpiderWebPlot spiderwebplot = new CalibrationSpiderWebPlot(categorydataset);
		spiderwebplot.setToolTipGenerator(new StandardCategoryToolTipGenerator());// 标准分类提示器

		// 刻度数
		spiderwebplot.setTicks(2);		
		// 阴影
		spiderwebplot.setWebFilled(false);		
		//背景颜色
		spiderwebplot.setBackgroundPaint(Color.white);
		//字体
		spiderwebplot.setLabelFont(new Font("微软雅黑",Font.PLAIN,9));
		
		
		//XY轴线颜色
		spiderwebplot.setAxisLinePaint(Color.lightGray);
		
		spiderwebplot.setSeriesOutlineStroke(0,new BasicStroke(1.0F, 1, 1, 1.0F, new float[] { 6F, 6F }, 0.0F));
		
		spiderwebplot.setSeriesOutlineStroke(1, new BasicStroke(1.0F, 1, 1, 1.0F, null, 0.0F));
		
		JFreeChart jfreechart = new JFreeChart("", TextTitle.DEFAULT_FONT, spiderwebplot, false);
		
		if(i==0){
			jfreechart.setBackgroundPaint(Color.white); // 背景颜色
		}
		if(i==1){
			jfreechart.setBackgroundPaint(new Color(203,254,254)); // 背景颜色
		}

		LegendTitle legendtitle = new LegendTitle(spiderwebplot);
		legendtitle.setPosition(RectangleEdge.BOTTOM);
		legendtitle.setItemFont(new Font("微软雅黑",Font.PLAIN,14));
		jfreechart.addSubtitle(legendtitle);
		return jfreechart;
	}

	/**
	 * 测试
	 * 
	 * 
	 */
	public static void main(String args[]) {
		CalibrationSpiderWebPlotDemo calibrationSpiderWebPlotDemo = new CalibrationSpiderWebPlotDemo(
				"JFreeChart: CalibrationSpiderWebPlotDemo.java");
		calibrationSpiderWebPlotDemo.pack();
		RefineryUtilities.centerFrameOnScreen(calibrationSpiderWebPlotDemo);
		calibrationSpiderWebPlotDemo.setVisible(true);
	}
}
