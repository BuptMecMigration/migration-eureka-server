package com.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChainModel implements Serializable {

    private static final long serialVersionUID = 2724888087391664167L;

    private Integer chainId;

    private Integer serviceNum;

    private Integer updateuserid;

    private HashMap<Integer, String> addrMap;
}
