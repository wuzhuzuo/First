package com.back.testpro.controller;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Controller;

@Controller
public class commonController {

	/**
	 * 对单元格进行解析操作
	 * 
	 * @param cell
	 * @return
	 */
	public String parseCell(HSSFCell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				DecimalFormat format = new DecimalFormat("######0.00");
				// 单元格设置成常规
				result = format.format(value);

				String a = result.substring(result.length() - 3);

				if (a.equals(".00")) {// 如果以 .00 结尾 取消不显示
					result = result.substring(0, result.length() - 3);
				}

			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString().trim();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}

	/**
	 * 等级计算
	 * 
	 * @param String
	 * @return String
	 */
	public String numLevel(String str1, String str2) {

		double c = 0;
		double a = Double.valueOf(str1).doubleValue();
		double b = Double.valueOf(str2).doubleValue();

		String result = "0";

		if (b != 0) {
			c = a / b * 100;
		} else {
			c = 0;
		}

		if (c > 80) {
			result = "A";
		} else if (c > 60 && c <= 80) {
			result = "B";
		} else if (c > 40 && c <= 60) {
			result = "C";
		} else if (c > 0 && c <= 40) {
			result = "D";
		}

		return result;
	}

	/**
	 * 建议等级计算
	 * 
	 * @param String
	 * @return String
	 */
	public String advLevel(String str1, String str2) {

		double c = 0;
		double a = Double.valueOf(str1).doubleValue();
		double b = Double.valueOf(str2).doubleValue();

		String result = "";

		if (b != 0) {
			c = a / b * 100;
		} else {
			c = 0;
		}

		if (c > 80) {
			result = "A";
		} else if (c > 60 && c <= 80) {
			result = "B";
		} else if (c > 40 && c <= 60) {
			result = "C";
		} else if (c > 0 && c <= 40) {
			result = "D";
		}

		return result;
	}

	/**
	 * 年份+季度
	 * 
	 * @param String
	 * @return String
	 */
	public String changeYear(String a) {

		String b = "";

		String c = "";

		if (a.length() > 4) {

			c = a.substring(4, 5);

			if (c != "" && c.equals("1")) {
				b = a.substring(0, 4) + "春";
			}
			if (c != "" && c.equals("2")) {
				b = a.substring(0, 4) + "夏";
			}
			if (c != "" && c.equals("3")) {
				b = a.substring(0, 4) + "秋";
			}
			if (c != "" && c.equals("4")) {
				b = a.substring(0, 4) + "冬";
			}
		}

		return b;
	}

	/**
	 * 平均值得分率
	 * 
	 * @param list
	 * @param String
	 * @return String
	 */
	public String numAvg(ArrayList<Double> list, String str1) {

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String result = "0";

		double a = Double.valueOf(str1).doubleValue();

		if (list.size() < 1) {

			return result;

		}

		if (a == 0) {
			return result;
		}

		double count = 0;// 取值

		double sum = 0;// 总和

		for (int i = 0; i < list.size(); i++) {

			count = (Double) list.get(i);

			sum = sum + count;

		}

		double avg = (sum / list.size()) / a * 100;

		result = df.format(avg);

		return result;
	}

	/**
	 * 平均值计算
	 * 
	 * @param list
	 * @param String
	 * @return String
	 */
	public String numListAvg(ArrayList<Double> list) {

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String result = "0.00";

		if (list.size() < 1) {

			return result;

		}

		double count = 0;// 取值

		double sum = 0;// 总和

		for (int i = 0; i < list.size(); i++) {

			count = (Double) list.get(i);

			sum = sum + count;

		}

		double avg = sum / list.size();

		result = df.format(avg);

		return result;
	}

	/**
	 * 总和
	 * 
	 * @param list
	 * @return String
	 */
	public String numAll(ArrayList<Double> list) {

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String result = "0";

		if (list.size() < 1) {

			return result;

		}

		double count = 0;// 取值

		double sum = 0;// 总和

		for (int i = 0; i < list.size(); i++) {

			count = list.get(i);

			sum = sum + count;

		}

		result = df.format(sum);

		return result;
	}

	/**
	 * 减
	 */
	public String sub(String aa, String bb) {

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		double a = Double.valueOf(aa).doubleValue();
		double b = Double.valueOf(bb).doubleValue();
		double c = a - b;

		return df.format(c);
	}

	/**
	 * 计算标准偏差
	 * 
	 * @param list
	 * @return String
	 * 
	 */
	public String stdevData(ArrayList<Double> list) {

		DecimalFormat df = new DecimalFormat("######0.00000");// 计算用保留五位

		String result = "0";// 返回结果

		double count = 0;// 取值

		double sum = 0;// 总和

		if (list.size() < 1) {

			return result;

		}

		if (list.size() == 1) {

			return result;

		}

		for (int i = 0; i < list.size(); i++) {

			count = list.get(i);

			sum = sum + count;

		}

		double avg = sum / list.size();// 平均值

		double sum1 = 0;// 计算差值平方

		for (int i = 0; i < list.size(); i++) {

			count = Math.pow(list.get(i) - avg, 2);// 求差值平方

			sum1 = sum1 + count;

		}

		double stdev = Math.pow(sum1 / (list.size() - 1), 1.0 / 2);

		result = df.format(stdev);

		return result;
	}

	/**
	 * 百分位
	 * 
	 * @param ful
	 *            满分
	 * @param rank
	 *            排名
	 * @param count
	 *            总人数
	 * @return
	 */
	public String perData(String ful, int rank, int count) {

		DecimalFormat df = new DecimalFormat("######0.00");// 保存显示用保留二位

		String result = "0";

		double a = Double.valueOf(ful).doubleValue();
		double b = Double.valueOf(rank).doubleValue();

		double c = a - (a * b - 50) / count;

		result = df.format(c);

		return result;
	}

	/**
	 * 得分率计算
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String avgNum(String str1, String str2) {
		DecimalFormat df = new DecimalFormat("######0.00");// 计算用保留两位
		double c = 0;
		double a = Double.valueOf(str1).doubleValue();
		double b = Double.valueOf(str2).doubleValue();
		if (b != 0) {
			c = a / b * 100;
		} else {
			c = 0;
		}
		return df.format(c);
	}

	/**
	 * 标准分计算
	 * 
	 * @param list
	 * @param str1
	 *            个人得分
	 * @param str2
	 *            标准偏差
	 * @return
	 */
	public String standNum(ArrayList<Double> list, String str1, String str2) {
		DecimalFormat df = new DecimalFormat("######0.00");// 计算用保留两位
		double c = 0;
		double a = Double.valueOf(str1).doubleValue();
		double b = Double.valueOf(str2).doubleValue();

		if (b == 0) {

			return "0";
		}

		double count = 0;// 取值
		double sum = 0;// 总和

		for (int i = 0; i < list.size(); i++) {

			count = list.get(i);

			sum = sum + count;

		}

		double avg = sum / list.size();// 平均值

		c = (a - avg) / b * 10 + 50;

		return df.format(c);
	}

	/**
	 * 除
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String div(String str1, int str2) {

		DecimalFormat df = new DecimalFormat("######0.00");// 计算用保留两位
		double c = 0;
		double a = Double.valueOf(str1).doubleValue();
		double b = Double.valueOf(str2).doubleValue();

		if (b != 0) {

			c = a / b;

		} else {

			c = 0;
		}

		return df.format(c);
	}

	public static void main(String[] args) {
		commonController c = new commonController();
		String re = c.perData("26", 3, 69);
		System.out.println(re);
	}

}
