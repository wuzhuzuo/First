package com.back.testpro.controller;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.utils.DateTime;
import com.back.testpro.JfreeCharIntface.barChart;
import com.back.testpro.model.TxDataCount;
import com.back.testpro.model.TxDataCountMain;
import com.back.testpro.model.TyModel;
import com.back.testpro.model.TyModelLanguage;
import com.back.testpro.model.TyModelMain;
import com.back.testpro.service.TxDataCountMainService;
import com.back.testpro.service.TxDataCountService;
import com.back.testpro.service.TyModelLanguageService;
import com.back.testpro.service.TyModelMainService;
import com.back.testpro.service.TyModelService;
import com.back.testpro.testNew.CalibrationSpiderWebPlotDemo;
import com.back.testpro.utils.IConstant;
import com.back.testpro.utils.WriteExcelBean;

import jxl.write.WriteException;

@Controller
public class TxDataCountController extends BaseController {

	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOperateButton(List<TResource> re, String[] params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired(required = true)
	private TxDataCountService txDataCountService;

	@Autowired(required = true)
	private TxDataCountMainService txDataCountMainService;

	@Autowired(required = true)
	private TyModelMainService tyModelMainService;

	@Autowired(required = true)
	private commonController commonController;
	@Autowired(required = true)
	private TyModelService tyModelService;
	@Autowired(required = true)
	private TyModelLanguageService tyModelLanguageService;

	private String filename;

	protected WriteExcelBean web;

	/*
	 * 数据列表
	 */
	@RequestMapping(value = "/back/dataCount_list")
	public String countList(TxDataCountMain tdcm, ModelMap model) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(true);// 分页状态
		
		
		String re = getLoginSession().getDepartmentName();
		String flag = "0";
		
		if(re.equals("教育机构")){//权限判定
			flag = "1";
		};
		model.put("flag",flag);

		String id = request.getParameter("id");

		tdcm.setTdmMrid(Integer.parseInt(id));

		List<TxDataCountMain> tdcms = txDataCountMainService.queryTemp(tdcm);
		
		model.put("tdcm", tdcm);
		model.put("tdcms", tdcms);
		model.put("id", id);
		
		model.put("page", page);

		return "backpage/testpro/txDataCount/list";
	}
	
	
	/*
	 * 高级检索
	 */
	@RequestMapping(value = "/back/dataCount_checkList")
	public String countCheckList(TxDataCount tdc, ModelMap model) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态
		
		String id = request.getParameter("id");
		model.put("id", id);
		
		return "backpage/testpro/txDataCount/checkList";
	}
	
	/*
	 * 删除数据
	 */
	@RequestMapping(value = "/back/dataCount_del")
	public String countDel(TxDataCountMain tdcm, ModelMap model) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String mId = request.getParameter("mId");

		String id = request.getParameter("id");
		
		String[] Cid = null;

		if (mId != "" || mId.length() > 0) {

			String Countid = mId.replaceAll(",", ";");

			Cid = Countid.split(";");
		}
			
		txDataCountService.deleteByPrimaryKeys(Cid);//批量删除子表
		
		txDataCountMainService.deleteByPrimaryKeys(Cid);//批量删除主表

		model.put("id", id);
		
		model.put("page", page);

		return "redirect:/back/dataCount_list.do?id="+id;
	}

	/*
	 * 添加数据页面
	 */
	@RequestMapping(value = "/back/dataCount_add")
	public String countAdd(ModelMap model) {

		String id = request.getParameter("id");

		TyModelMain tmMain = new TyModelMain();

		tmMain.setTymmSpare1(id);

		List<TyModelMain> tms = tyModelMainService.queryTempList(tmMain);// 取某一项模板列表

		model.put("tms", tms);

		model.put("id", id);

		return "backpage/testpro/txDataCount/edit";
	}

	/*
	 * 显示数据页面
	 */
	@RequestMapping(value = "/back/dataCount_update")
	public String countUpdata(TxDataCount tdc, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态
		
		String re = getLoginSession().getDepartmentName();
		String flag = "0";
		
		if(re.equals("教育机构")){//权限判定
			flag = "1";
		};
		model.put("flag",flag);


		String id = request.getParameter("id");

		String tdmId = request.getParameter("tdmId");

		tdc.setTxdcMainid(tdmId);

		List<TxDataCount> tdcs = txDataCountService.queryTempList(tdc);
		
		TxDataCountMain tdcm = txDataCountMainService.selectByPrimaryKey(tdmId);

		model.put("tdcm", tdcm);
		
		model.put("tdcs", tdcs);

		model.put("id", id);

		model.put("tdmId", tdmId);

		return "backpage/testpro/txDataCount/update";
	}

	
	/*
	 * 更新数据
	 */
	@RequestMapping(value = "/back/dataCount_updateSave")
	public String countUpdataSave(TxDataCountMain tdcm, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String id = request.getParameter("id");

		String tdmId = request.getParameter("tdmId");
		
		TxDataCount tdc = new TxDataCount();
		tdc.setTxdcMainid(tdmId);
		List<TxDataCount> tdcs = txDataCountService.queryTempList(tdc);//获得子表
		String[] s = tdmId.split(";");
		
		if (tdcs != null || tdcs.size()>0) {
			txDataCountService.deleteByPrimaryKeys(s);//删除子表
		}
		
		String[] txdcNames = request.getParameterValues("txdcNames");
		String[] txdcFlags = request.getParameterValues("txdcFlags");
		String[] txdcOrders = request.getParameterValues("txdcOrders");
		String[] txdcSides = request.getParameterValues("txdcSides");
		String[] txdcCounts = request.getParameterValues("txdcCounts");
		
		if (txdcNames != null && txdcFlags != null && txdcOrders != null && txdcSides != null 
				&& txdcNames.length != txdcFlags.length 
				&& txdcOrders.length != txdcSides.length 
				&& txdcNames.length != txdcOrders.length ) {
			model.put("href", "dataCount_list?id=" + id);
			model.put("msg", "数据信息有误！ ");
			return IConstant.ERROR_PAGE;
		}
		
		for (int i = 0; i < txdcNames.length; i++) {
			TxDataCount tdc1 = new TxDataCount();
			
			tdc1.setTxdcMainid(tdmId);
			tdc1.setTxdcFlag(Integer.parseInt(txdcFlags[i]));
			tdc1.setTxdcOrder(Integer.parseInt(txdcOrders[i]));
			tdc1.setTxdcName(txdcNames[i]);
			tdc1.setTxdcSide(txdcSides[i]);
			
			String countNames = "";
			if(txdcFlags[i].equals("1")){
				String[] c = request.getParameterValues("Ccs"+i);
				for(String ss:c)
					countNames = countNames + ss +";";
				
				tdc1.setTxdcCount(countNames.substring(0,countNames.length()-1));
				
			}else{
				tdc1.setTxdcCount(txdcCounts[i]);
			}
			
			txDataCountService.insert(tdc1);
		}
		
		tdcm.setTdmName(txdcCounts[3]);// 姓名
		tdcm.setTdmTestnum(txdcCounts[2]);// 考号
		tdcm.setTdmSchool(txdcCounts[9]);// 学校
		tdcm.setTdmGrade(txdcCounts[7]);// 年级
		tdcm.setTdmClass(txdcCounts[6]);// 班级
		tdcm.setTdmSpare5(txdcCounts[4]);// 身份证
		tdcm.setTdmMrid(Integer.parseInt(id));
		
		String zh = request.getParameter("zh");
		String zj = request.getParameter("zj");
		String count3 = request.getParameter("count3");
		
		tdcm.setTdmSpare6(zh+"||"+zj+"||"+count3);
		
		txDataCountMainService.updateByPrimaryKey(tdcm);//更新主表

		model.put("id", id);

		model.put("tdmId", tdmId);

		return "redirect:/back/dataCount_update.do?id="+id+"&tdmId="+tdmId;
	}

	/*
	 * 数据上传
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	@RequestMapping(value = "/back/dataCount_upload")
	public String countUpload(@RequestParam("excelFile") MultipartFile file, TxDataCountMain tdcm, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String modelId = request.getParameter("modelId");

		int id = tdcm.getTdmMrid();// 项目id

		int iRowNum = 0;// 工作表中的行数量
		int iCellNum = 0;// 每行中的列数量
		
		int fh = 0;

		try {
			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());

			HSSFWorkbook wb = new HSSFWorkbook(fs);// xls 获取工作簿对象

			HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表

			iRowNum = sheet.getPhysicalNumberOfRows();// 获取sheet表中总行数

			if (iRowNum == 0) {
				model.put("href", "dataCount_list.do?id=" + id);
				model.put("msg", "数据错误请核对！");
				return IConstant.ERROR_PAGE;
			}

			TyModel ty = new TyModel();
			ty.setTymSpare2(modelId);
			List<TyModel> tys = tyModelService.queryView(ty);// 获得第一个子模板

			TyModelLanguage tymlan = new TyModelLanguage();
			tymlan.setTymlMainid(modelId);
			List<TyModelLanguage> tymlans = tyModelLanguageService.queryTemp(tymlan);// 获得第二个子模板
			Map<String, TyModelLanguage> mapTymlans0 = new HashMap<String, TyModelLanguage>();// 综合分析
			Map<String, TyModelLanguage> mapTymlans1 = new HashMap<String, TyModelLanguage>();// 专家建议
			for (TyModelLanguage tmlMap : tymlans)
				if (tmlMap.getTymlFlag() == 0) {
					mapTymlans0.put(tmlMap.getTymlName(), tmlMap);
				} else if (tmlMap.getTymlFlag() == 1) {
					mapTymlans1.put(tmlMap.getTymlName(), tmlMap);
				}

			Map<String, List> mapL = new HashMap<String, List>();

			Map<String, List> mapR = new HashMap<String, List>();

			for (TyModel re : tys)
				if (re.getTymFlag() == 1 && re.getTymSide().equals("L")) {

					List<Double> listL = new ArrayList<Double>();

					mapL.put(re.getTymName(), listL);

				} else if (re.getTymFlag() == 1 && re.getTymSide().equals("R")) {

					List<Double> listR = new ArrayList<Double>();

					mapR.put(re.getTymName(), listR);

				}

			ArrayList<TxDataCountMain> tdcms = new ArrayList<TxDataCountMain>();
			ArrayList<TxDataCount> tdcs = new ArrayList<TxDataCount>();

			ArrayList<Double> listAllsum = new ArrayList<Double>();// 所有总数集合
			
			ArrayList<Double> listRfull = new ArrayList<Double>();// 所有R区项目满分集合

			/** 开始读取行数据 */

			int startRowNum = 1;// excel 1含标题 0不含标题
			
			

			for (int j = startRowNum; j < iRowNum; j++) {
				HSSFRow rowTmp = sheet.getRow(j);
				if (rowTmp == null) {// 判断是否为空行
					continue;
				}
				if (j == startRowNum) {
					iCellNum = rowTmp.getLastCellNum();

					if (iCellNum != tys.size()) {// 判断列数与模板项数

						model.put("href", "dataCount_list.do?id=" + id);
						model.put("msg", "导入表格列数与所选模板项数不符！请核对后重新上传。");
						return IConstant.ERROR_PAGE;

					}
					iCellNum = iCellNum + 1;// 获取第一行中总单元格总数+1
				}
				String aValues[] = new String[iCellNum];// 列数集合

				/** 开始读取每行中每个单元格数据 */

				for (int k = 0; k < iCellNum; k++) {
					HSSFCell cellTmp = rowTmp.getCell(k);
					if (cellTmp == null) {// 判断是否为空单元格
						aValues[k] = new String("");
						continue;
					}
					aValues[k] = commonController.parseCell(cellTmp);// 解析单元格中的数据
				}

				String mainID = UUID.randomUUID().toString();

				TxDataCountMain tdcm1 = new TxDataCountMain();

				tdcm1.setTdmId(mainID);
				tdcm1.setTdmMrid(tdcm.getTdmMrid());
				tdcm1.setTdmTestarea(tdcm.getTdmTestarea());
				tdcm1.setTdmYearmonth(tdcm.getTdmYearmonth());
				tdcm1.setTdmYearseason(tdcm.getTdmYearseason());

				tdcm1.setTdmName(aValues[3]);// 姓名
				tdcm1.setTdmTestnum(aValues[2]);// 考号
				tdcm1.setTdmSchool(aValues[9]);// 学校
				tdcm1.setTdmGrade(aValues[6]);// 年级
				tdcm1.setTdmClass(aValues[7]);// 班级
				tdcm1.setTdmSpare5(aValues[4]);// 身份证
				tdcm1.setTdmSpare1(tdcm.getTdmSpare1());// 报告名称

				ArrayList<Double> numAllR = new ArrayList<Double>();// 右侧项总和
																	// 用于排名

				String gen = "";// 综合分析
				String adv = "";// 专家建议
				for (int r = 0; r < tys.size(); r++) {

					TyModel tm = tys.get(r);

					TxDataCount tdc = new TxDataCount();

					tdc.setTxdcMainid(mainID);
					tdc.setTxdcOrder(tm.getTymOrder());
					tdc.setTxdcName(tm.getTymName());
					tdc.setTxdcFlag(tm.getTymFlag());
					tdc.setTxdcSide(tm.getTymSide());

					if (tm.getTymFlag() == 1) {// 数据集合(得分;满分;得分率;等级;团队平均得分率;标准偏差;标准分)

						tdc.setTxdcCount(aValues[r] + ";" + tm.getTymFuldata());

						String level = commonController.numLevel(aValues[r], tm.getTymFuldata());// 等级

						String advLevel = commonController.advLevel(aValues[r], tm.getTymFuldata());// 建议等级

						TyModelLanguage tmlnew0 = mapTymlans0.get(tm.getTymName());

						TyModelLanguage tmlnew1 = mapTymlans1.get(tm.getTymName());

						if (level.equals("A") && !tmlnew0.getTymlLevel1().equals("")) {
							gen = gen + " 【" + tm.getTymName() + "】" + tmlnew0.getTymlLevel1();
						}
						if (level.equals("B") && !tmlnew0.getTymlLevel2().equals("")) {
							gen = gen + " 【" + tm.getTymName() + "】" + tmlnew0.getTymlLevel2();
						}
						if (level.equals("C") && !tmlnew0.getTymlLevel3().equals("")) {
							gen = gen + " 【" + tm.getTymName() + "】" + tmlnew0.getTymlLevel3();
						}
						if (level.equals("D") && !tmlnew0.getTymlLevel4().equals("")) {
							gen = gen + " 【" + tm.getTymName() + "】" + tmlnew0.getTymlLevel4();
						}

						if (advLevel.equals("A") && !tmlnew1.getTymlLevel1().equals("")) {
							adv = adv + " 【" + tm.getTymName() + "】" + tmlnew1.getTymlLevel1();
						}
						if (advLevel.equals("B") && !tmlnew1.getTymlLevel2().equals("")) {
							adv = adv + " 【" + tm.getTymName() + "】" + tmlnew1.getTymlLevel2();
						}
						if (advLevel.equals("C") && !tmlnew1.getTymlLevel3().equals("")) {
							adv = adv + " 【" + tm.getTymName() + "】" + tmlnew1.getTymlLevel3();
						}
						if (advLevel.equals("D") && !tmlnew1.getTymlLevel4().equals("")) {
							adv = adv + " 【" + tm.getTymName() + "】" + tmlnew1.getTymlLevel4();
						}

						if (tm.getTymSide().equals("L")) {// L行操作

							List<Double> listL1 = new ArrayList<Double>();

							listL1 = mapL.get(tm.getTymName());

							listL1.add(Double.valueOf(aValues[r]));

							mapL.put(tm.getTymName(), listL1);

						} else if (tm.getTymSide().equals("R")) {// R行操作

							List<Double> listR1 = new ArrayList<Double>();

							listR1 = mapR.get(tm.getTymName());

							listR1.add(Double.valueOf(aValues[r]));

							mapR.put(tm.getTymName(), listR1);

							numAllR.add(Double.valueOf(aValues[r]));
							
							listRfull.add(Double.valueOf(tm.getTymFuldata()));

						}

					} else if (tm.getTymFlag() == 0) {

						tdc.setTxdcCount(aValues[r]);
					}

					tdcs.add(tdc);

				}

				tdcm1.setTdmSpare4(commonController.numAll(numAllR));

				tdcm1.setTdmSpare6(gen + "||" + adv);

				listAllsum.add(Double.valueOf(commonController.numAll(numAllR)));

				tdcms.add(tdcm1);
				
				fh++;

			}
			/** 结束读取每行中每个单元格数据 */

			/** 数据操作 **/

			if (tdcs.size() > 0) {

				for (int i = 0; i < tdcs.size(); i++) {

					TxDataCount tc = tdcs.get(i);

					if (tc.getTxdcFlag() == 0) {// 标识为0 不进行处理
						continue;
					}

					String[] count = tc.getTxdcCount().split(";");

					if (count.length > 1) {

						String sco = commonController.avgNum(count[0], count[1]);// 得分率

						String level = commonController.numLevel(count[0], count[1]);// 等级

						if (tc.getTxdcSide().equals("L")) {

							List<Double> a = new ArrayList<Double>();// L区
																		// 某项数据结果集合

							a = mapL.get(tc.getTxdcName());

							String areSco = commonController.numAvg((ArrayList<Double>) a, count[1]);// L区
																										// 某项平均得分率

							String stdev = commonController.stdevData((ArrayList<Double>) a);// L区
																								// 某项标准偏差

							String stand = commonController.standNum((ArrayList<Double>) a, count[0], stdev);// L区
																												// 某项标准分

							tdcs.get(i).setTxdcCount(tc.getTxdcCount() + ";" + sco + ";" + level + ";" + areSco + ";"
									+ stdev + ";" + stand);// 数据集合(得分;满分;得分率;等级;团队平均得分率;标准偏差;标准分)

						} else if (tc.getTxdcSide().equals("R")) {

							List<Double> a = new ArrayList<Double>();// L区
																		// 某项数据结果集合

							a = mapR.get(tc.getTxdcName());

							String areSco = commonController.numAvg((ArrayList<Double>) a, count[1]);// R区
																										// 某项平均得分率

							String stdev = commonController.stdevData((ArrayList<Double>) a);// R区
																								// 某项标准偏差

							String stand = commonController.standNum((ArrayList<Double>) a, count[0], stdev);// R区
																												// 某项标准分

							tdcs.get(i).setTxdcCount(tc.getTxdcCount() + ";" + sco + ";" + level + ";" + areSco + ";"
									+ stdev + ";" + stand);// 数据集合(得分;满分;得分率;等级;团队平均得分率;标准偏差;标准分)
						}
					}
				}
			}

			/** 定义排序规则 **/
			Comparator<TxDataCountMain> comparator = new Comparator<TxDataCountMain>() {
				public int compare(TxDataCountMain s1, TxDataCountMain s2) {
					// 先排成绩
					if (s1.getTdmSpare4() != s2.getTdmSpare4()) {
						
						return Integer.valueOf((int) (Double.valueOf(s2.getTdmSpare4())-Double.valueOf(s1.getTdmSpare4())));
						
					} else {
						// 成绩相同则按学号排序
						return s1.getTdmTestnum().compareTo(s2.getTdmTestnum());
					}
				}
			};

			Collections.sort(tdcms, comparator);// 开始排序

			int rangKing = 1;// 排序起始数
			
			ArrayList<TxDataCountMain> txDataCountMainArrayList = new ArrayList<TxDataCountMain>();
			ArrayList<TxDataCount> txDataCountArrayList = new ArrayList<TxDataCount>();

			if (tdcms.size() > 0) {
				for (int i = 0; i < tdcms.size(); i++) {

					TxDataCountMain tdm1 = tdcms.get(i);
					String stdAllNum = commonController.standNum(listAllsum, tdm1.getTdmSpare4(),
							commonController.stdevData(listAllsum)); // 标准总分
					tdm1.setTdmSpare6(tdm1.getTdmSpare6() + "||" + stdAllNum);

					if (i < 1) {
						tdm1.setTdmSpare2(String.valueOf(rangKing));
						tdm1.setTdmSpare3(commonController.perData("100", rangKing, tdcms.size()));
						rangKing++;
						txDataCountMainArrayList.add(tdm1);
						continue;
					}

					TxDataCountMain tdm2 = tdcms.get(i - 1);

					if (tdm2.getTdmSpare4().equals(tdm1.getTdmSpare4())) {
						tdm1.setTdmSpare2(tdm2.getTdmSpare2());
						tdm1.setTdmSpare3(commonController.perData("100", Integer.valueOf(tdm2.getTdmSpare2()), tdcms.size()));
						txDataCountMainArrayList.add(tdm1);
						continue;
					}

					tdm1.setTdmSpare2(String.valueOf(rangKing));
					tdm1.setTdmSpare3(commonController.perData("100", rangKing, tdcms.size()));
					rangKing++;
					txDataCountMainArrayList.add(tdm1);
				}
			}
			
			
			 if (!tdcs.equals(null) && tdcs.size() > 0) {// 保存数据子表信息
			 
				 for (TxDataCount save : tdcs)
//					 txDataCountService.insert(save);
				 	 txDataCountArrayList.add(save);
			 }
			 
			 txDataCountMainService.insertNew(txDataCountMainArrayList, txDataCountArrayList);
			 

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "dataCount_list.do?id=" + id);
			model.put("msg", "Excel格式错误，请选择正确的模板文件！" );
			return IConstant.ERROR_PAGE;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "dataCount_list.do?id=" + id);
			model.put("msg", "Excel格式错误，请选择正确的模板文件！" );
			return IConstant.ERROR_PAGE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "dataCount_list.do?id=" + id);
			model.put("msg", "Excel格式错误，请选择正确的模板文件！" );
			return IConstant.ERROR_PAGE;
		}

		model.put("id", id);

		return "redirect:/back/dataCount_list.do?id=" + id;
	}

	/*
	 * 数据展示
	 */
	@RequestMapping(value = "/back/dataCount_viewMap")
	public String countMap(ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String id = request.getParameter("id");

		String tdmId = request.getParameter("tdmId");

		try {

			TxDataCountMain tdcm = txDataCountMainService.selectByPrimaryKey(tdmId);// 数据主表
																					// 1条
			String[] talk = tdcm.getTdmSpare6().split("\\|\\|");

			String gen = talk[0];

			String adv = talk[1];

			String stand1 = talk[2];// 标准总分1

			String stand2 = "";// 标准总分2

			String stand3 = "";// 标准总分3

			model.put("tdcm", tdcm);

			TxDataCount tdc = new TxDataCount();
			tdc.setTxdcMainid(tdmId);

			List<TxDataCount> tdcs = txDataCountService.queryTempList(tdc);// 数据子表
																			// N条

			List<TxDataCount> tdcsL = new ArrayList<TxDataCount>();// L集合

			List<TxDataCount> tdcsR = new ArrayList<TxDataCount>();// R集合

			ArrayList<String[]> listZ = new ArrayList<String[]>();// 循环集合

			int maxLine = 0;// 最大项数

			String zzName1 = "";// 成绩追踪名称
			String zzName2 = "";
			String zzName3 = "";

			for (TxDataCount re : tdcs)
				if (re.getTxdcSide() != null && re.getTxdcSide().equals("L")) {
					tdcsL.add(re);
				} else if (re.getTxdcSide() != null && re.getTxdcSide().equals("R")) {
					tdcsR.add(re);
				}

			Double Lmax = 0.00;
			Double Lmin = 100.00;
			Double Rmax = 0.00;
			Double Rmin = 100.00;

			String Lcount1 = "";// L区小计第1-6项
			String Lcount2 = "";
			String Lcount3 = "";
			String Lcount4 = "";
			String Lcount5 = "";
			String Lcount6 = "";
			ArrayList<Double> listL = new ArrayList<Double>();// L区个人得分集合
			ArrayList<Double> listLcount = new ArrayList<Double>();// L区满分集合
			ArrayList<Double> listLcountAve = new ArrayList<Double>();// L区地区集合

			String Rcount1 = "";// R区小计第1-6项
			String Rcount2 = "";
			String Rcount3 = "";
			String Rcount4 = "";
			String Rcount5 = "";
			String Rcount6 = "";
			ArrayList<Double> listR = new ArrayList<Double>();// R区个人得分集合
			ArrayList<Double> listRcount = new ArrayList<Double>();// R区满分集合
			ArrayList<Double> listRcountAve = new ArrayList<Double>();// R区地区集合

			if (tdcsL.size() <= tdcsR.size()) {

				maxLine = tdcsR.size();

				for (int i = 0; i < tdcsR.size(); i++) {

					String[] num = new String[18];// 定义页面一行内容 共18项

					if (i < tdcsL.size()) {// L区 操作
						num[0] = tdcsL.get(i).getTxdcName();
						String[] o = tdcsL.get(i).getTxdcCount().split(";");
						num[1] = o[2];
						num[2] = o[4];
						num[3] = commonController.sub(o[2], o[4]);
						num[4] = o[0];
						num[5] = o[3];
						num[6] = o[1];

						if (Double.valueOf(o[2]) > Lmax) {
							Lmax = Double.valueOf(o[2]);
						}
						if (Double.valueOf(o[2]) < Lmin) {
							Lmin = Double.valueOf(o[2]);
						}

						num[7] = tdcsR.get(i).getTxdcName();
						String[] k = tdcsR.get(i).getTxdcCount().split(";");
						num[8] = k[2];
						num[9] = k[4];
						num[10] = commonController.sub(k[2], k[4]);
						num[11] = k[0];
						num[12] = k[3];
						num[13] = k[1];

						if (Double.valueOf(k[2]) > Rmax) {
							Rmax = Double.valueOf(k[2]);
						}
						if (Double.valueOf(k[2]) < Rmin) {
							Rmin = Double.valueOf(k[2]);
						}

						listZ.add(num);

						listL.add(Double.valueOf(o[0]));
						listLcount.add(Double.valueOf(o[1]));

						listR.add(Double.valueOf(k[0]));
						listRcount.add(Double.valueOf(k[1]));

						listLcountAve.add(Double.valueOf(o[4]));
						listRcountAve.add(Double.valueOf(k[4]));
						
						
						if (i == tdcsR.size()-1) {
							
							String[] num1 = new String[18];

							listZ.add(num1);// 增加一行为追踪所用
						}

						continue;
					}

					num[7] = tdcsR.get(i).getTxdcName();
					String[] k = tdcsR.get(i).getTxdcCount().split(";");
					num[8] = k[2];
					num[9] = k[4];
					num[10] = commonController.sub(k[2], k[4]);
					num[11] = k[0];
					num[12] = k[3];
					num[13] = k[1];

					if (Double.valueOf(k[2]) > Rmax) {
						Rmax = Double.valueOf(k[2]);
					}
					if (Double.valueOf(k[2]) < Rmin) {
						Rmin = Double.valueOf(k[2]);
					}

					listR.add(Double.valueOf(k[0]));
					listRcount.add(Double.valueOf(k[1]));
					listRcountAve.add(Double.valueOf(k[4]));

					listZ.add(num);

					String[] num1 = new String[18];

					listZ.add(num1);// 增加一行为追踪所用
				}

			} else if (tdcsL.size() > tdcsR.size()) {

				maxLine = tdcsL.size();

				for (int i = 0; i < tdcsL.size(); i++) {

					String[] num = new String[18];// 定义页面一行内容 共18项

					if (i < tdcsR.size()) {// R区 操作
						num[0] = tdcsL.get(i).getTxdcName();
						String[] o = tdcsL.get(i).getTxdcCount().split(";");
						num[1] = o[2];
						num[2] = o[4];
						num[3] = commonController.sub(o[2], o[4]);
						num[4] = o[0];
						num[5] = o[3];
						num[6] = o[1];

						if (Double.valueOf(o[2]) > Lmax) {
							Lmax = Double.valueOf(o[2]);
						}
						if (Double.valueOf(o[2]) < Lmin) {
							Lmin = Double.valueOf(o[2]);
						}

						num[7] = tdcsR.get(i).getTxdcName();
						String[] k = tdcsR.get(i).getTxdcCount().split(";");
						num[8] = k[2];
						num[9] = k[4];
						num[10] = commonController.sub(k[2], k[4]);
						num[11] = k[0];
						num[12] = k[3];
						num[13] = k[1];

						if (Double.valueOf(k[2]) > Rmax) {
							Rmax = Double.valueOf(k[2]);
						}
						if (Double.valueOf(k[2]) < Rmin) {
							Rmin = Double.valueOf(k[2]);
						}

						listZ.add(num);

						listL.add(Double.valueOf(o[0]));
						listLcount.add(Double.valueOf(o[1]));

						listR.add(Double.valueOf(k[0]));
						listRcount.add(Double.valueOf(k[1]));

						listLcountAve.add(Double.valueOf(o[4]));
						listRcountAve.add(Double.valueOf(k[4]));

						continue;
					}

					num[0] = tdcsL.get(i).getTxdcName();
					String[] o = tdcsL.get(i).getTxdcCount().split(";");
					num[1] = o[2];
					num[2] = o[4];
					num[3] = commonController.sub(o[2], o[4]);
					num[4] = o[0];
					num[5] = o[3];
					num[6] = o[1];

					if (Double.valueOf(o[2]) > Lmax) {
						Lmax = Double.valueOf(o[2]);
					}
					if (Double.valueOf(o[2]) < Lmin) {
						Lmin = Double.valueOf(o[2]);
					}

					listL.add(Double.valueOf(o[0]));
					listLcount.add(Double.valueOf(o[1]));
					listLcountAve.add(Double.valueOf(o[4]));

					listZ.add(num);
				}
			}

			Double LmaxIn = Lmax * 0.9;
			Double RmaxIn = Rmax * 0.9;

			Double LminIn = Lmin * 1.1;
			Double RminIn = Rmin * 1.1;

			Lcount4 = commonController.numAll(listL);
			Rcount4 = commonController.numAll(listR);

			Lcount6 = commonController.numAll(listLcount);
			Rcount6 = commonController.numAll(listRcount);

			Lcount1 = commonController.avgNum(Lcount4, Lcount6);
			Rcount1 = commonController.avgNum(Rcount4, Rcount6);

			Lcount2 = commonController.numListAvg(listLcountAve);
			Rcount2 = commonController.numListAvg(listRcountAve);

			Lcount3 = commonController.sub(Lcount1, Lcount2);
			Rcount3 = commonController.sub(Rcount1, Rcount2);

			model.put("LmaxIn", LmaxIn);
			model.put("LminIn", LminIn);
			model.put("RmaxIn", RmaxIn);
			model.put("RminIn", RminIn);

			model.put("Lcount1", Lcount1);
			model.put("Lcount2", Lcount2);
			model.put("Lcount3", Lcount3);
			model.put("Lcount4", Lcount4);
			model.put("Lcount5", Lcount5);
			model.put("Lcount6", Lcount6);

			model.put("Rcount1", Rcount1);
			model.put("Rcount2", Rcount2);
			model.put("Rcount3", Rcount3);
			model.put("Rcount4", Rcount4);
			model.put("Rcount5", Rcount5);
			model.put("Rcount6", Rcount6);

			/** 生成L雷达图 begin **/
			DefaultCategoryDataset dataset1 = (DefaultCategoryDataset) CalibrationSpiderWebPlotDemo
					.createDatasetFlx(tdcsL);

			JFreeChart chart1 = CalibrationSpiderWebPlotDemo.createChart(dataset1,0);

			String url1 = request.getSession().getServletContext().getRealPath("/")+"/images/";

			String fileName1 = barChart.newCreatChart(chart1, 250, 200, null, session, url1);

			String graphURL1 = request.getSession().getServletContext().getRealPath("/") + fileName1;

			model.put("fileName1", fileName1);
			model.put("graphURL1", graphURL1);
			/** 生成L雷达图 end **/

			/** 生成R雷达图 begin **/
			DefaultCategoryDataset dataset2 = (DefaultCategoryDataset) CalibrationSpiderWebPlotDemo
					.createDatasetFlx(tdcsR);

			JFreeChart chart2 = CalibrationSpiderWebPlotDemo.createChart(dataset2,1);

			String url2 = request.getSession().getServletContext().getRealPath("/")+"/images/";

			String fileName2 = barChart.newCreatChart(chart2, 250, 200, null, session, url2);

			String graphURL2 = request.getSession().getServletContext().getRealPath("/") + fileName2;

			model.put("fileName2", fileName2);
			model.put("graphURL2", graphURL2);
			/** 生成R雷达图 end **/

			/** 生成柱状图 begin **/
			DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();

			if (tdcm.getTdmSpare5() != null && !tdcm.getTdmSpare5().equals("")) {

				List<TxDataCountMain> tdcm3 = txDataCountMainService.queryTempThree(tdcm);

				/** 追踪第一列为所打开数据 **/

				zzName1 = commonController.changeYear(tdcm.getTdmYearseason());

				dataset3.addValue(Double.valueOf(tdcm.getTdmSpare3()).doubleValue(),
						commonController.changeYear(tdcm.getTdmYearseason()), "百分位");// 第一行固定百分位

				for (int i = 0; i <= tdcsR.size(); i++) {
					// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
					if (i == 0) {
						String[] a = listZ.get(i);
						a[14] = "百分位";
						a[15] = tdcm.getTdmSpare3();
						continue;
					}

					String[] k = tdcsR.get(i - 1).getTxdcCount().split(";");
					dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
							commonController.changeYear(tdcm.getTdmYearseason()), tdcsR.get(i - 1).getTxdcName());

					String[] b = listZ.get(i);
					b[14] = tdcsR.get(i - 1).getTxdcName();
					b[15] = k[6];

				}
				
				dataset3.addValue(Double.valueOf(stand1).doubleValue(), 
						commonController.changeYear(tdcm.getTdmYearseason()), "标准总分");

				if (tdcm3.size() > 0) {

					int endLine = 2;// 数量计数 起始为2，到4终止,只取2个对象

					for (int i = 0; i < tdcm3.size(); i++) {

						if (endLine == 4) {
							break;
						}

						TxDataCountMain tdcmNew = tdcm3.get(i);

						if (tdcmNew.getTdmId().equals(tdcm.getTdmId())) {
							continue;
						}

						TxDataCount tdcNew = new TxDataCount();
						tdcNew.setTxdcMainid(tdcmNew.getTdmId());

						List<TxDataCount> tdcsNew = txDataCountService.queryTempList(tdcNew);

						Map<String, TxDataCount> mapR = new HashMap<String, TxDataCount>();

						for (TxDataCount reNew : tdcsNew)// R map集合
							if (reNew.getTxdcSide() != null && reNew.getTxdcSide().equals("R")) {
								mapR.put(reNew.getTxdcName(), reNew);
							}

						dataset3.addValue(Double.valueOf(tdcmNew.getTdmSpare3()).doubleValue(),
								commonController.changeYear(tdcmNew.getTdmYearseason()), "百分位");// 第一行固定百分位

						if (endLine == 2) {// 第2列数据构造
							zzName2 = commonController.changeYear(tdcmNew.getTdmYearseason());
							stand2 = tdcmNew.getTdmSpare6().split("\\|\\|")[2];

							for (int ii = 0; ii <= tdcsR.size(); ii++) {
								// 根据打开样本进行取数
								// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
								if (ii == 0) {
									String[] a = listZ.get(ii);
									a[16] = tdcmNew.getTdmSpare3();
									continue;
								}

								TxDataCount tdcMap = mapR.get(tdcsR.get(ii - 1).getTxdcName());// 根据样本名称取对象

								if (tdcMap == null) {// 无此对象 0.00补位

									dataset3.addValue(0.00, commonController.changeYear(tdcmNew.getTdmYearseason()),
											tdcsR.get(ii - 1).getTxdcName());
									String[] b = listZ.get(ii);
									b[16] = "0.00";

								} else {
									String[] k = tdcMap.getTxdcCount().split(";");
									dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
											commonController.changeYear(tdcmNew.getTdmYearseason()),
											tdcsR.get(ii - 1).getTxdcName());

									String[] b = listZ.get(ii);
									b[16] = k[6];
								}
							}
							
							dataset3.addValue(Double.valueOf(stand2).doubleValue(), 
									commonController.changeYear(tdcmNew.getTdmYearseason()), "标准总分");
							
						} else if (endLine == 3) {// 第3列数据构造
							zzName3 = commonController.changeYear(tdcmNew.getTdmYearseason());
							stand3 = tdcmNew.getTdmSpare6().split("\\|\\|")[2];
							
							for (int ii = 0; ii <= tdcsR.size(); ii++) {
								// 根据打开样本进行取数
								// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
								if (ii == 0) {
									String[] a = listZ.get(ii);
									a[17] = tdcmNew.getTdmSpare3();
									continue;
								}

								TxDataCount tdcMap = mapR.get(tdcsR.get(ii - 1).getTxdcName());// 根据样本名称取对象

								if (tdcMap == null) {// 无此对象 0.00补位

									dataset3.addValue(0.00, commonController.changeYear(tdcmNew.getTdmYearseason()),
											tdcsR.get(ii - 1).getTxdcName());
									String[] b = listZ.get(ii);
									b[17] = "0.00";

								} else {
									String[] k = tdcMap.getTxdcCount().split(";");
									dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
											commonController.changeYear(tdcmNew.getTdmYearseason()),
											tdcsR.get(ii - 1).getTxdcName());

									String[] b = listZ.get(ii);
									b[17] = k[6];
								}
							}
							
							dataset3.addValue(Double.valueOf(stand3).doubleValue(), 
									commonController.changeYear(tdcmNew.getTdmYearseason()), "标准总分");
						}
						endLine++;
					}

				}

			} else {// 无身份证信息则显示本次数据

				zzName1 = commonController.changeYear(tdcm.getTdmYearseason());

				dataset3.addValue(Double.valueOf(tdcm.getTdmSpare3()).doubleValue(),
						commonController.changeYear(tdcm.getTdmYearseason()), "百分位");// 第一行固定百分位

				for (int i = 0; i <= tdcsR.size(); i++) {
					// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
					if (i == 0) {
						String[] a = listZ.get(i);
						a[14] = "百分位";
						a[15] = tdcm.getTdmSpare3();
						continue;
					}

					String[] k = tdcsR.get(i - 1).getTxdcCount().split(";");
					dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
							commonController.changeYear(tdcm.getTdmYearseason()), tdcsR.get(i - 1).getTxdcName());

					String[] b = listZ.get(i);
					b[14] = tdcsR.get(i - 1).getTxdcName();
					b[15] = k[6];

				}
				
				dataset3.addValue(Double.valueOf(stand1).doubleValue(), 
						commonController.changeYear(tdcm.getTdmYearseason()), "标准总分");

			}

			// 创建主题样式
			StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
			// 设置标题字体
			standardChartTheme.setExtraLargeFont(new Font("隶书", Font.PLAIN, 20));
			// 设置图例的字体
			standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
			// 设置轴向的字体
			standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
			// 应用主题样式
			ChartFactory.setChartTheme(standardChartTheme);

			JFreeChart chart3 = ChartFactory.createBarChart("", "", "", dataset3, PlotOrientation.VERTICAL, true, false,
					false);

			chart3.setBackgroundPaint(Color.white);// 设定背景颜色

			// 得到图标中 plot 对象
			CategoryPlot plot = (CategoryPlot) chart3.getPlot();
			plot.setBackgroundAlpha(0.0f);// 设置背景颜色
			plot.setRangeGridlinePaint(Color.BLACK);// 设置横线颜色
			// 柱形属性
			BarRenderer br = (BarRenderer) plot.getRenderer();
			br.setShadowVisible(false);

			// y轴设置
			ValueAxis va = plot.getRangeAxis();
			va.setRange(0, 100);// 数据集
			// y轴刻度设置
			NumberAxis na = (NumberAxis) plot.getRangeAxis();
			na.setAutoTickUnitSelection(false);// 关闭自动分配
			NumberTickUnit ntu = new NumberTickUnit(20D);// 数据集分段单位
			na.setTickUnit(ntu);

			// x轴设置
			CategoryAxis ca = plot.getDomainAxis();
			ca.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));// 设置x轴文字

			ca.setMaximumCategoryLabelLines(10);// 设置竖型排列
			ca.setMaximumCategoryLabelWidthRatio(0.5f);

			ca.setTickLabelPaint(Color.BLACK);

			String graphURL3 = request.getSession().getServletContext().getRealPath("/");

			String url3 = request.getSession().getServletContext().getRealPath("/")+"/images/";

			String fileName3 = barChart.newCreatChart(chart3, 300, 300, null, session, url3);

			model.put("fileName3", fileName3);
			model.put("graphURL3", graphURL3);
			/** 生成柱状图 end **/

			model.put("zzName1", zzName1);
			model.put("zzName2", zzName2);
			model.put("zzName3", zzName3);

			model.put("gen", gen);
			model.put("adv", adv);

			model.put("tdcsL", tdcsL);
			model.put("tdcsR", tdcsR);
			model.put("listZ", listZ);
			model.put("maxLine", maxLine);
			model.put("tdcs", tdcs);
			model.put("id", id);

			model.put("stand1", stand1);
			model.put("stand2", stand2);
			model.put("stand3", stand3);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "dataCount_list.do?id=" + id);
			model.put("msg", "数据错误！" + e);
			return IConstant.ERROR_PAGE;
		}

		return "backpage/testpro/txDataCount/viewMap";
	}

	/**
	 * 数据导出
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/back/dataCount_exc")
	public String countExc(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String mId = request.getParameter("mId");

		String id = request.getParameter("id");
		
		String name = DateTime.getCurDate_yyyyMMddHHmmss();

		this.writeExcel(name+"导出.xls", mId, id);

		this.downExcel(request, response);

		return null;

	}
	
	
	/**
	 * 数据--批量导出
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/back/dataCount_excAll")
	public String countExcAll(TxDataCountMain tdcm,HttpServletRequest request, HttpServletResponse response,ModelMap model) throws Exception {
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String id = request.getParameter("id");
		
		tdcm.setTdmMrid(Integer.parseInt(id));

		List<TxDataCountMain> tdcms = txDataCountMainService.queryTemp(tdcm);
		
		if(tdcms.size()==0){
			return "redirect:/back/dataCount_list.do?id="+id;
		}
		
		String mId = "";
		
		for(TxDataCountMain tdcmNew : tdcms){
			
			mId = mId + tdcmNew.getTdmId()+";";
			
		}
		
		if(mId.length()>0){
			
			mId = mId.substring(0, mId.length() - 1);
			
		}
		
		String name = DateTime.getCurDate_yyyyMMddHHmmss();

		this.writeExcel(name+"导出.xls", mId, id);

		this.downExcel(request, response);

		return null;

	}

	/**
	 * 创建excel
	 * 
	 * @param filename
	 * @param title
	 * @param fields
	 * @param result
	 * @param sheetName
	 * @return
	 */

	public boolean writeExcel(String filename, String mId, String id) {
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态
		boolean bl = false;
		try {

			this.filename = filename;

			web = new WriteExcelBean(filename, 0);// 写标题

			web.writeAble();// 创建excel

			String[] Cid = null;

			if (mId != "" || mId.length() > 0) {

				String Countid = mId.replaceAll(",", ";");

				Cid = Countid.split(";");
			}

			for (int j = 0; j < Cid.length; j++) {

				String dateId = Cid[j];

				TxDataCountMain tdcm = txDataCountMainService.selectByPrimaryKey(dateId);// 取得成绩对象主表

				String[] talk = tdcm.getTdmSpare6().split("\\|\\|");

				String gen = talk[0];

				String adv = talk[1];
				
				String stand1 = talk[2];// 标准总分1

				String stand2 = "";// 标准总分2

				String stand3 = "";// 标准总分3

				TxDataCount tdc = new TxDataCount();
				tdc.setTxdcMainid(dateId);
				List<TxDataCount> tdcs = txDataCountService.queryTempList(tdc);// 取得子表

				web.writeAbleSheet(tdcm.getTdmName(), j);// 命名sheet

				/*------生成excel 数据  begin-----------*/
				/*-0-*/
				web.addMergeCell(0, 0, 17, 1, tdcm.getTdmSpare1(), web.wcftitle);
				/*-2-*/
				web.addMergeCell(0, 2, 0, 2, "考区", web.wcfcaptionGreen1);
				web.addMergeCell(1, 2, 1, 2, "组别", web.wcfcaptionGreen1);
				web.addMergeCell(2, 2, 3, 2, "考号", web.wcfcaptionGreen1);
				web.addMergeCell(4, 2, 5, 2, "姓名", web.wcfcaptionGreen1);
				web.addMergeCell(6, 2, 7, 2, "ID", web.wcfcaptionGreen1);
				web.addMergeCell(8, 2, 8, 2, "年级", web.wcfcaptionGreen1);
				web.addMergeCell(9, 2, 9, 2, "班级", web.wcfcaptionGreen1);
				web.addMergeCell(10, 2, 10, 2, "性别", web.wcfcaptionGreen1);
				web.addCellCaptionGreen(11, 2, "");
				web.addCellCaptionGreen(12, 2, "");
				web.addCellCaptionGreen(13, 2, "");
				web.addMergeCell(14, 2, 17, 2, "考点", web.wcfcaptionGreen1);
				/*-3-*/
				web.addCell(0, 3, tdcm.getTdmTestarea(), web.wcfresult);
				web.addCell(1, 3, tdcs.get(0).getTxdcCount(), web.wcfresult);
				web.addMergeCell(2, 3, 3, 3, tdcm.getTdmTestnum(), web.wcfresult);
				web.addMergeCell(4, 3, 5, 3, tdcm.getTdmName(), web.wcfresult);
				web.addMergeCell(6, 3, 7, 3, tdcs.get(1).getTxdcCount(), web.wcfresult);
				web.addCell(8, 3, tdcm.getTdmGrade(), web.wcfresult);
				web.addCell(9, 3, tdcm.getTdmClass(), web.wcfresult);
				web.addCell(10, 3, tdcs.get(5).getTxdcCount(), web.wcfresult);
				web.addCell(11, 3, "", web.wcfresult);
				web.addCell(12, 3, "", web.wcfresult);
				web.addCell(13, 3, "", web.wcfresult);
				web.addMergeCell(14, 3, 17, 3, tdcm.getTdmSchool(), web.wcfresult);
				/*-4-*/
				web.addMergeCell(0, 4, 6, 4, "L区", web.wcftitleBlue);
				web.addMergeCell(7, 4, 13, 4, "R区", web.wcftitleBlue);
				web.addMergeCell(14, 4, 17, 4, "追踪", web.wcftitleBlue);

				String zzName1 = "";// 成绩追踪名称
				String zzName2 = "";
				String zzName3 = "";

				int maxLine = 0;// 最大项数

				Double Lmax = 0.00;
				Double Lmin = 100.00;
				Double Rmax = 0.00;
				Double Rmin = 100.00;

				String Lcount1 = "";// L区小计第1-6项
				String Lcount2 = "";
				String Lcount3 = "";
				String Lcount4 = "";
				String Lcount5 = "";
				String Lcount6 = "";
				ArrayList<Double> listL = new ArrayList<Double>();// L区个人得分集合
				ArrayList<Double> listLcount = new ArrayList<Double>();// L区满分集合
				ArrayList<Double> listLcountAve = new ArrayList<Double>();// L区地区集合

				String Rcount1 = "";// R区小计第1-6项
				String Rcount2 = "";
				String Rcount3 = "";
				String Rcount4 = "";
				String Rcount5 = "";
				String Rcount6 = "";
				ArrayList<Double> listR = new ArrayList<Double>();// R区个人得分集合
				ArrayList<Double> listRcount = new ArrayList<Double>();// R区满分集合
				ArrayList<Double> listRcountAve = new ArrayList<Double>();// R区地区集合

				List<TxDataCount> tdcsL = new ArrayList<TxDataCount>();// L集合

				List<TxDataCount> tdcsR = new ArrayList<TxDataCount>();// R集合

				ArrayList<String[]> listZ = new ArrayList<String[]>();// 循环集合

				for (TxDataCount re : tdcs)
					if (re.getTxdcSide() != null && re.getTxdcSide().equals("L")) {
						tdcsL.add(re);
					} else if (re.getTxdcSide() != null && re.getTxdcSide().equals("R")) {
						tdcsR.add(re);
					}

				if (tdcsL.size() <= tdcsR.size()) {

					maxLine = tdcsR.size();

					for (int i = 0; i < tdcsR.size(); i++) {

						String[] num = new String[18];// 定义页面一行内容 共18项

						if (i < tdcsL.size()) {// L区 操作
							String[] o = tdcsL.get(i).getTxdcCount().split(";");
							
							if(o[1].equals("0")){
								num[0] = "";
								num[1] = "";
								num[2] = "";
								num[3] = "";
								num[4] = "";
								num[5] = "";
								num[6] = "";
							}else {
								num[0] = tdcsL.get(i).getTxdcName();
								num[1] = o[2];
								num[2] = o[4];
								num[3] = commonController.sub(o[2], o[4]);
								num[4] = o[0];
								num[5] = o[3];
								num[6] = o[1];
							}

							if (Double.valueOf(o[2]) > Lmax) {
								Lmax = Double.valueOf(o[2]);
							}
							if (Double.valueOf(o[2]) < Lmin) {
								Lmin = Double.valueOf(o[2]);
							}

							String[] k = tdcsR.get(i).getTxdcCount().split(";");
							
							if(k[1].equals("0")){
								num[7] = "";
								num[8] = "";
								num[9] = "";
								num[10] = "";
								num[11] = "";
								num[12] = "";
								num[13] = "";
							}else {
								num[7] = tdcsR.get(i).getTxdcName();
								num[8] = k[2];
								num[9] = k[4];
								num[10] = commonController.sub(k[2], k[4]);
								num[11] = k[0];
								num[12] = k[3];
								num[13] = k[1];
							}

							if (Double.valueOf(k[2]) > Rmax) {
								Rmax = Double.valueOf(k[2]);
							}
							if (Double.valueOf(k[2]) < Rmin) {
								Rmin = Double.valueOf(k[2]);
							}

							listZ.add(num);

							listL.add(Double.valueOf(o[0]));
							listLcount.add(Double.valueOf(o[1]));

							listR.add(Double.valueOf(k[0]));
							listRcount.add(Double.valueOf(k[1]));

							listLcountAve.add(Double.valueOf(o[4]));
							listRcountAve.add(Double.valueOf(k[4]));
							
							if(i == tdcsR.size()-1){
								String[] num1 = new String[18];

								listZ.add(num1);// 增加一行为追踪所用
							}

							continue;
						}

						String[] k = tdcsR.get(i).getTxdcCount().split(";");
						
						if(k[1].equals("0")){
							num[7] = "";
							num[8] = "";
							num[9] = "";
							num[10] = "";
							num[11] = "";
							num[12] = "";
							num[13] = "";
						}else {
							num[7] = tdcsR.get(i).getTxdcName();
							num[8] = k[2];
							num[9] = k[4];
							num[10] = commonController.sub(k[2], k[4]);
							num[11] = k[0];
							num[12] = k[3];
							num[13] = k[1];
						}

						if (Double.valueOf(k[2]) > Rmax) {
							Rmax = Double.valueOf(k[2]);
						}
						if (Double.valueOf(k[2]) < Rmin) {
							Rmin = Double.valueOf(k[2]);
						}

						listR.add(Double.valueOf(k[0]));
						listRcount.add(Double.valueOf(k[1]));
						listRcountAve.add(Double.valueOf(k[4]));

						listZ.add(num);

						String[] num1 = new String[18];

						listZ.add(num1);// 增加一行为追踪所用
					}

				} else if (tdcsL.size() > tdcsR.size()) {

					maxLine = tdcsL.size();

					for (int i = 0; i < tdcsL.size(); i++) {

						String[] num = new String[18];// 定义页面一行内容 共18项

						if (i < tdcsR.size()) {// R区 操作
							String[] o = tdcsL.get(i).getTxdcCount().split(";");
							
							if(o[1].equals("0")){
								num[0] = "";
								num[1] = "";
								num[2] = "";
								num[3] = "";
								num[4] = "";
								num[5] = "";
								num[6] = "";
							}else {
								num[0] = tdcsL.get(i).getTxdcName();
								num[1] = o[2];
								num[2] = o[4];
								num[3] = commonController.sub(o[2], o[4]);
								num[4] = o[0];
								num[5] = o[3];
								num[6] = o[1];
							}

							if (Double.valueOf(o[2]) > Lmax) {
								Lmax = Double.valueOf(o[2]);
							}
							if (Double.valueOf(o[2]) < Lmin) {
								Lmin = Double.valueOf(o[2]);
							}

							String[] k = tdcsR.get(i).getTxdcCount().split(";");
							
							if(k[1].equals("0")){
								num[7] = "";
								num[8] = "";
								num[9] = "";
								num[10] = "";
								num[11] = "";
								num[12] = "";
								num[13] = "";
							}else {
								num[7] = tdcsR.get(i).getTxdcName();
								num[8] = k[2];
								num[9] = k[4];
								num[10] = commonController.sub(k[2], k[4]);
								num[11] = k[0];
								num[12] = k[3];
								num[13] = k[1];
							}

							if (Double.valueOf(k[2]) > Rmax) {
								Rmax = Double.valueOf(k[2]);
							}
							if (Double.valueOf(k[2]) < Rmin) {
								Rmin = Double.valueOf(k[2]);
							}

							listZ.add(num);

							listL.add(Double.valueOf(o[0]));
							listLcount.add(Double.valueOf(o[1]));

							listR.add(Double.valueOf(k[0]));
							listRcount.add(Double.valueOf(k[1]));

							listLcountAve.add(Double.valueOf(o[4]));
							listRcountAve.add(Double.valueOf(k[4]));

							continue;
						}

						String[] o = tdcsL.get(i).getTxdcCount().split(";");
						
						if(o[1].equals("0")){
							num[0] = "";
							num[1] = "";
							num[2] = "";
							num[3] = "";
							num[4] = "";
							num[5] = "";
							num[6] = "";
						}else {
							num[0] = tdcsL.get(i).getTxdcName();
							num[1] = o[2];
							num[2] = o[4];
							num[3] = commonController.sub(o[2], o[4]);
							num[4] = o[0];
							num[5] = o[3];
							num[6] = o[1];
						}
						if (Double.valueOf(o[2]) > Lmax) {
							Lmax = Double.valueOf(o[2]);
						}
						if (Double.valueOf(o[2]) < Lmin) {
							Lmin = Double.valueOf(o[2]);
						}

						listL.add(Double.valueOf(o[0]));
						listLcount.add(Double.valueOf(o[1]));
						listLcountAve.add(Double.valueOf(o[4]));

						listZ.add(num);
					}
				}

				Lcount4 = commonController.numAll(listL);
				Rcount4 = commonController.numAll(listR);

				Lcount6 = commonController.numAll(listLcount);
				Rcount6 = commonController.numAll(listRcount);

				Lcount1 = commonController.avgNum(Lcount4, Lcount6);
				Rcount1 = commonController.avgNum(Rcount4, Rcount6);

				Lcount2 = commonController.numListAvg(listLcountAve);
				Rcount2 = commonController.numListAvg(listRcountAve);

				Lcount3 = commonController.sub(Lcount1, Lcount2);
				Rcount3 = commonController.sub(Rcount1, Rcount2);

				DefaultCategoryDataset dataset3 = new DefaultCategoryDataset();

				if (tdcm.getTdmSpare5() != null && !tdcm.getTdmSpare5().equals("")) {

					List<TxDataCountMain> tdcm3 = txDataCountMainService.queryTempThree(tdcm);

					/** 追踪第一列为所打开数据 **/

					zzName1 = commonController.changeYear(tdcm.getTdmYearseason());

					dataset3.addValue(Double.valueOf(tdcm.getTdmSpare3()).doubleValue(),
							commonController.changeYear(tdcm.getTdmYearseason()), "1");// 第一行固定百分位

					for (int i = 0; i <= tdcsR.size(); i++) {
						if (i == 0) {
							String[] a = listZ.get(i);
							a[14] = i+1+"百分位";
							a[15] = tdcm.getTdmSpare3();
							continue;
						}

						String[] k = tdcsR.get(i - 1).getTxdcCount().split(";");
						dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
								commonController.changeYear(tdcm.getTdmYearseason()), i+1+"");

						String[] b = listZ.get(i);
						b[14] = i+1+tdcsR.get(i - 1).getTxdcName();
						b[15] = k[6];

					}
					
					dataset3.addValue(Double.valueOf(stand1).doubleValue(), 
							commonController.changeYear(tdcm.getTdmYearseason()), tdcsR.size()+2+"");

					if (tdcm3.size() > 0) {

						int endLine = 2;// 数量计数 起始为2，到4终止,只取2个对象

						for (int i = 0; i < tdcm3.size(); i++) {

							if (endLine == 4) {
								break;
							}

							TxDataCountMain tdcmNew = tdcm3.get(i);

							if (tdcmNew.getTdmId().equals(tdcm.getTdmId())) {
								continue;
							}

							TxDataCount tdcNew = new TxDataCount();
							tdcNew.setTxdcMainid(tdcmNew.getTdmId());

							List<TxDataCount> tdcsNew = txDataCountService.queryTempList(tdcNew);

							Map<String, TxDataCount> mapR = new HashMap<String, TxDataCount>();

							for (TxDataCount reNew : tdcsNew)// R map集合
								if (reNew.getTxdcSide() != null && reNew.getTxdcSide().equals("R")) {
									mapR.put(reNew.getTxdcName(), reNew);
								}

							dataset3.addValue(Double.valueOf(tdcmNew.getTdmSpare3()).doubleValue(),
									commonController.changeYear(tdcmNew.getTdmYearseason()), "1");// 第一行固定百分位

							if (endLine == 2) {// 第2列数据构造
								zzName2 = commonController.changeYear(tdcmNew.getTdmYearseason());
								stand2 = tdcmNew.getTdmSpare6().split("\\|\\|")[2];

								for (int ii = 0; ii <= tdcsR.size(); ii++) {
									// 根据打开样本进行取数
									if (ii == 0) {
										String[] a = listZ.get(ii);
										a[16] = tdcmNew.getTdmSpare3();
										continue;
									}

									TxDataCount tdcMap = mapR.get(tdcsR.get(ii - 1).getTxdcName());// 根据样本名称取对象

									if (tdcMap == null) {// 无此对象 0.00补位

										dataset3.addValue(0.00, commonController.changeYear(tdcmNew.getTdmYearseason()),ii+1+"");
										String[] b = listZ.get(ii);
										b[16] = "0.00";

									} else {
										String[] k = tdcMap.getTxdcCount().split(";");
										dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
												commonController.changeYear(tdcmNew.getTdmYearseason()),ii+1+"");

										String[] b = listZ.get(ii);
										b[16] = k[6];
									}
								}
								dataset3.addValue(Double.valueOf(stand2).doubleValue(), 
										commonController.changeYear(tdcmNew.getTdmYearseason()), tdcsR.size()+2+"");
								
							} else if (endLine == 3) {// 第3列数据构造
								zzName3 = commonController.changeYear(tdcmNew.getTdmYearseason());
								stand3 = tdcmNew.getTdmSpare6().split("\\|\\|")[2];

								for (int ii = 0; ii <= tdcsR.size(); ii++) {
									// 根据打开样本进行取数
									if (ii == 0) {
										String[] a = listZ.get(ii);
										a[17] = tdcmNew.getTdmSpare3();
										continue;
									}

									TxDataCount tdcMap = mapR.get(tdcsR.get(ii - 1).getTxdcName());// 根据样本名称取对象

									if (tdcMap == null) {// 无此对象 0.00补位

										dataset3.addValue(0.00, commonController.changeYear(tdcmNew.getTdmYearseason()),
												ii+1+"");
										String[] b = listZ.get(ii);
										b[17] = "0.00";

									} else {
										String[] k = tdcMap.getTxdcCount().split(";");
										dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
												commonController.changeYear(tdcmNew.getTdmYearseason()),
												ii+1+"");

										String[] b = listZ.get(ii);
										b[17] = k[6];
									}
								}
								dataset3.addValue(Double.valueOf(stand3).doubleValue(), 
										commonController.changeYear(tdcmNew.getTdmYearseason()), tdcsR.size()+2+"");
								
							}
							endLine++;
						}

					}

				} else {// 无身份证信息则显示本次数据

					zzName1 = commonController.changeYear(tdcm.getTdmYearseason());

					dataset3.addValue(Double.valueOf(tdcm.getTdmSpare3()).doubleValue(),
							commonController.changeYear(tdcm.getTdmYearseason()), "1");// 第一行固定百分位

					for (int i = 0; i <= tdcsR.size(); i++) {
						// 数据集合(0得分;1满分;2得分率;3等级;4团队平均得分率;5标准偏差;6标准分)
						if (i == 0) {
							String[] a = listZ.get(i);
							a[14] = i+1+"百分位";
							a[15] = tdcm.getTdmSpare3();
							continue;
						}

						String[] k = tdcsR.get(i - 1).getTxdcCount().split(";");
						dataset3.addValue(Double.valueOf(k[6]).doubleValue(),
								commonController.changeYear(tdcm.getTdmYearseason()), i+1+"");

						String[] b = listZ.get(i);
						b[14] = i+1+tdcsR.get(i - 1).getTxdcName();
						b[15] = k[6];

					}
					dataset3.addValue(Double.valueOf(stand1).doubleValue(), 
							commonController.changeYear(tdcm.getTdmYearseason()), tdcsR.size()+2+"");

				}

				/*-5-*/
				web.addCell(0, 5, "维度内容", web.wcfcaptionOrange);
				web.addCell(1, 5, "个人得分率", web.wcfcaptionOrange);
				web.addCell(2, 5, "地区得分率", web.wcfcaptionOrange);
				web.addCell(3, 5, "差值", web.wcfcaptionOrange);
				web.addCell(4, 5, "选手得分", web.wcfcaptionOrange);
				web.addCell(5, 5, "等级", web.wcfcaptionOrange);
				web.addCell(6, 5, "满分值", web.wcfcaptionOrange);
				
				web.addCell(7, 5, "维度内容", web.wcfcaptionOrange);
				web.addCell(8, 5, "个人得分率", web.wcfcaptionOrange);
				web.addCell(9, 5, "地区得分率", web.wcfcaptionOrange);
				web.addCell(10, 5, "差值", web.wcfcaptionOrange);
				web.addCell(11, 5, "选手得分", web.wcfcaptionOrange);
				web.addCell(12, 5, "等级", web.wcfcaptionOrange);
				web.addCell(13, 5, "满分值", web.wcfcaptionOrange);
				
				web.addCell(14, 5, "", web.wcfcaptionOrange);
				web.addCell(15, 5, zzName1, web.wcfcaptionOrange);
				web.addCell(16, 5, zzName2, web.wcfcaptionOrange);
				web.addCell(17, 5, zzName3, web.wcfcaptionOrange);

				/*-6-*/
				/* 循环 */
				int line = 6;
				for (int i = 0; i < listZ.size(); i++) {

					web.addCellCaptionGreen(0, line, listZ.get(i)[0]);
					web.addCellResultYellow(1, line, listZ.get(i)[1]);
					web.addCell(2, line, listZ.get(i)[2], web.wcfresult);
					web.addCellResultYellow(3, line, listZ.get(i)[3]);
					web.addCell(4, line, listZ.get(i)[4], web.wcfresult);
					web.addCellResultYellow(5, line, listZ.get(i)[5]);
					web.addCell(6, line, listZ.get(i)[6], web.wcfresult);
					
					web.addCellCaptionGreen(7, line, listZ.get(i)[7]);
					web.addCellResultYellow(8, line, listZ.get(i)[8]);
					web.addCell(9, line, listZ.get(i)[9], web.wcfresult);
					web.addCellResultYellow(10, line, listZ.get(i)[10]);
					web.addCell(11, line, listZ.get(i)[11], web.wcfresult);
					web.addCellResultYellow(12, line, listZ.get(i)[12]);
					web.addCell(13, line, listZ.get(i)[13], web.wcfresult);
					
					web.addCellCaptionGreen(14, line, listZ.get(i)[14]);
					web.addCell(15, line, listZ.get(i)[15], web.wcfresult);
					web.addCellResultYellow(16, line, listZ.get(i)[16]);
					web.addCell(17, line, listZ.get(i)[17], web.wcfresult);

					line++;

				}

				/* 小计 line */
				web.addCell(0, line, "小计", web.wcfcaptionOrange);
				web.addCell(1, line, Lcount1, web.wcfcaptionOrange);
				web.addCell(2, line, Lcount2, web.wcfcaptionOrange);
				web.addCell(3, line, Lcount3, web.wcfcaptionOrange);
				web.addCell(4, line, Lcount4, web.wcfcaptionOrange);
				web.addCell(5, line, Lcount5, web.wcfcaptionOrange);
				web.addCell(6, line, Lcount6, web.wcfcaptionOrange);
				
				web.addCell(7, line, "小计", web.wcfcaptionOrange);
				web.addCell(8, line, Rcount1, web.wcfcaptionOrange);
				web.addCell(9, line, Rcount2, web.wcfcaptionOrange);
				web.addCell(10, line, Rcount3, web.wcfcaptionOrange);
				web.addCell(11, line, Rcount4, web.wcfcaptionOrange);
				web.addCell(12, line, Rcount5, web.wcfcaptionOrange);
				web.addCell(13, line, Rcount6, web.wcfcaptionOrange);
				
				web.addCell(14, line, line-5+"标准总分", web.wcfcaptionOrange);
				web.addCell(15, line, stand1, web.wcfcaptionOrange);
				web.addCell(16, line, stand2, web.wcfcaptionOrange);
				web.addCell(17, line, stand3, web.wcfcaptionOrange);

				/** 生成L雷达图 begin **/
				DefaultCategoryDataset dataset1 = (DefaultCategoryDataset) CalibrationSpiderWebPlotDemo
						.createDatasetFlx(tdcsL);

				JFreeChart chart1 = CalibrationSpiderWebPlotDemo.createChart(dataset1,0);

				String url1 = request.getSession().getServletContext().getRealPath("/")+"/images/";

				String fileName1 = barChart.newCreatChart(chart1, 250, 200, null, session, url1);

				String graphURL1 = request.getSession().getServletContext().getRealPath("/")+"/images/" + fileName1;

				/** 生成L雷达图 end **/

				/** 生成R雷达图 begin **/
				DefaultCategoryDataset dataset2 = (DefaultCategoryDataset) CalibrationSpiderWebPlotDemo
						.createDatasetFlx(tdcsR);

				JFreeChart chart2 = CalibrationSpiderWebPlotDemo.createChart(dataset2,1);

				String url2 = request.getSession().getServletContext().getRealPath("/")+"/images/";

				String fileName2 = barChart.newCreatChart(chart2, 250, 200, null, session, url2);

				String graphURL2 = request.getSession().getServletContext().getRealPath("/")+"/images/" + fileName2;

				/** 生成R雷达图 end **/

				/** 柱状图 **/
				// 创建主题样式
				StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
				// 设置标题字体
				standardChartTheme.setExtraLargeFont(new Font("黑体", Font.PLAIN, 20));
				// 设置图例的字体
				standardChartTheme.setRegularFont(new Font("黑体", Font.PLAIN, 20));
				// 设置轴向的字体
				standardChartTheme.setLargeFont(new Font("黑体", Font.PLAIN, 20));
				// 应用主题样式
				ChartFactory.setChartTheme(standardChartTheme);

				JFreeChart chart3 = ChartFactory.createBarChart("", "", "", dataset3, PlotOrientation.VERTICAL, true,
						false, false);

				chart3.setBackgroundPaint(Color.white);// 设定背景颜色

				// 得到图标中 plot 对象
				CategoryPlot plot = (CategoryPlot) chart3.getPlot();
				plot.setBackgroundAlpha(0.0f);// 设置背景颜色
				plot.setRangeGridlinePaint(Color.BLACK);// 设置横线颜色
				// 柱形属性
				BarRenderer br = (BarRenderer) plot.getRenderer();
				br.setShadowVisible(false);

				// y轴设置
				ValueAxis va = plot.getRangeAxis();
				va.setRange(0, 100);// 数据集
				// y轴刻度设置
				NumberAxis na = (NumberAxis) plot.getRangeAxis();
				na.setAutoTickUnitSelection(false);// 关闭自动分配
				NumberTickUnit ntu = new NumberTickUnit(20D);// 数据集分段单位
				na.setTickUnit(ntu);

				// x轴设置
				CategoryAxis ca = plot.getDomainAxis();
				ca.setTickLabelFont(new Font("黑体", Font.PLAIN, 13));// 设置x轴文字

				ca.setMaximumCategoryLabelLines(10);// 设置竖型排列
				ca.setMaximumCategoryLabelWidthRatio(0.5f);

				ca.setTickLabelPaint(Color.BLACK);

				String graphURL3 = request.getSession().getServletContext().getRealPath("/")+"/images/";

				String url3 = request.getSession().getServletContext().getRealPath("/")+"/images/";

				String fileName3 = barChart.newCreatChart(chart3, 300, 300, null, session, url3);

				/* 图形 line+1 */
				
				web.addPic(graphURL1, 0, line+1+1 , 6, 10);
				web.addPic(graphURL2, 6, line+1+1 , 6, 10);
				web.addPic(graphURL3 + fileName3, 12, line+1+1 , 6, 10);

				/* 综合分析 line+1+12 */
				String sr = "  请依据图表中各项知识点的[个人得分率][差值][等级]等项得失，及时查缺补漏。";
				web.addMergeCell(0, line + 1 + 12, 0, line + 1 + 12 + 10, "综合分析", web.wcftitle1);
				web.addMergeCell(1, line + 1 + 12, 17, line + 1 + 12 + 10, gen+sr, web.wcfcaptionSong);

				/* 专家建议 line+1+12+10+1 */

				web.addMergeCell(0, line + 1 + 12 + 10 + 1, 0, line + 1 + 12 + 10 + 1 + 10, "专家建议", web.wcftitle1);
				web.addMergeCell(1, line + 1 + 12 + 10 + 1, 17, line + 1 + 12 + 10 + 1 + 10, adv, web.wcfcaptionSong);

			}

			web.write();

			bl = true;
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			web.close();
		}
		return bl;
	}

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
			System.out.println(ex1);
			

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
