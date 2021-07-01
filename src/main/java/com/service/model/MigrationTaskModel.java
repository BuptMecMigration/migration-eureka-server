package com.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MigrationTaskModel {

    private Integer userId;

    private Integer userLevel;

    private String destinationIP;

    private String destinationPort;

    private Integer taskId;

    private Integer taskLevel;

}
