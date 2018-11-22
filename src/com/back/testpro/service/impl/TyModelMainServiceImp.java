package com.back.testpro.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TyModelMainMapper;
import com.back.testpro.model.TyModelMain;
import com.back.testpro.service.TyModelMainService;

@Service
public class TyModelMainServiceImp implements TyModelMainService{
	
	@Autowired(required = true)
	private TyModelMainMapper tyModelMainMapper;

	public int deleteByPrimaryKey(String tymmId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(TyModelMain record) {
		// TODO Auto-generated method stub
		
		if(record.getTymmId()==null || record.getTymmId().equals("")){
			record.setTymmId(UUID.randomUUID().toString());
		}
		
		return tyModelMainMapper.insert(record);
	}

	public int insertSelective(TyModelMain record) {
		// TODO Auto-generated method stub
		
		if(record.getTymmId()==null || record.getTymmId().equals("")){
			record.setTymmId(UUID.randomUUID().toString());
		}
		
		return tyModelMainMapper.insert(record);
	}

	public TyModelMain selectByPrimaryKey(String tymmId) {
		// TODO Auto-generated method stub
		return tyModelMainMapper.selectByPrimaryKey(tymmId);
	}

	public int updateByPrimaryKeySelective(TyModelMain record) {
		// TODO Auto-generated method stub
		return tyModelMainMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TyModelMain record) {
		// TODO Auto-generated method stub
		return tyModelMainMapper.updateByPrimaryKey(record);
	}

	public List<TyModelMain> queryTempList(TyModelMain record) {
		// TODO Auto-generated method stub
		return tyModelMainMapper.queryTempList(record);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return tyModelMainMapper.deleteByPrimaryKeys(ids);
	}

}
