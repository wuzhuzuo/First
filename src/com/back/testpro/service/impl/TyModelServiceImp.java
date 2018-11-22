package com.back.testpro.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TyModelMapper;
import com.back.testpro.model.TyModel;
import com.back.testpro.service.TyModelService;

@Service
public class TyModelServiceImp implements TyModelService {
	
	@Autowired(required = true)
	private TyModelMapper tyModelMapper;



	public int insert(TyModel record) {
		// TODO Auto-generated method stub
		if(record.getTymId()==null || record.getTymId().equals("")){
			record.setTymId(UUID.randomUUID().toString());
		}
		return tyModelMapper.insert(record);
	}

	public int insertSelective(TyModel record) {
		// TODO Auto-generated method stub
		if(record.getTymId()==null || record.getTymId().equals("")){
			record.setTymId(UUID.randomUUID().toString());
		}
		return tyModelMapper.insertSelective(record);
	}

	public TyModel selectByPrimaryKey(String tymId) {
		// TODO Auto-generated method stub
		return tyModelMapper.selectByPrimaryKey(tymId);
	}

	public int updateByPrimaryKeySelective(TyModel record) {
		// TODO Auto-generated method stub
		return tyModelMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TyModel record) {
		// TODO Auto-generated method stub
		return tyModelMapper.updateByPrimaryKey(record);
	}

	public List<TyModel> queryTempList(TyModel record) {
		// TODO Auto-generated method stub
		return tyModelMapper.queryTempList(record);
	}

	public List<TyModel> queryView(TyModel record) {
		// TODO Auto-generated method stub
		return tyModelMapper.queryView(record);
	}

	public TyModel queryTemp(TyModel record) {
		// TODO Auto-generated method stub
		return tyModelMapper.queryTemp(record);
	}

	public int deleteByPrimaryKey(String tymId) {
		// TODO Auto-generated method stub
		return tyModelMapper.deleteByPrimaryKey(tymId);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return tyModelMapper.deleteByPrimaryKeys(ids);
	}

}
