package com.back.testpro.dao;

import java.util.List;

import com.back.testpro.model.TxDataCount;

public interface TxDataCountMapper {
    int deleteByPrimaryKey(String txdcId);

    int insert(TxDataCount record);

    int insertSelective(TxDataCount record);

    TxDataCount selectByPrimaryKey(String txdcId);

    int updateByPrimaryKeySelective(TxDataCount record);

    int updateByPrimaryKey(TxDataCount record);
    
    List<TxDataCount> queryTempList(TxDataCount record);
    
    int deleteByPrimaryKeys(String[] ids);
}