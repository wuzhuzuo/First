package com.back.base.service;

import java.util.List;
import java.util.Map;

import com.back.base.pageModel.Login;

//定义公共结构 LoginService
public interface LoginService {
	//public公共的  
	//void无返回参数  List<>带有范型的列表  Map<>带有范型的集合
	//方法定义：名称save，()中为传入参数Login对象。下同
	public void save(Login login);

	public List<Login> list(Login login);

	public Login find(String id);
	
	public void update(Login login);
	
	public void deleteByPrimaryKeys(String ids);

	public Login login(Login login);

	public void relationLogin(String roleId,String loginIds);

	public List<Login> selectByRoleId(String roleId);
	
	public List<Login> selectNoRelationByRoleId(Login login,String roleId);

	public void deleteRelation(String ids);

	public Login saveOrUpdate(Login login);
	
	public void pwdChange(String id,String pwd);

	public void statusChange(String id);
	
	public Map<String,List<String>> resourceList(String roleId);

}
