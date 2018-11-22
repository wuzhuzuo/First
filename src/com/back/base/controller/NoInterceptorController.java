package com.back.base.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.back.base.AbstractEntity;
import com.back.base.model.TResource;
import com.back.base.pageModel.Company;
import com.back.base.pageModel.Login;
import com.back.base.pageModel.SessionInfo;
import com.back.base.service.CompanyService;
import com.back.base.service.LoginService;
import com.back.base.utils.ConfigUtil;
import com.back.base.utils.DateUtil;
import com.back.base.utils.IConstant;
import com.back.base.utils.MD5Util;
import com.google.code.kaptcha.Constants;

@Controller
@RequestMapping("/login")
public class NoInterceptorController extends BaseController{
	@Autowired(required = true)
	private LoginService loginService;
	
	
	@Autowired(required = true)
	private CompanyService companyService;

	/**
	 * 登陆
	 * 
	 * @param login
	 * @return
	 */
	@RequestMapping(params = "cmd=login")
	public String login(Login login, ModelMap model,String verifyCode, HttpSession session,HttpServletRequest request) {
		try {
			
			if(null != login.getUsername() && null != login.getPassword()){//判断是否录入用户名和密码
				login.setPassword(login.getPassword());				
				login = loginService.login(login);//获取用户信息
				if (null != login) {//获取用户信息成功
					login.setLogintime(DateUtil.Time2String(new Date()));//更新当前登录时间
					login.setLoginip(request.getRemoteHost());//获取登录IP地址
					login.setLoginFlag(true);//获取登录状态
					loginService.update(login);//更新登录信息
					SessionInfo sessionInfo = new SessionInfo();//创建session信息模型
					sessionInfo.setLogin(login);//赋值登录信息
					sessionInfo.setResourceMap(loginService.resourceList(login.getRoleId()));//获取用户权限菜单
					session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
					
					if(login.getDepartmentCode()==null || login.getDepartmentCode().equals("")){//判断所属部门
						
						Company company = companyService.find(login.getDepartmentId());//获取部门信息
						
						if(company !=null){//判断部门信息是否存在
							
							login.setDepartmentCode(company.getCode());//获取部门编号
						}
					}
					
					session.setAttribute("login", login);//页面传值
					
					return "redirect:/login/back/loginsucc.do"; //跳转方法
				} else {
					model.put("msg", "用户名,密码不正确或用户被禁用！");
					return "/backpage/base/login";
				}				
			}else{
				return "/backpage/base/login";
			}
		} catch (Exception e) {
			model.put("msg", e.getMessage());
			e.printStackTrace();
			return IConstant.ERROR_PAGE;
		}
	}
	
	
	@RequestMapping(value = "/back/loginsucc")
	public String loginSuccess() {
		
		return "backpage/base/index";
	}
	  
	
	@Override
	public String getOperateColumn(List<TResource> re, AbstractEntity ae) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getOperateButton(List<TResource> re,String[] params) {
		// TODO Auto-generated method stub
		return null;
	}


}
