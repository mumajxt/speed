package com.jxt.dataspeed.pojo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Donate {
    private Integer id;
    private String name;
    private Double money;
    private Date date;
}
