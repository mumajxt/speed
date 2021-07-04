package com.jxt.dataspeed.controller;
import com.jxt.dataspeed.pojo.dto.TrackDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TrackController {

    @Autowired
    private TrackService trackService;

    /**
     * 增
     * @param trackDTO
     * @return
     */
    @RequestMapping(value = "/track",method = RequestMethod.POST)
    public ResultInfo saveTrackDTO(TrackDTO trackDTO){
        //System.out.println(trackDTO);
        ResultInfo info = new ResultInfo();
        Integer id = trackService.saveTrackDTO(trackDTO);
        if (id!=null){
            info.setFlag(true);
            info.setData(trackService.getTrackDTO(id));
        }else {
            info.setFlag(false);
            info.setErrorMsg("添加失败！");
        }
        return info;
    }


    /**
     * 删
     * @param id
     * @return
     */
    @RequestMapping(value = "/track/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteTrack(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        boolean flag = trackService.deleteTrack(Integer.parseInt(id));
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("添加失败！");
        }
        return info;
    }

    /**
     * 改
     * @param trackDTO
     * @return
     */
    @RequestMapping(value = "/track",method = RequestMethod.PUT)
    public ResultInfo updateTrackDTO(TrackDTO trackDTO){
        //System.out.println(trackDTO);
        ResultInfo info = new ResultInfo();
        boolean flag = trackService.updateTrackDTO(trackDTO);
        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("修改失败！");
        }
        return info;
    }

    /**
     * 查
     * @param id
     * @return
     */
    @RequestMapping(value = "/track/{id}",method = RequestMethod.GET)
    public ResultInfo getTrackDTO(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if (id!=null&&!id.equals("")){
            TrackDTO trackDTO = trackService.getTrackDTO(Integer.parseInt(id));
            if (trackDTO!=null){
                info.setFlag(true);
                info.setData(trackDTO);
            }else {
                info.setFlag(false);
                info.setErrorMsg("找不到该地图！");
            }
        }else {
            info.setFlag(false);
            info.setErrorMsg("无id！");
        }
        return info;
    }
    @RequestMapping(value = "/tracks",method = RequestMethod.GET)
    public ResultInfo listTrackDTOs(@RequestParam(name = "type",required = false) String type,
                                    @RequestParam(name = "pet",required = false) String pet,
                                    @RequestParam(name = "ecu",required = false) String ecu){
        ResultInfo info = new ResultInfo();
        List<TrackDTO> trackDTOs;
        if(type!=null&&!"".equals(type)){
            trackDTOs = trackService.listTrackDTOsByParams(type,pet,ecu);
        }else{
            trackDTOs = trackService.listTrackDTOs();
        }
        if (trackDTOs!=null){
            info.setFlag(true);
            info.setData(trackDTOs);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }


}
