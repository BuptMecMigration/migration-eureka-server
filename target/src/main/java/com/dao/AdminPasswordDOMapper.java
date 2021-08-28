package com.dao;

import com.dataobject.AdminPasswordDO;

public interface AdminPasswordDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminPasswordDO record);

    int insertSelective(AdminPasswordDO record);

    AdminPasswordDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminPasswordDO record);

    int updateByPrimaryKey(AdminPasswordDO record);
}