package com.jxt.dataspeed.controller;

import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.domain.PetSkin;
import com.jxt.dataspeed.pojo.dto.PetDTO;
import com.jxt.dataspeed.pojo.dto.PetSkinDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PetController {

    @Autowired
    private PetService petService;
    /**
     * 增
     * @return
     */

    @RequestMapping(value = "/pet",method = RequestMethod.POST)
    public ResultInfo savePet(Pet pet){
        ResultInfo info = new ResultInfo();
        Integer id = petService.savePet(pet);
        if (id!=null){
            info.setFlag(true);
            info.setData(petService.getPetDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("保存失败！");
        }
        return info;
    }


    @RequestMapping(value = "/petSkin",method = RequestMethod.POST)
    public ResultInfo savePetSkinDTO(PetSkin petSkin){
        ResultInfo info = new ResultInfo();
        Integer id = petService.savePetSkin(petSkin);
        if (id!=null){
            info.setFlag(true);
            info.setData(petService.getPetSkinDTO(id));
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

    @RequestMapping(value = "pet/{id}",method = RequestMethod.DELETE)
    public ResultInfo deletePet(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = petService.deletePet(Integer.parseInt(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败！");
        }
        return info;
    }
    @RequestMapping(value = "petSkin/{id}",method = RequestMethod.DELETE)
    public ResultInfo deletePetSkin(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = petService.deletePetSkin(Integer.parseInt(id));
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
    @RequestMapping(value = "/pet",method = RequestMethod.PUT)
    public ResultInfo updatePet(Pet pet){
        ResultInfo info = new ResultInfo();
        boolean flag = petService.updatePet(pet);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("更新失败！");
        }
        return info;
    }
    @RequestMapping(value = "/petSkin",method = RequestMethod.PUT)
    public ResultInfo updatePetSkin(PetSkin petSkin){
        ResultInfo info = new ResultInfo();
        boolean flag = petService.updatePetSkin(petSkin);
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

    @RequestMapping(value = "/pets",method = RequestMethod.GET)
    public ResultInfo listPetDTOs(){
        ResultInfo info = new ResultInfo();
        List<PetDTO> petDTOS = petService.listPetDTOs();
        if (petDTOS!=null){
            info.setFlag(true);
            info.setData(petDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

    @RequestMapping(value = "/petSkins",method = RequestMethod.GET)
    public ResultInfo listPetSkinDTOs(){
        ResultInfo info = new ResultInfo();
        List<PetSkinDTO> petSkinDTOS = petService.listPetSkinDTOs();
        if (petSkinDTOS!=null){
            info.setFlag(true);
            info.setData(petSkinDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

}
