package com.jxt.dataspeed.pojo.dto;

import com.jxt.dataspeed.pojo.domain.Car;
import lombok.Data;

@Data
public class CarSkinDTO {
    Integer id;
    Car car;
    String name;
    private Integer power;
}
