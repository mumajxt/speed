package com.jxt.dataspeed.controller;

import com.jxt.dataspeed.pojo.domain.Record;
import com.jxt.dataspeed.pojo.domain.Record;
import com.jxt.dataspeed.pojo.dto.RecordDTO;
import com.jxt.dataspeed.pojo.vo.PageBean;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.AdminService;
import com.jxt.dataspeed.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RecordController {

    @Autowired
    private RecordService recordService;
    @Autowired
    private AdminService adminService;

    /**
     * 增
     */

    @RequestMapping(value = "/record",method = RequestMethod.POST)
    public ResultInfo saveRecord(Record record,
                                 @RequestParam(value = "timeStr")String timeStr,
                                 @RequestParam(value = "dateStr")String dateStr,
                                 HttpServletRequest request){
        //System.out.println(record);
        ResultInfo info = new ResultInfo();
        Integer adminId = adminService.verifyAdmin(request);
        if (adminId==null){
            info.setErrorMsg("未登录");
            return info;
        }
        record.setUpdateAdminId(adminId);
        record.setUpdateTime(new Date());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            record.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            info.setErrorMsg("解析日期出错");
            return info;
        }
        String[] list = timeStr.split(":");
        if (list.length==3){
            int a = Integer.parseInt(list[0]);
            int b = Integer.parseInt(list[1]);
            int c = Integer.parseInt(list[2]);
            record.setTimeSecond(a*60+b);
            record.setTimeMillisecond(c);
            Double id = recordService.saveRecord(record);
            if (id!=null){
                info.setFlag(true);
                info.setData(recordService.getRecordDTO(id));
            }else {
                info.setFlag(false);
                info.setErrorMsg("保存失败！");
            }
        }else {
            info.setErrorMsg("解析记录出错");
        }
        return info;
    }


    /**
     * 删
     * @return
     */

    @RequestMapping(value = "record/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteRecord(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = recordService.deleteRecord(Integer.parseInt(id));
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
    @RequestMapping(value = "/record",method = RequestMethod.PUT)
    public ResultInfo updateRecord(Record record,
                                   @RequestParam(value = "timeStr")String timeStr,
                                   @RequestParam(value = "dateStr")String dateStr,
                                   HttpServletRequest request){
        //System.out.println(record);
        ResultInfo info = new ResultInfo();
        Integer adminId = adminService.verifyAdmin(request);
        if (adminId==null){
            info.setErrorMsg("未登录");
            return info;
        }
        record.setUpdateAdminId(adminId);
        record.setUpdateTime(new Date());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            record.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
            info.setErrorMsg("解析日期出错");
            return info;
        }
        String[] list = timeStr.split(":");
        if (list.length==3){
            int a = Integer.parseInt(list[0]);
            int b = Integer.parseInt(list[1]);
            int c = Integer.parseInt(list[2]);
            record.setTimeSecond(a*60+b);
            record.setTimeMillisecond(c);
            boolean flag = recordService.updateRecord(record);
            if (flag){
                info.setFlag(true);
            }else {
                info.setFlag(false);
                info.setErrorMsg("更新失败！");
            }
        }else {
            info.setErrorMsg("解析记录出错");
        }
        return info;
    }
    
    
    /**
     * 查
     * @return
     */

    @RequestMapping(value = "/records",method = RequestMethod.GET)
    public ResultInfo listRecordDTOs(
            @RequestParam(name = "type",required = false) String type,
            @RequestParam(name = "pet",required = false) String pet,
            @RequestParam(name = "ecu",required = false) String ecu
            ){
        //System.out.println(type+"#"+pet+"#"+ecu);
        ResultInfo info = new ResultInfo();
        List<RecordDTO> recordDTOS = recordService.listRecordDTOs(type,pet,ecu);
        if (recordDTOS!=null){
            info.setFlag(true);
            info.setData(recordDTOS);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

    @RequestMapping(value = "/recentRecords",method = RequestMethod.GET)
    public ResultInfo listRecentRecordDTOs(
            @RequestParam(name = "type") String type,
            @RequestParam(name = "pet") String pet,
            @RequestParam(name = "ecu") String ecu,
            @RequestParam(name = "currentPage") String currentPage,
            @RequestParam(name = "pageSize") String pageSize,
            @RequestParam(name = "searchKey") String searchKey,

            @RequestParam(name = "trackId",required = false) String trackId,
            @RequestParam(name = "playerId",required = false) String playerId,
            @RequestParam(name = "carId",required = false) String carId,
            @RequestParam(name = "orderBy",required = false) String orderBy
            ){
        //System.out.println(type+"#"+pet+"#"+ecu);
        ResultInfo info = new ResultInfo();
        PageBean pageBean = recordService.listRecentRecordDTOs(type,pet,ecu,currentPage,pageSize,searchKey,trackId,playerId,carId,orderBy);


        if (pageBean!=null){
            info.setFlag(true);
            info.setData(pageBean);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }
}
