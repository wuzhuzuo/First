package com.back.testpro.dao;

import java.util.List;

import com.back.testpro.model.TsRawTy;

public interface TsRawTyMapper {
    int deleteByPrimaryKey(String tsrId);

    int insert(TsRawTy record);

    int insertSelective(TsRawTy record);

    TsRawTy selectByPrimaryKey(String tsrId);

    int updateByPrimaryKeySelective(TsRawTy record);

    int updateByPrimaryKey(TsRawTy record);
    
    List<TsRawTy> queryTemp(TsRawTy record);
}