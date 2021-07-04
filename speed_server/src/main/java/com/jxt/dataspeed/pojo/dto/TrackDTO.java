package com.jxt.dataspeed.pojo.dto;

import lombok.Data;

@Data
public class TrackDTO {
    private Integer id;
    private String name;
    private String key;
    private Integer stars;
    private Boolean isNew;
    private Boolean isLeague;
    private Boolean isSea;
    private Boolean isOld;
    private String date;
    private String label;
    private Long dateLong;
    private Integer power;
}
