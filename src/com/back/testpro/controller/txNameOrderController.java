package com.back.testpro.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import com.back.testpro.model.txNameOrder;
import com.back.testpro.service.txNameOrderService;
import com.back.testpro.utils.IConstant;

@Controller
public class txNameOrderController extends BaseController {

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
	private txNameOrderService txNameOrderService;
	
	@Autowired(required = true)
	private commonController commonController;

	/*
	 * 姓名序号列表
	 */
	@RequestMapping(value = "/back/txno_list")
	public String countList(txNameOrder txno, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(true);// 分页状态

		String re = getLoginSession().getDepartmentName();
		String flag = "0";

		if (re.equals("教育机构")) {// 权限判定
			flag = "1";
		}
		;
		model.put("flag", flag);

		List<txNameOrder> txnos = txNameOrderService.queryTempList(txno);

		model.put("txnos", txnos);

		model.put("page", page);

		return "backpage/testpro/nameOrder/list";
	}

	/*
	 * 姓名序号新增
	 */
	@RequestMapping(value = "/back/txno_add")
	public String countadd(txNameOrder txno, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		model.put("page", page);

		model.put("txno", txno);

		if (txno.getTxnoName() == null || txno.getTxnoName().equals("")) {

			return "backpage/testpro/nameOrder/edit";

		}

		txNameOrderService.insertSelective(txno);

		return "redirect:/back/txno_list.do";

	}

	/*
	 * 姓名序号删除
	 */
	@RequestMapping(value = "/back/txno_Delete")
	public String countDelete(txNameOrder txno, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		String mId = request.getParameter("mId");

		String[] Cid = null;

		if (mId != "" || mId.length() > 0) {

			String Countid = mId.replaceAll(",", ";");

			Cid = Countid.split(";");
		}

		txNameOrderService.deleteByPrimaryKeys(Cid);

		return "redirect:/back/txno_list.do";

	}

	/*
	 * 姓名序号更新
	 */
	@RequestMapping(value = "/back/txno_update")
	public String countUpdate(txNameOrder txno, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态
		model.put("page", page);

		String re = getLoginSession().getDepartmentName();
		String flag = "0";

		if (re.equals("教育机构")) {// 权限判定
			flag = "1";
		}
		;
		model.put("flag", flag);

		String id = request.getParameter("id");

		if (id != null) {

			txno = txNameOrderService.selectByPrimaryKey(Integer.valueOf(id));

			model.put("txno", txno);

			return "backpage/testpro/nameOrder/update";
		}

		txNameOrderService.updateByPrimaryKey(txno);

		return "redirect:/back/txno_list.do";

	}

	/*
	 * 姓名序号上传页面
	 */
	@RequestMapping(value = "/back/txno_upload")
	public String countUpload(txNameOrder txno, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		model.put("page", page);

		return "backpage/testpro/nameOrder/upload";

	}

	/*
	 * 姓名序号上传保存
	 */
	@SuppressWarnings({ "unchecked", "resource" })
	@RequestMapping(value = "/back/txno_uploadOn")
	public String countUploadOn(@RequestParam("excelFile") MultipartFile file, ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		model.put("page", page);

		int iRowNum = 0;// 工作表中的行数量
		int iCellNum = 0;// 每行中的列数量

		try {

			POIFSFileSystem fs = new POIFSFileSystem(file.getInputStream());

			HSSFWorkbook wb = new HSSFWorkbook(fs);// xls 获取工作簿对象

			HSSFSheet sheet = wb.getSheetAt(0);// 获取工作簿中第一个sheet表

			iRowNum = sheet.getPhysicalNumberOfRows();// 获取sheet表中总行数
			
			ArrayList<txNameOrder> txnos = new ArrayList<txNameOrder>();
			
			/** 开始读取行数据 */

			int startRowNum = 1;// excel 1含标题 0不含标题
			
			if (iRowNum == 0) {
				model.put("href", "txno_list.do");
				model.put("msg", "数据错误请核对！");
				return IConstant.ERROR_PAGE;
			}
		
			for (int j = startRowNum; j < iRowNum; j++) {
				HSSFRow rowTmp = sheet.getRow(j);
				if (rowTmp == null) {// 判断是否为空行
					continue;
				}
				if (j == startRowNum) {
					iCellNum = rowTmp.getLastCellNum();

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
				
				txNameOrder txno = new txNameOrder();
				txno.setTxnoName(aValues[0]);
				txno.setTxnoSpare1(aValues[1]);
				txno.setTxnoRemark(aValues[2]);				
				txnos.add(txno);
			}
			/** 结束读取每行中每个单元格数据 */
			
			if(txnos.size() > 0){
				
				for (int i = 0; i < txnos.size(); i++) {
					
					txNameOrder txno = txnos.get(i);
					
					txNameOrderService.insertSelective(txno);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "txno_list.do");
			model.put("msg", "Excel格式错误，请选择正确的模板文件！");
			return IConstant.ERROR_PAGE;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "txno_list.do");
			model.put("msg", "Excel格式错误，请选择正确的模板文件！");
			return IConstant.ERROR_PAGE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.put("href", "txno_list.do");
			model.put("msg", "Excel格式错误，请选择正确的模板文件！");
			return IConstant.ERROR_PAGE;
		}

		return "redirect:/back/txno_list.do";

	}
	
	
	/*
	 * 帮助页面
	 */
	@RequestMapping(value = "/back/help_list")
	public String helpList(ModelMap model) {

		PageContext page = PageContext.getContext(request, rowPerPage);// 获得分页标签
		page.setPagination(false);// 分页状态

		return "backpage/testpro/help/list";

	}

}
