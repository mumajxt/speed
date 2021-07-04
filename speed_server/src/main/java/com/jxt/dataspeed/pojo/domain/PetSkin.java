package com.jxt.dataspeed.pojo.domain;

import lombok.Data;

@Data
public class PetSkin {
    Integer id;
    String name;
    Integer petId;
    private Integer power;
}
