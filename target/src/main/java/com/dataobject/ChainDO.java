package com.dataobject;

public class ChainDO {

    private Integer chainId;

    private Integer serviceNum;

    private Integer updateuserid;

    private String addrMap;

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    public Integer getUpdateuserid() {
        return updateuserid;
    }

    public void setUpdateuserid(Integer updateuserid) {
        this.updateuserid = updateuserid;
    }

    public String getAddrMap() {
        return addrMap;
    }

    public void setAddrMap(String addrMap) {
        this.addrMap = addrMap == null ? null : addrMap.trim();
    }
}
