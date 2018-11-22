package com.back.testpro.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TxDataCountMapper;
import com.back.testpro.model.TxDataCount;
import com.back.testpro.service.TxDataCountService;

@Service
public class TxDataCountServiceImp implements TxDataCountService{
	
	@Autowired(required = true)
	private TxDataCountMapper txDataCountMapper;

	public int deleteByPrimaryKey(String txdcId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(TxDataCount record) {
		// TODO Auto-generated method stub
		if(record.getTxdcId()==null || record.getTxdcId().equals("")){
			record.setTxdcId(UUID.randomUUID().toString());
		}
		return txDataCountMapper.insert(record);
	}

	public int insertSelective(TxDataCount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public TxDataCount selectByPrimaryKey(String txdcId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(TxDataCount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(TxDataCount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<TxDataCount> queryTempList(TxDataCount record) {
		// TODO Auto-generated method stub
		return txDataCountMapper.queryTempList(record);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return txDataCountMapper.deleteByPrimaryKeys(ids);
	}

}
