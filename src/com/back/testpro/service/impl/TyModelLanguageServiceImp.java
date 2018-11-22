package com.back.testpro.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.back.testpro.dao.TyModelLanguageMapper;
import com.back.testpro.model.TyModelLanguage;
import com.back.testpro.service.TyModelLanguageService;

@Service
public class TyModelLanguageServiceImp implements TyModelLanguageService{

	@Autowired(required = true)
	private TyModelLanguageMapper tyModelLanguageMapper;
	
	
	public int deleteByPrimaryKey(String tymlId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insert(TyModelLanguage record) {
		// TODO Auto-generated method stub
		
		if(record.getTymlId()==null || record.getTymlId().equals("")){
			record.setTymlId(UUID.randomUUID().toString());
		}
		
		return tyModelLanguageMapper.insert(record);
	}

	public int insertSelective(TyModelLanguage record) {
		// TODO Auto-generated method stub
		if(record.getTymlId()==null || record.getTymlId().equals("")){
			record.setTymlId(UUID.randomUUID().toString());
		}
		
		return tyModelLanguageMapper.insertSelective(record);
	}

	public TyModelLanguage selectByPrimaryKey(String tymlId) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(TyModelLanguage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(TyModelLanguage record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<TyModelLanguage> queryTemp(TyModelLanguage record) {
		// TODO Auto-generated method stub
		return tyModelLanguageMapper.queryTemp(record);
	}

	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return tyModelLanguageMapper.deleteByPrimaryKeys(ids);
	}

}
