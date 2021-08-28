package com.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel implements Serializable {

    private Integer adminId;

    private String adminName;

    private String thirdpartyId;

    private String encrptPassword;
}
