package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.Car;
import com.jxt.dataspeed.pojo.domain.CarSkin;
import com.jxt.dataspeed.pojo.domain.Ecu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CarMapper {

    /*
    赛车
     */
    int saveCar(Car car);
    int deleteCar(int carId);
    int updateCar(Car car);
    List<Car> listCars();
    List<Car> listCarsByParams(@Param("type") String type,
                               @Param("pet") String pet,
                               @Param("ecu")String ecu);
    List<Car> listCarsByCarRank(String carRank);
    Car getCarById(int carId);
    Car getCarByName(String carName);

    int increaseCarPower(int carId);


    /*
    赛车皮肤
     */
    int saveCarSkin(CarSkin carSkin);
    int deleteCarSkin(int carSkinId);
    int updateCarSkin(CarSkin carSkin);
    List<CarSkin> listCarSkinsByCarId(int carId);
    List<CarSkin> listCarSkins();
    CarSkin getCarSkinById(int carSkinId);
    int increaseCarSkinPower(int carSkinId);


    /*
    ECU
     */
    int saveEcu(Ecu ecu);
    int deleteEcu(int ecuId);
    int updateEcu(Ecu ecu);
    List<Ecu> listEcus();
    Ecu getEcuById(int ecuId);
    Ecu getEcuByName(String ecuName);
    int increaseEcuPower(int ecuId);


}
