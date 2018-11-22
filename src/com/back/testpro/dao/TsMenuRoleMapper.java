package com.back.testpro.dao;

import com.back.testpro.model.TsMenuRole;

public interface TsMenuRoleMapper {
    int deleteByPrimaryKey(String mrId);

    int insert(TsMenuRole record);

    int insertSelective(TsMenuRole record);

    TsMenuRole selectByPrimaryKey(String mrId);

    int updateByPrimaryKeySelective(TsMenuRole record);

    int updateByPrimaryKey(TsMenuRole record);
}