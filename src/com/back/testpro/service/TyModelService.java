package com.back.testpro.service;

import java.util.List;

import com.back.testpro.model.TyModel;

public interface TyModelService {
	
	int deleteByPrimaryKey(String tymId);

    int insert(TyModel record);

    int insertSelective(TyModel record);

    TyModel selectByPrimaryKey(String tymId);

    int updateByPrimaryKeySelective(TyModel record);

    int updateByPrimaryKey(TyModel record);
    
    List<TyModel> queryTempList(TyModel record);
    
    List<TyModel> queryView(TyModel record);
    
    TyModel queryTemp(TyModel record);
    
    int deleteByPrimaryKeys(String[] ids);

}
