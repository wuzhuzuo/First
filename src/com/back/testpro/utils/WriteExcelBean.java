package com.back.testpro.utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * @author Henry.Ci modify:2006-10-25
 *         <p>
 *         Description: 用于写Excel文件
 *         </p>
 *
 */
public class WriteExcelBean extends ExcelBean {

	public jxl.write.WritableCellFormat wcftitle;
	public jxl.write.WritableCellFormat wcftitle1;
	public jxl.write.WritableCellFormat wcftitleBlue;
	public jxl.write.WritableCellFormat wcftitleDeepBlue;

	public jxl.write.WritableCellFormat wcfcaption;
	public jxl.write.WritableCellFormat wcfcaptionSong;
	public jxl.write.WritableCellFormat wcfcaptionYellow;
	public jxl.write.WritableCellFormat wcfcaptionGreen;
	public jxl.write.WritableCellFormat wcfcaptionGreen1;
	public jxl.write.WritableCellFormat wcfcaptionOrange;

	public jxl.write.WritableCellFormat wcfresult;
	public jxl.write.WritableCellFormat wcfresultYellow;
	public jxl.write.WritableCellFormat wcfresultlabel;
	

	public WriteExcelBean(String filename, int tag) {
		super(filename, tag);
	}

	public WriteExcelBean(String soufilename, String desfilename) {
		super(soufilename, desfilename);
	}

	/**
	 * 设置标题的打印格式 Title Font 无颜色
	 * 
	 * @throws WriteException
	 */
	public void setTitleFont() throws WriteException {
		jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 20, WritableFont.BOLD);
		wcftitle = new jxl.write.WritableCellFormat(wfc, jxl.write.NumberFormats.TEXT);
		wcftitle.setWrap(true);// 是否自动换行
		wcftitle.setAlignment(jxl.format.Alignment.CENTRE);
		wcftitle.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcftitle.setBorder(jxl.format.Border.BOTTOM, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	
	/**
	 * 设置标题的打印格式 Title Font 无颜色
	 * 
	 * @throws WriteException
	 */
	public void setTitleFont1() throws WriteException {
		jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD);
		wcftitle1 = new jxl.write.WritableCellFormat(wfc, jxl.write.NumberFormats.TEXT);
		wcftitle1.setWrap(true);// 是否自动换行
		wcftitle1.setAlignment(jxl.format.Alignment.CENTRE);
		wcftitle1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcftitle1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置标题的打印格式 Title Font 蓝色
	 * 
	 * @throws WriteException
	 */
	public void setTitleFontBlue() throws WriteException {
		jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD);
		wcftitleBlue = new jxl.write.WritableCellFormat(wfc, jxl.write.NumberFormats.TEXT);
		Color color = Color.decode("#CCFFFF");
		wwb.setColourRGB(Colour.PALE_BLUE, color.getRed(), color.getGreen(), color.getBlue());
		wcftitleBlue.setBackground(jxl.format.Colour.PALE_BLUE);
		wcftitleBlue.setWrap(true);// 是否自动换行
		wcftitleBlue.setAlignment(jxl.format.Alignment.CENTRE);
		wcftitleBlue.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcftitleBlue.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置标题的打印格式 Title Font 深蓝色
	 * 
	 * @throws WriteException
	 */
	public void setTitleFontDeepBlue() throws WriteException {
		jxl.write.WritableFont wfc = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD);
		wcftitleDeepBlue = new jxl.write.WritableCellFormat(wfc, jxl.write.NumberFormats.TEXT);
		wcftitleDeepBlue.setBackground(jxl.format.Colour.LIGHT_GREEN);
		wcftitleDeepBlue.setWrap(true);// 是否自动换行
		wcftitleDeepBlue.setAlignment(jxl.format.Alignment.CENTRE);
		wcftitleDeepBlue.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcftitleDeepBlue.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置字段字体打印格式 无色
	 */
	public void setCaptionFont() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 6, WritableFont.NO_BOLD);
		wcfcaption = new jxl.write.WritableCellFormat(wfc1);
		wcfcaption.setWrap(true);// 是否自动换行
		wcfcaption.setAlignment(jxl.format.Alignment.CENTRE);
		wcfcaption.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaption.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	
	/**
	 * 设置字段字体打印格式 无色 宋体
	 */
	public void setCaptionFontSong() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 8, WritableFont.NO_BOLD);
		wcfcaptionSong = new jxl.write.WritableCellFormat(wfc1);
		wcfcaptionSong.setWrap(true);// 是否自动换行
		wcfcaptionSong.setAlignment(jxl.format.Alignment.LEFT);
		wcfcaptionSong.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaptionSong.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置字段字体打印格式 黄色
	 */
	public void setCaptionFontYellow() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 6, WritableFont.NO_BOLD);
		wcfcaptionYellow = new jxl.write.WritableCellFormat(wfc1,jxl.write.NumberFormats.TEXT);
		wcfcaptionYellow.setBackground(jxl.format.Colour.LIGHT_ORANGE);
		wcfcaptionYellow.setWrap(true);// 是否自动换行
		wcfcaptionYellow.setAlignment(jxl.format.Alignment.CENTRE);
		wcfcaptionYellow.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaptionYellow.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	/**
	 * 设置字段字体打印格式 绿色
	 */
	public void setCaptionFontGreen() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 5, WritableFont.NO_BOLD);
		wcfcaptionGreen = new jxl.write.WritableCellFormat(wfc1);
		Color color = Color.decode("#CCFFCC");
		wwb.setColourRGB(Colour.ORANGE, color.getRed(), color.getGreen(), color.getBlue());
		wcfcaptionGreen.setBackground(jxl.format.Colour.ORANGE);
		wcfcaptionGreen.setWrap(false);// 是否自动换行
		wcfcaptionGreen.setAlignment(jxl.format.Alignment.CENTRE);
		wcfcaptionGreen.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaptionGreen.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	/**
	 * 设置字段字体打印格式 绿色
	 */
	public void setCaptionFontGreen1() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 7, WritableFont.NO_BOLD);
		wcfcaptionGreen1 = new jxl.write.WritableCellFormat(wfc1);
		Color color = Color.decode("#CCFFCC");
		wwb.setColourRGB(Colour.ORANGE, color.getRed(), color.getGreen(), color.getBlue());
		wcfcaptionGreen1.setBackground(Colour.ORANGE);
		wcfcaptionGreen1.setWrap(false);// 是否自动换行
		wcfcaptionGreen1.setAlignment(jxl.format.Alignment.CENTRE);
		wcfcaptionGreen1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaptionGreen1.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	/**
	 * 设置字段字体打印格式 桔色
	 */
	public void setCaptionFontOrange() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 5, WritableFont.NO_BOLD);
		wcfcaptionOrange = new jxl.write.WritableCellFormat(wfc1);
		Color color = Color.decode("#FFCC99");
		wwb.setColourRGB(Colour.LIGHT_ORANGE, color.getRed(), color.getGreen(), color.getBlue());
		wcfcaptionOrange.setBackground(jxl.format.Colour.LIGHT_ORANGE);
		wcfcaptionOrange.setWrap(false);// 是否自动换行
		wcfcaptionOrange.setAlignment(jxl.format.Alignment.CENTRE);
		wcfcaptionOrange.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfcaptionOrange.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置结果集打印格式 //结果字体 无色
	 */
	public void setResultFont() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 7,WritableFont.NO_BOLD);
		wcfresult = new jxl.write.WritableCellFormat(wfc1, jxl.write.NumberFormats.TEXT);
		wcfresult.setWrap(false);// 是否自动换行
		wcfresult.setAlignment(jxl.format.Alignment.CENTRE);
		wcfresult.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfresult.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 设置结果集打印格式 //结果字体 黄色
	 */
	public void setResultFontYellow() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 6,
				WritableFont.NO_BOLD);
		wcfresultYellow = new jxl.write.WritableCellFormat(wfc1, jxl.write.NumberFormats.TEXT);
		Color color = Color.decode("#FFFF99");
		wwb.setColourRGB(Colour.VERY_LIGHT_YELLOW, color.getRed(), color.getGreen(), color.getBlue());
		wcfresultYellow.setBackground(jxl.format.Colour.VERY_LIGHT_YELLOW);
		wcfresultYellow.setWrap(true);// 是否自动换行
		wcfresultYellow.setAlignment(jxl.format.Alignment.CENTRE);
		wcfresultYellow.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfresultYellow.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}
	

	/**
	 * 设置结果集打印格式 //结果字体 无色 //文本格式
	 */
	public void setResultFontLabel() throws WriteException {
		jxl.write.WritableFont wfc1 = new jxl.write.WritableFont(WritableFont.createFont("Arial"), 6,
				WritableFont.NO_BOLD);
		wcfresultlabel = new jxl.write.WritableCellFormat(wfc1, jxl.write.NumberFormats.DEFAULT);
		wcfresultlabel.setWrap(true);// 是否自动换行
		wcfresultlabel.setAlignment(jxl.format.Alignment.LEFT);
		wcfresultlabel.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
		wcfresultlabel.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.TURQUOISE);
	}

	/**
	 * 
	 * @param row
	 *            begin wiht 0;
	 * @param col
	 *            begin wiht 0;
	 * @param context
	 *            插入内容
	 * @param wcf
	 *            插入的格式
	 */
	public void addCell(int col, int row, String context, WritableCellFormat wcf) {
		try {
			ws.addCell(new Label(col, row, context, wcf));
			ws.setColumnView(4, 4);
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	public int getColumns() {
		iColumns = ws.getColumns();
		return iColumns;
	}

	public int getRows() {
		iRows = ws.getRows();
		return iRows;
	}

	/**
	 * @param row
	 * @param col
	 * @param inumber
	 *            插入int 数值
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void addCellNumber(int col, int row, int inumber) throws RowsExceededException, WriteException {
		jxl.write.Number labelN = new jxl.write.Number(col, row, inumber, wcfresult);
		ws.addCell(labelN);
	}

	/**
	 * @param row
	 * @param col
	 * @param dnumber
	 *            插入double数值
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void addCellNumber(int col, int row, double dnumber) throws RowsExceededException, WriteException {
		jxl.write.Number labelN = new jxl.write.Number(col, row, dnumber, wcfresultYellow);
		ws.addCell(labelN);
	}

	/**
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCell(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcfresult));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	/**
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellBlue(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcftitleBlue));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	/**
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellYellow(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcfcaptionYellow));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	/**
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellYellowResult(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcfresultYellow));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}
	/**
	 * 蓝色背景标题
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellTitleBlue(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcftitleBlue));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}
	/**
	 * 绿色背景字段
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellCaptionGreen(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcfcaptionGreen));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}
	/**
	 * 黄色背景结果
	 * @param row
	 * @param col
	 * @param context
	 *            插入 String
	 */
	public void addCellResultYellow(int col, int row, String context) {
		try {
			ws.addCell(new Label(col, row, context, wcfresultYellow));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	/**
	 * @param url
	 *            插入图片 url
	 */
	public void addPic(String url, int col, int row, int width, int height) {

		try {

			File imgFile = new File(url);

			// WritableImage(col, row, width, height, imgFile);4 4 4 18
			// col row是图片的起始行起始列 width height是定义图片跨越的行数与列数
			WritableImage image = new WritableImage(col, row, width, height, imgFile);
			ws.addImage(image);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param row
	 *            需要删除的row
	 */
	public void removeRow(int row) {
		ws.removeRow(row);
	}

	/**
	 * @param row
	 *            需要加入的row
	 */
	public void insertRow(int row) {
		ws.insertRow(row);
	}

	/**
	 * @param row
	 *            列合并开始 row 以0开始计算
	 * @param col
	 *            行合并开始 col 以0开始计算
	 * @param rowend
	 *            列合并结束row 以0开始计算
	 * @param colend
	 *            行合并结束col 以0开始计算
	 * @param context
	 *            输入内容
	 * @param wcf
	 *            格式
	 */
	public void addMergeCell(int col, int row, int rowend, int colend, String context, WritableCellFormat wcf) {
		try {
			ws.mergeCells(col, row, rowend, colend);
			ws.addCell(new Label(col, row, context, wcf));
		} catch (RowsExceededException e) {
			close();
			e.printStackTrace();
		} catch (WriteException e) {
			close();
			e.printStackTrace();
		}
	}

	public void writeAble() throws WriteException {
		try {
			createWorkbook();
			
			setTitleFont();
			setTitleFont1();
			setTitleFontBlue();
			setTitleFontDeepBlue();
			
			setCaptionFont();
			setCaptionFontSong();
			setCaptionFontYellow();
			setCaptionFontGreen();
			setCaptionFontGreen1();
			setCaptionFontOrange();
			
			setResultFont();
			setResultFontYellow();
			setResultFontLabel();

		} catch (IOException e) {
			close();
			e.printStackTrace();
		}
	}

	/**
	 * 新增sheet
	 * 
	 * @param sheetName
	 * @param i
	 * @throws WriteException
	 * @throws IOException
	 */
	public void writeAbleSheet(String sheetName, int i) throws WriteException, IOException {

		createSheet(sheetName + "_" + (i + 1), i);// 写sheet名称

	}

	public static void main(String[] args) throws WriteException, IOException {
		WriteExcelBean web = new WriteExcelBean("d:\\test.xls", 0);
		web.writeAble();
		web.addCell(2, 6, "test");
		web.addMergeCell(3, 0, 14, 3, "统计表", web.wcftitle);
		web.write();
		web.close();
	}
}
