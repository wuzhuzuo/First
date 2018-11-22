package com.back.testpro.dao;

import java.util.List;

import com.back.testpro.model.TxDataCountMain;

public interface TxDataCountMainMapper {
    int deleteByPrimaryKey(String tdmId);

    int insert(TxDataCountMain record);

    int insertSelective(TxDataCountMain record);

    TxDataCountMain selectByPrimaryKey(String tdmId);

    int updateByPrimaryKeySelective(TxDataCountMain record);

    int updateByPrimaryKey(TxDataCountMain record);
    
    List<TxDataCountMain> queryTemp(TxDataCountMain record);
    
    List<TxDataCountMain> queryTempThree(TxDataCountMain record);
    
    int deleteByPrimaryKeys(String[] ids);
}