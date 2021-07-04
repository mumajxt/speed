package com.jxt.dataspeed.service;

import com.jxt.dataspeed.mapper.CarMapper;
import com.jxt.dataspeed.pojo.domain.Car;
import com.jxt.dataspeed.pojo.domain.CarSkin;
import com.jxt.dataspeed.pojo.domain.Ecu;
import com.jxt.dataspeed.pojo.domain.Player;
import com.jxt.dataspeed.pojo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;


    /**
     增
     */
    public Integer saveCar(Car car) {
        int i = carMapper.saveCar(car);
        if (i==1){
            return car.getId();
        }else {
            return null;
        }
    }
    public Integer saveCarSkin(CarSkin carSkin){
        double i = carMapper.saveCarSkin(carSkin);
        if (i==1){
            return carSkin.getId();
        }else {
            return null;
        }
    }
    public Integer saveEcu(Ecu ecu) {
        double i = carMapper.saveEcu(ecu);
        if (i==1){
            return ecu.getId();
        }else {
            return null;
        }
    }

    /**
     * 删
     */
    public boolean deleteCar(int id) {
        return carMapper.deleteCar(id)>0;
    }

    public boolean deleteCarSkin(int id) {
        return carMapper.deleteCarSkin(id)>0;
    }

    public boolean deleteEcu(int id) {
        return carMapper.deleteEcu(id)>0;
    }


    /**
     * 改
     */
    public boolean updateCar(Car car) {
        return carMapper.updateCar(car)>0;
    }
    public boolean updateCarSkin(CarSkin carSkin) {
        return carMapper.updateCarSkin(carSkin)>0;
    }
    public boolean updateEcu(Ecu ecu) {
        return carMapper.updateEcu(ecu)>0;
    }

    /**
     * 查
     */
    public List<CarDTO> listCarDTOs() {
        List<CarDTO> carDTOs = new LinkedList<>();
        List<Car> cars = carMapper.listCars();
        for (Car car : cars) {
            carDTOs.add(getCarDTO(car));
        }
        return carDTOs;
    }
    public List<CarDTO> listCarDTOsByParams(String type, String pet, String ecu) {
        List<CarDTO> carDTOs = new LinkedList<>();
        List<Car> cars = carMapper.listCarsByParams(type,pet,ecu);
        for (Car car : cars) {
            carDTOs.add(getCarDTO(car));
        }
        return carDTOs;
    }
    public List<CarSkinDTO> listCarSkinDTOs(){
        List<CarSkinDTO> carSkinDTOs = new LinkedList<>();
        List<CarSkin> carSkins = carMapper.listCarSkins();
        for (CarSkin carSkin : carSkins) {
            carSkinDTOs.add(getCarSkinDTO(carSkin));
        }
        return carSkinDTOs;
    }
    public List<EcuDTO> listEcuDTOs() {
        List<EcuDTO> ecuDTOS = new LinkedList<>();
        List<Ecu> ecus = carMapper.listEcus();
        for (Ecu ecu : ecus) {
            ecuDTOS.add(getEcuDTO(ecu));
        }
        return ecuDTOS;
    }

    public CarSkin getCarSkin(CarSkinDTO dto){
        CarSkin carSkin = new CarSkin();
        carSkin.setCarId(dto.getCar().getId());
        carSkin.setId(dto.getId());
        carSkin.setName(dto.getName());
        return carSkin;
    }

    public Car getCar(Integer id){
        if (id!=null){
            return carMapper.getCarById(id);
        }
        return null;
    }


    /**
     * 获取赛车皮肤列表
     * @param carId
     * @return 赛车皮肤列表
     */
    public List<CarSkinDTO> listCarSkinDTOs(int carId){
        List<CarSkinDTO> carSkinDTOs = new LinkedList<>();
        List<CarSkin> carSkins = carMapper.listCarSkinsByCarId(carId);
        for (CarSkin carSkin : carSkins) {
            CarSkinDTO carSkinDTO = new CarSkinDTO();
            carSkinDTO.setId(carSkin.getId());
            carSkinDTO.setName(carSkin.getName());
            carSkinDTOs.add(carSkinDTO);
        }
        return carSkinDTOs;
    }

    /**
     * 获取赛车皮肤列表
     * @param car
     * @return 赛车皮肤列表
     */
    public List<CarSkinDTO> listCarSkinDTOs(Car car){
        List<CarSkinDTO> carSkinDTOs = new LinkedList<>();
        List<CarSkin> carSkins = carMapper.listCarSkinsByCarId(car.getId());
        for (CarSkin carSkin : carSkins) {
            CarSkinDTO carSkinDTO = new CarSkinDTO();
            carSkinDTO.setId(carSkin.getId());
            carSkinDTO.setName(carSkin.getName());
            carSkinDTOs.add(carSkinDTO);
        }
        return carSkinDTOs;
    }

    String getLabel(String carRank){
        String label;
        switch (carRank){
            case "A":label="A车";break;
            case "B":label="B车";break;
            case "C":label="C车";break;
            case "D":label="D车";break;
            case "L1":label="L1滑板";break;
            case "X":label="悬浮车";break;
            case "M1":label="M1摩托";break;
            case "M2":label="M2摩托";break;
            default:label="未知";break;
        }
        return label;
    }


    public CarDTO getCarDTO(int carId){
        Car car = carMapper.getCarById(carId);
        return getCarDTO(car);
    }

    /**
     * domain转DTO
     */
    public CarDTO getCarDTO(Car car){
        if (car==null) return null;
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setName(car.getName());
        carDTO.setRank(car.getRank());
        carDTO.setLabel(getLabel(car.getRank()));
        carDTO.setPower(car.getPower());
        return carDTO;
    }
    public EcuDTO getEcuDTO(Ecu ecu){
        if (ecu==null) return null;
        EcuDTO dto = new EcuDTO();
        dto.setId(ecu.getId());
        dto.setName(ecu.getName());
        dto.setPower(ecu.getPower());
        return dto;
    }
    public EcuDTO getEcuDTO(Integer id){
        if (id==null) return null;
        Ecu ecu = carMapper.getEcuById(id);
        return getEcuDTO(ecu);
    }
    public CarSkinDTO getCarSkinDTO(CarSkin carSkin){
        if (carSkin==null) return null;
        CarSkinDTO dto = new CarSkinDTO();
        dto.setId(carSkin.getId());
        dto.setName(carSkin.getName());
        dto.setCar(getCar(carSkin.getCarId()));
        dto.setPower(carSkin.getPower());
        return dto;
    }
    public CarSkinDTO getCarSkinDTO(Integer carSkinId){
        if (carSkinId==null) return null;
        return getCarSkinDTO(carMapper.getCarSkinById(carSkinId));
    }
}
