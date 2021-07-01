package com.service;

import com.error.BusinessException;
import com.service.model.ChainModel;

import java.io.IOException;

public interface ChainService {

    //链信息上传
    void chainAdd(ChainModel chainModel) throws BusinessException, IOException;

    //链信息更新
    void chainUpdate(ChainModel chainModel) throws BusinessException, IOException;

    //链信息查询
    ChainModel chainInfoQuery(Integer chainId) throws BusinessException, IOException;
}
