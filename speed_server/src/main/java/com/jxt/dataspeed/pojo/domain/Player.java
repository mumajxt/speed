package com.jxt.dataspeed.pojo.domain;

import lombok.Data;

@Data
public class Player {
    private Double id;
    private String name;
    private Integer teamId;
    private Boolean isPro;
    private Integer power;
}
