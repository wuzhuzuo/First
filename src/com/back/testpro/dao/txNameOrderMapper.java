package com.back.testpro.dao;

import java.util.List;

import com.back.testpro.model.txNameOrder;

public interface txNameOrderMapper {
    int deleteByPrimaryKey(Integer txnoId);

    int insert(txNameOrder record);

    int insertSelective(txNameOrder record);

    txNameOrder selectByPrimaryKey(Integer txnoId);

    int updateByPrimaryKeySelective(txNameOrder record);

    int updateByPrimaryKey(txNameOrder record);
    
    List<txNameOrder> queryTempList(txNameOrder record);
    
    int deleteByPrimaryKeys(String[] ids);
}