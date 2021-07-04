package com.jxt.dataspeed.controller;

import com.jxt.dataspeed.pojo.domain.Player;
import com.jxt.dataspeed.pojo.domain.Team;
import com.jxt.dataspeed.pojo.dto.PlayerDTO;
import com.jxt.dataspeed.pojo.dto.TeamDTO;
import com.jxt.dataspeed.pojo.dto.TrackDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /**
     * 增
     * @return
     */


    @RequestMapping(value = "/player",method = RequestMethod.POST)
    public ResultInfo savePlayer(Player player){
        ResultInfo info = new ResultInfo();
        Double id = playerService.savePlayer(player);
        if (id!=null){
            info.setFlag(true);
            info.setData(playerService.getPlayerDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("保存失败！");
        }
        return info;
    }


    @RequestMapping(value = "/team",method = RequestMethod.POST)
    public ResultInfo saveTeamDTO(TeamDTO teamDTO){
        ResultInfo info = new ResultInfo();
        Integer id = playerService.saveTeamDTO(teamDTO);
        if (id!=null){
            info.setFlag(true);
            info.setData(playerService.getTeamDTO(id));
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

    @RequestMapping(value = "player/{id}",method = RequestMethod.DELETE)
    public ResultInfo deletePlayer(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = playerService.deletePlayer(Double.parseDouble(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("删除失败！");
        }
        return info;
    }
    @RequestMapping(value = "team/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteTeamDTO(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = playerService.deleteTeamDTO(Integer.parseInt(id));
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
    @RequestMapping(value = "/team",method = RequestMethod.PUT)
    public ResultInfo updateTeamDTO(TeamDTO teamDTO){
        ResultInfo info = new ResultInfo();
        boolean flag = playerService.updateTeamDTO(teamDTO);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("更新失败！");
        }
        return info;
    }

    @RequestMapping(value = "/player",method = RequestMethod.PUT)
    public ResultInfo updatePlayer(Player player){
        ResultInfo info = new ResultInfo();
        boolean flag = playerService.updatePlayer(player);
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

    @RequestMapping(value = "/players",method = RequestMethod.GET)
    public ResultInfo listPlayerDTOs(
            @RequestParam(name = "type",required = false) String type,
            @RequestParam(name = "pet",required = false) String pet,
            @RequestParam(name = "ecu",required = false) String ecu
    ){
        ResultInfo info = new ResultInfo();
        List<PlayerDTO> playerDTOS;
        if (type!=null&&!"".equals(type)){
            playerDTOS = playerService.listPlayerDTOsByParams(type,pet,ecu);
        }else{
            playerDTOS = playerService.listPlayerDTOs();
        }
        if (playerDTOS!=null){
            info.setFlag(true);
            info.setData(playerDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

    @RequestMapping(value = "/teams",method = RequestMethod.GET)
    public ResultInfo listTeamDTOs(){
        ResultInfo info = new ResultInfo();
        List<TeamDTO> teamDTOS = playerService.listTeamDTOs();
        if (teamDTOS!=null){
            info.setFlag(true);
            info.setData(teamDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

}
