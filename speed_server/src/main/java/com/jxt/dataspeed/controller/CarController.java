package com.jxt.dataspeed.controller;

import com.jxt.dataspeed.pojo.domain.Car;
import com.jxt.dataspeed.pojo.domain.CarSkin;
import com.jxt.dataspeed.pojo.domain.Ecu;
import com.jxt.dataspeed.pojo.dto.CarDTO;
import com.jxt.dataspeed.pojo.dto.CarSkinDTO;
import com.jxt.dataspeed.pojo.dto.EcuDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarService carService;

    /**
     * 增
     * @return
     */

    @RequestMapping(value = "/car",method = RequestMethod.POST)
    public ResultInfo saveCar(Car car){
        ResultInfo info = new ResultInfo();
        Integer id = carService.saveCar(car);
        if (id!=null){
            info.setFlag(true);
            info.setData(carService.getCarDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("保存失败！");
        }
        return info;
    }


    @RequestMapping(value = "/carSkin",method = RequestMethod.POST)
    public ResultInfo saveCarSkinDTO(CarSkin carSkin){
        ResultInfo info = new ResultInfo();
        Integer id = carService.saveCarSkin(carSkin);
        if (id!=null){
            info.setFlag(true);
            info.setData(carService.getCarSkinDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("保存失败！");
        }
        return info;
    }

    @RequestMapping(value = "/ecu",method = RequestMethod.POST)
    public ResultInfo saveEcu(Ecu ecu){
        ResultInfo info = new ResultInfo();
        Integer id = carService.saveEcu(ecu);
        if (id!=null){
            info.setFlag(true);
            info.setData(carService.getEcuDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("保存失败！");
        }
        return info;
    }


    /**
     * 删
     * @return
     */

    @RequestMapping(value = "car/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteCar(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = carService.deleteCar(Integer.parseInt(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败！");
        }
        return info;
    }
    @RequestMapping(value = "carSkin/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteCarSkin(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = carService.deleteCarSkin(Integer.parseInt(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败！");
        }
        return info;
    }

    @RequestMapping(value = "ecu/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteEcu(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = carService.deleteEcu(Integer.parseInt(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败！");
        }
        return info;
    }




    /**
     * 改
     * @return
     */
    @RequestMapping(value = "/car",method = RequestMethod.PUT)
    public ResultInfo updateCar(Car car){
        ResultInfo info = new ResultInfo();
        boolean flag = carService.updateCar(car);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("更新失败！");
        }
        return info;
    }
    @RequestMapping(value = "/carSkin",method = RequestMethod.PUT)
    public ResultInfo updateCarSkin(CarSkin carSkin){
        ResultInfo info = new ResultInfo();
        boolean flag = carService.updateCarSkin(carSkin);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("更新失败！");
        }
        return info;
    }
    @RequestMapping(value = "/ecu",method = RequestMethod.PUT)
    public ResultInfo updateEcu(Ecu ecu){
        ResultInfo info = new ResultInfo();
        boolean flag = carService.updateEcu(ecu);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("更新失败！");
        }
        return info;
    }



    /**
     * 查
     * @return
     */

    @RequestMapping(value = "/cars",method = RequestMethod.GET)
    public ResultInfo listCarDTOs(
            @RequestParam(name = "type",required = false) String type,
            @RequestParam(name = "pet",required = false) String pet,
            @RequestParam(name = "ecu",required = false) String ecu
    ){
        ResultInfo info = new ResultInfo();
        List<CarDTO> carDTOS;
        if (type!=null&&!"".equals(type)){
            carDTOS = carService.listCarDTOsByParams(type,pet,ecu);
        }else {
            carDTOS = carService.listCarDTOs();
        }
        if (carDTOS!=null){
            info.setFlag(true);
            info.setData(carDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

    @RequestMapping(value = "/carSkins",method = RequestMethod.GET)
    public ResultInfo listCarSkinDTOs(){
        ResultInfo info = new ResultInfo();
        List<CarSkinDTO> carSkinDTOS = carService.listCarSkinDTOs();
        if (carSkinDTOS!=null){
            info.setFlag(true);
            info.setData(carSkinDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }
    @RequestMapping(value = "/ecus",method = RequestMethod.GET)
    public ResultInfo listEcuDTOs(){
        ResultInfo info = new ResultInfo();
        List<EcuDTO> ecuDTOs = carService.listEcuDTOs();
        if (ecuDTOs!=null){
            info.setFlag(true);
            info.setData(ecuDTOs);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

}
