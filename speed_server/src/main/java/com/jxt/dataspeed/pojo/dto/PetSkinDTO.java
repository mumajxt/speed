package com.jxt.dataspeed.pojo.dto;

import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.domain.PetSkin;
import lombok.Data;

@Data
public class PetSkinDTO {
    private Integer id;
    private String name;
    private Pet pet;
    private Integer power;
}
