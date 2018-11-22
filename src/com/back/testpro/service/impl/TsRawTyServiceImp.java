package com.back.testpro.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TsRawTyMapper;
import com.back.testpro.model.TsRawTy;
import com.back.testpro.service.TsRawTyService;

@Service
public class TsRawTyServiceImp implements TsRawTyService{
	
	@Autowired(required = true)
	private TsRawTyMapper tsRawTyMapper;

	public int deleteByPrimaryKey(String tsrId) {
		// TODO Auto-generated method stub
		return tsRawTyMapper.deleteByPrimaryKey(tsrId);
	}

	public int insert(TsRawTy record) {
		// TODO Auto-generated method stub
		record.setTsrId(UUID.randomUUID().toString());
		return tsRawTyMapper.insert(record);
	}

	public int insertSelective(TsRawTy record) {
		// TODO Auto-generated method stub
		record.setTsrId(UUID.randomUUID().toString());
		return tsRawTyMapper.insertSelective(record);
	}

	public TsRawTy selectByPrimaryKey(String tsrId) {
		// TODO Auto-generated method stub
		return tsRawTyMapper.selectByPrimaryKey(tsrId);
	}

	public int updateByPrimaryKeySelective(TsRawTy record) {
		// TODO Auto-generated method stub
		return tsRawTyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TsRawTy record) {
		// TODO Auto-generated method stub
		return tsRawTyMapper.updateByPrimaryKey(record);
	}

	public List<TsRawTy> queryTemp(TsRawTy record) {
		// TODO Auto-generated method stub
		return tsRawTyMapper.queryTemp(record);
	}

}
