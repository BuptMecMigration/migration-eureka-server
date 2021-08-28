package com.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.dao.ChainDOMapper;
import com.dataobject.ChainDO;
import com.error.BusinessException;
import com.error.UserError;
import com.service.ChainService;
import com.service.model.ChainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChainServiceImpl implements ChainService {

    @Autowired
    private ChainDOMapper chainDOMapper;

    @Override
    public void chainAdd(ChainModel chainModel) throws BusinessException, IOException {
        if(null == chainModel){
            throw new BusinessException(UserError.CHAIN_UPDATE_ERROR);
        }

        if(chainModel.getServiceNum() <= 0 || null == chainModel.getAddrMap()) {
            //此处省去对于用户ID的判断
            throw new BusinessException(UserError.CHAIN_UPDATE_ERROR);
        }

        ChainDO chainDO = convertChain2DO(chainModel);

        chainDOMapper.updateByPrimaryKey(chainDO);
    }

    @Override
    public void chainUpdate(ChainModel chainModel) throws BusinessException {
        if(null == chainModel){
            throw new BusinessException(UserError.CHAIN_UPDATE_ERROR);
        }

        if(chainModel.getServiceNum() <= 0 || null == chainModel.getAddrMap()) {
            //此处省去对于用户ID的判断
            throw new BusinessException(UserError.CHAIN_UPDATE_ERROR);
        }

        ChainDO chainDO = convertChain2DO(chainModel);

        chainDOMapper.insertSelective(chainDO);
    }

    @Override
    public ChainModel chainInfoQuery(Integer chainId) throws BusinessException {
        if(chainId < 0){
            throw new BusinessException(UserError.CHAIN_QUERY_ERROR);
        }

        ChainDO chainDO = chainDOMapper.selectByPrimaryKey(chainId);

        return convertChain2Model(chainDO);
    }

    private ChainModel convertChain2Model(ChainDO chainDO) {
        if(null == chainDO){
            return null;
        }
        ChainModel chainModel = new ChainModel();
        chainModel.setChainId(chainDO.getChainId());
        chainModel.setServiceNum(chainDO.getServiceNum());
        chainModel.setUpdateuserid(chainDO.getUpdateuserid());
        //反序列化map
        chainModel.setAddrMap(deserilizableForMapFromFile(chainDO.getAddrMap(), String.class));
        return chainModel;
    }

    private ChainDO convertChain2DO(ChainModel chainModel) {
        if(null == chainModel){
            return null;
        }
        ChainDO chainDO = new ChainDO();
        chainDO.setChainId(chainModel.getChainId());
        chainDO.setServiceNum(chainModel.getServiceNum());
        chainDO.setUpdateuserid(chainModel.getUpdateuserid());
        //序列化map
        chainDO.setAddrMap(serilizableForMap(chainModel.getAddrMap()));
        return chainDO;
    }

    private static String serilizableForMap(Object objMap) {
        return JSON.toJSONString(objMap, true);// (maps,CityEntity.class);
    }

    /* 将json文件中的内容读取出来，反序列化为HashMap */
    private static <T, K> HashMap<K, T> deserilizableForMapFromFile(String mapString, Class<T> clazz) {
        Map<K, T> map = JSON.parseObject(mapString, new TypeReference<Map<K, T>>() { });
        return (HashMap<K, T>) map;
    }
}
