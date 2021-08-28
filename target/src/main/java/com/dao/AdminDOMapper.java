package com.dao;

import com.dataobject.AdminDO;

public interface AdminDOMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminDO record);

    int insertSelective(AdminDO record);

    AdminDO selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminDO record);

    int updateByPrimaryKey(AdminDO record);
}