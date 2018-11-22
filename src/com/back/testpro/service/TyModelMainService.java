package com.back.testpro.service;

import java.util.List;

import com.back.testpro.model.TyModelMain;

public interface TyModelMainService {
	
    int deleteByPrimaryKey(String tymmId);

    int insert(TyModelMain record);

    int insertSelective(TyModelMain record);

    TyModelMain selectByPrimaryKey(String tymmId);

    int updateByPrimaryKeySelective(TyModelMain record);

    int updateByPrimaryKey(TyModelMain record);
    
    List<TyModelMain> queryTempList(TyModelMain record);
    
    int deleteByPrimaryKeys(String[] ids);

}
