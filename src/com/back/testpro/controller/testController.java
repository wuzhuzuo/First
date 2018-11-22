package com.back.testpro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back.base.AbstractEntity;
import com.back.base.controller.BaseController;
import com.back.base.model.TResource;
import com.back.base.page.PageContext;
import com.back.base.utils.DateUtil;
import com.back.testpro.model.TsRawTy;
import com.back.testpro.model.TyModel;
import com.back.testpro.model.TyModelLanguage;
import com.back.testpro.model.TyModelMain;
import com.back.testpro.service.TsRawTyService;
import com.back.testpro.service.TyModelLanguageService;
import com.back.testpro.service.TyModelMainService;
import com.back.testpro.service.TyModelService;
import com.back.testpro.utils.IConstant;

@Controller
public class testController extends BaseController {

	@Autowired(required = true)
	private TsRawTyService tsRawTyService;

	@Autowired(required = true)
	private TyModelService tyModelService;
	
	@Autowired(required = true)
	private TyModelMainService tyModelMainService;
	
	@Autowired(required = true)
	private TyModelLanguageService tyModelLanguageService;

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

	/*
	 * 遍历初始模板
	 */
	@RequestMapping(value = "/back/test_test")
	public String countTest(TsRawTy tsr, ModelMap model) {

		String id = request.getParameter("id");

		tsr.setTsrNum(Integer.parseInt(id));

		List<TsRawTy> tsrs = tsRawTyService.queryTemp(tsr);

		String[] Cid = null;

		String[] Cid1 = null;

		ArrayList<String[]> arraylist = new ArrayList<String[]>();

		if (tsrs.size() > 0) {
			for (int i = 0; i < tsrs.size(); i++) {

				TsRawTy tsr1 = tsrs.get(i);

				Cid = tsr1.getTsrContent().split(";");

			}
		}

		if (Cid.length > 0) {
			for (int i = 0; i < Cid.length; i++) {

				String s = Cid[i];

				Cid1 = s.split("-");

				arraylist.add(Cid1);

			}

		}

		model.put("arraylist", arraylist);

		model.put("id", id);

		return "backpage/testpro/test/edit";

	}

	/*
	 * 保存模板
	 */
	@RequestMapping(value = "/back/test_testadd")
	public String countAdd(ModelMap model) {
		
		String MainId = UUID.randomUUID().toString();//制定主键ID  子表的spare2 字段

		String id = request.getParameter("id");

		String[] obja = request.getParameterValues("obja");// name

		String[] objb = request.getParameterValues("objb");// flag

		String[] values = request.getParameterValues("values");
		
		String[] sides = request.getParameterValues("side");
		
		String[] zhlevelsA = request.getParameterValues("zhlevelsA");
		String[] zhlevelsB = request.getParameterValues("zhlevelsB");
		String[] zhlevelsC = request.getParameterValues("zhlevelsC");
		String[] zhlevelsD = request.getParameterValues("zhlevelsD");
		
		String[] zjlevelsA = request.getParameterValues("zjlevelsA");
		String[] zjlevelsB = request.getParameterValues("zjlevelsB");
		String[] zjlevelsC = request.getParameterValues("zjlevelsC");
		String[] zjlevelsD = request.getParameterValues("zjlevelsD");
		
		
		String titlename = request.getParameter("titlename");

		if (obja != null && objb != null && obja.length != objb.length) {
			model.put("href", "test_test.do?id=" + id);
			model.put("msg", "数据信息有误！ " + obja.length + " and " + objb.length);
			return IConstant.ERROR_PAGE;
		}
		
		TyModelMain tmMain = new TyModelMain();//先保存主表
		tmMain.setTymmId(MainId);
		tmMain.setTymmName(titlename);
		tmMain.setTymmSpare1(id);//项目代码
		tmMain.setTymmTime(DateUtil.Time2String(new Date()));
		tyModelMainService.insert(tmMain);
		
		int fulid = 0;
		for (int i = 0; i < obja.length; i++) {
			TyModel tm = new TyModel();//第一个子表
			tm.setTymName(obja[i]);
			tm.setTymOrder(i);// 排序
			tm.setTymFlag(Integer.parseInt(objb[i]));
			tm.setTymSpare1(id);
			tm.setTymSpare2(MainId);
			
			
			TyModelLanguage tml_zh = new TyModelLanguage();//第二个子表 综合分析集合 flag=0
			TyModelLanguage tml_zj = new TyModelLanguage();//第二个子表 专家建议集合 flag=1
			

			if (Integer.parseInt(objb[i]) == 1) {
				tm.setTymFuldata(values[fulid]);
				tm.setTymSide(sides[fulid]);
				
				tml_zh.setTymlMainid(MainId);
				tml_zh.setTymlName(obja[i]);
				tml_zh.setTymlOrder(i);
				tml_zh.setTymlFlag(0);
				tml_zh.setTymlLevel1(zhlevelsA[fulid]);
				tml_zh.setTymlLevel2(zhlevelsB[fulid]);
				tml_zh.setTymlLevel3(zhlevelsC[fulid]);
				tml_zh.setTymlLevel4(zhlevelsD[fulid]);
				
				
				tml_zj.setTymlMainid(MainId);
				tml_zj.setTymlName(obja[i]);
				tml_zj.setTymlOrder(i);
				tml_zj.setTymlFlag(1);
				tml_zj.setTymlLevel1(zjlevelsA[fulid]);
				tml_zj.setTymlLevel2(zjlevelsB[fulid]);
				tml_zj.setTymlLevel3(zjlevelsC[fulid]);
				tml_zj.setTymlLevel4(zjlevelsD[fulid]);
				
				fulid++;
				
				tyModelLanguageService.insert(tml_zh);
				
				tyModelLanguageService.insert(tml_zj);
			}
			


			tyModelService.insert(tm);

		}

		model.put("id", id);

		return "redirect:/back/test_testlist.do?id="+id;

	}

	/*
	 * 模板列表
	 */
	@RequestMapping(value = "/back/test_testlist")
	public String countList(TyModelMain tmMain, ModelMap model) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(true);// 分页状态
		
		String re = getLoginSession().getDepartmentName();
		String flag = "0";
		
		if(re.equals("教育机构")){//权限判定
			flag = "1";
		};
		model.put("flag",flag);

		String id = request.getParameter("id");

		tmMain.setTymmSpare1(id);
		
		List<TyModelMain> tms = tyModelMainService.queryTempList(tmMain);

		model.put("tms", tms);

		model.put("id", id);
		
		model.put("page", page);

		return "backpage/testpro/test/list";
	}

	/*
	 * 模板显示页面
	 */
	@RequestMapping(value = "/back/test_testUpdate")
	public String countUpdate(TyModel tm, ModelMap model) {
		
		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态 是否分页
		model.put("page",page);
		
		String re = getLoginSession().getDepartmentName();
		String flag = "0";
		
		if(re.equals("教育机构")){//权限判定
			flag = "1";
		};
		model.put("flag",flag);

		String id = request.getParameter("id");
		
		String tymmId = request.getParameter("tymmId");
		
		TyModelMain tmm = tyModelMainService.selectByPrimaryKey(tymmId);
		
		tm.setTymSpare2(tymmId);

		List<TyModel> tms = tyModelService.queryView(tm);
		
		TyModelLanguage tml = new TyModelLanguage();
		tml.setTymlMainid(tymmId);
		List<TyModelLanguage> tmls = tyModelLanguageService.queryTemp(tml);
		
		model.put("tmm", tmm);

		model.put("tms", tms);
		
		model.put("tmls", tmls);

		model.put("id", id);
		
		model.put("tymmId", tymmId);

		return "backpage/testpro/test/update";
	}

	/*
	 * 模板保存更新页面
	 */
	@RequestMapping(value = "/back/test_testSave")
	public String countSave(TyModelMain tmm, ModelMap model) {

		String id = request.getParameter("id");
		
		tmm.setTymmTime(DateUtil.Time2String(new Date()));
		
		tyModelMainService.updateByPrimaryKeySelective(tmm);//先更新主表
		
		
		
		String[] tymNames = request.getParameterValues("tymNames");
		String[] tymFlags = request.getParameterValues("tymFlags");
		String[] tymFuldatas = request.getParameterValues("tymFuldatas");
		String[] tymOrders = request.getParameterValues("tymOrders");
		String[] sides = request.getParameterValues("side");
		String[] tymSpare1s = request.getParameterValues("tymSpare1s");

		
		String[] zhlevelsA = request.getParameterValues("zhlevelsA");
		String[] zhlevelsB = request.getParameterValues("zhlevelsB");
		String[] zhlevelsC = request.getParameterValues("zhlevelsC");
		String[] zhlevelsD = request.getParameterValues("zhlevelsD");
		
		String[] zjlevelsA = request.getParameterValues("zjlevelsA");
		String[] zjlevelsB = request.getParameterValues("zjlevelsB");
		String[] zjlevelsC = request.getParameterValues("zjlevelsC");
		String[] zjlevelsD = request.getParameterValues("zjlevelsD");

		if (tymNames != null && tymFlags != null && tymOrders != null && tymSpare1s != null 
				&& tymNames.length != tymFlags.length 
				&& tymOrders.length != tymSpare1s.length 
				&& tymNames.length != tymSpare1s.length ) {
			model.put("href", "test_test.do?id=" + id);
			model.put("msg", "数据信息有误！ ");
			return IConstant.ERROR_PAGE;
		}
		
		
		TyModel tm = new TyModel();
		
		tm.setTymSpare2(tmm.getTymmId());

		List<TyModel> tms = tyModelService.queryView(tm);//第一个子表
		
		String[] s = tmm.getTymmId().split(";");

		if (tms != null || tms.size()>0) {

			tyModelService.deleteByPrimaryKeys(s);// 先删除

		}
		
		
		TyModelLanguage tml = new TyModelLanguage();//第二个子表
		
		tml.setTymlMainid(tmm.getTymmId());
		
		List<TyModelLanguage> tmls = tyModelLanguageService.queryTemp(tml);
		
		if (tmls != null || tmls.size()>0) {

			tyModelLanguageService.deleteByPrimaryKeys(s);// 先删除

		}
		
		
		int fulid = 0;
		for (int i = 0; i < tymNames.length; i++) {
			TyModel tm1 = new TyModel();//第一个子表
			tm1.setTymName(tymNames[i]);
			tm1.setTymOrder(i);// 排序
			tm1.setTymFlag(Integer.parseInt(tymFlags[i]));
			tm1.setTymSpare1(id);
			tm1.setTymSpare2(tmm.getTymmId());
			
			
			TyModelLanguage tml_zh = new TyModelLanguage();//第二个子表 分析集合 flag=0
			TyModelLanguage tml_zj = new TyModelLanguage();//第二个子表 建议集合 flag=1
			

			if (Integer.parseInt(tymFlags[i]) == 1) {
				tm1.setTymFuldata(tymFuldatas[fulid]);
				tm1.setTymSide(sides[fulid]);
				
				tml_zh.setTymlMainid(tmm.getTymmId());
				tml_zh.setTymlName(tymNames[i]);
				tml_zh.setTymlOrder(i);
				tml_zh.setTymlFlag(0);
				tml_zh.setTymlLevel1(zhlevelsA[fulid]);
				tml_zh.setTymlLevel2(zhlevelsB[fulid]);
				tml_zh.setTymlLevel3(zhlevelsC[fulid]);
				tml_zh.setTymlLevel4(zhlevelsD[fulid]);
				
				
				tml_zj.setTymlMainid(tmm.getTymmId());
				tml_zj.setTymlName(tymNames[i]);
				tml_zj.setTymlOrder(i);
				tml_zj.setTymlFlag(1);
				tml_zj.setTymlLevel1(zjlevelsA[fulid]);
				tml_zj.setTymlLevel2(zjlevelsB[fulid]);
				tml_zj.setTymlLevel3(zjlevelsC[fulid]);
				tml_zj.setTymlLevel4(zjlevelsD[fulid]);
				
				tyModelLanguageService.insert(tml_zh);
				
				tyModelLanguageService.insert(tml_zj);
				
				fulid++;
			}

			tyModelService.insert(tm1);

		}


		model.put("id", id);

		return "redirect:/back/test_testlist.do?id="+id;
	}
	
	
	
	/*
	 * 
	 * 删除
	 */
	@RequestMapping(value = "/back/test_testDelete")
	public String countdelete(TyModelMain tmm, ModelMap model) {

		String mId = request.getParameter("mId");

		String id = request.getParameter("id");
		
		String[] Cid = null;

		if (mId != "" || mId.length() > 0) {

			String Countid = mId.replaceAll(",", ";");

			Cid = Countid.split(";");
		}
		
		tyModelLanguageService.deleteByPrimaryKeys(Cid);// 删除子表
		
		tyModelService.deleteByPrimaryKeys(Cid);// 删除子表
		
		tyModelMainService.deleteByPrimaryKeys(Cid);//删除主表


		model.put("id", id);

		return "redirect:/back/test_testlist.do?id="+id;
	}


}
