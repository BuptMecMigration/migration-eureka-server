package com.dao;

import com.dataobject.ChainDO;

public interface ChainDOMapper {
    int deleteByPrimaryKey(Integer chainId);

    int insert(ChainDO record);

    int insertSelective(ChainDO record);

    ChainDO selectByPrimaryKey(Integer chainId);

    int updateByPrimaryKeySelective(ChainDO record);

    int updateByPrimaryKey(ChainDO record);
}