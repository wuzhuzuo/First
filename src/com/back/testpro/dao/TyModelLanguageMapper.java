package com.back.testpro.dao;

import java.util.List;

import com.back.testpro.model.TyModelLanguage;

public interface TyModelLanguageMapper {
    int deleteByPrimaryKey(String tymlId);

    int insert(TyModelLanguage record);

    int insertSelective(TyModelLanguage record);

    TyModelLanguage selectByPrimaryKey(String tymlId);

    int updateByPrimaryKeySelective(TyModelLanguage record);

    int updateByPrimaryKey(TyModelLanguage record);
    
    List<TyModelLanguage> queryTemp(TyModelLanguage record);
    
    int deleteByPrimaryKeys(String[] ids);
}