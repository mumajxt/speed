package com.jxt.dataspeed.pojo.dto;

import lombok.Data;

@Data
public class PlayerDTO {
    private Double id;
    private String name;
    private Boolean isPro;
    private TeamDTO team;
    private Integer power;
}
