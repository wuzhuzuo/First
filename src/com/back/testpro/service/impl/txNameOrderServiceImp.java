package com.back.testpro.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.txNameOrderMapper;
import com.back.testpro.model.txNameOrder;
import com.back.testpro.service.txNameOrderService;

@Service
public class txNameOrderServiceImp implements  txNameOrderService{
	
	@Autowired(required = true)
	private txNameOrderMapper txNameOrderMapper;
	

	public int deleteByPrimaryKey(Integer txnoId) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.deleteByPrimaryKey(txnoId);
	}

	public int insert(txNameOrder record) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.insert(record);
	}

	public int insertSelective(txNameOrder record) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.insertSelective(record);
	}

	public txNameOrder selectByPrimaryKey(Integer txnoId) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.selectByPrimaryKey(txnoId);
	}

	public int updateByPrimaryKeySelective(txNameOrder record) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(txNameOrder record) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.updateByPrimaryKey(record);
	}

	public List<txNameOrder> queryTempList(txNameOrder record) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.queryTempList(record);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return txNameOrderMapper.deleteByPrimaryKeys(ids);
	}

}
