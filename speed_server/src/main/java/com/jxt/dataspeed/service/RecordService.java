package com.jxt.dataspeed.service;

import com.jxt.dataspeed.mapper.*;
import com.jxt.dataspeed.pojo.domain.Ecu;
import com.jxt.dataspeed.pojo.domain.Record;
import com.jxt.dataspeed.pojo.dto.RecordDTO;
import com.jxt.dataspeed.pojo.vo.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Service
public class RecordService {


    @Autowired
    RecordMapper recordMapper;
    @Autowired
    TrackMapper trackMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    PetMapper petMapper;
    @Autowired
    PlayerMapper playerMapper;

    @Autowired
    TrackService trackService;
    @Autowired
    PlayerService playerService;
    @Autowired
    CarService carService;
    @Autowired
    PetService petService;
    @Autowired
    AdminService adminService ;
    /**
     增
     */
    public Double saveRecord(Record record) {
        double i = recordMapper.saveRecord(record);
        trackMapper.increaseTrackPower(record.getTrackId());
        playerMapper.increasePlayerPower(record.getPlayerId());
        carMapper.increaseCarPower(record.getCarId());
        if (record.getCarSkinId()!=null){
            carMapper.increaseCarSkinPower(record.getCarSkinId());
        }
        if (record.getPetId()!=null){
            petMapper.increasePetPower(record.getPetId());
            if (record.getPetSkinId()!=null){
                petMapper.increasePetSkinPower(record.getPetSkinId());
            }
        }
        if (record.getEcuId()!=null){
            carMapper.increaseEcuPower(record.getEcuId());
        }
        if (i==1){
            return record.getId();
        }else {
            return null;
        }
    }
    /**
     * 删
     */
    public boolean deleteRecord(double id) {
        return recordMapper.deleteRecord(id)>0;
    }


    /**
     * 改
     */
    public boolean updateRecord(Record record) {
        return recordMapper.updateRecord(record)>0;
    }


    /**
     * 查
     */
    public List<RecordDTO> listRecordDTOs(String type,String pet,String ecu) {
        List<RecordDTO> recordDTOs = new LinkedList<>();
        List<Record> records;
        if (type!=null&&!"".equals(type)){
            HashMap<String, String> params = new HashMap<>();
            params.put("type",type);
            params.put("pet",pet);
            params.put("ecu",ecu);
            records = recordMapper.listRecordsByParams(params);
        }else {
            records = recordMapper.listRecords();
        }
        for (Record record : records) {
            recordDTOs.add(getRecordDTO(record));
        }
        return recordDTOs;
    }

    /**
     * 查
     */
    public PageBean listRecentRecordDTOs(String type,
                                         String pet,
                                         String ecu,
                                         String currentPage,
                                         String pageSize,
                                         String searchKey,
                                         String trackId,
                                         String playerId,
                                         String carId,
                                         String orderBy) {
        PageBean pageBean = new PageBean();
        int cur = Integer.parseInt(currentPage);
        int size = Integer.parseInt(pageSize);
        Integer trackIdInt = trackId!=null&&!"".equals(trackId)?Integer.parseInt(trackId):null;
        Integer playerIdInt = playerId!=null&&!"".equals(playerId)?Integer.parseInt(playerId):null;
        Integer carIdInt = carId!=null&&!"".equals(carId)?Integer.parseInt(carId):null;
        pageBean.setCurrentPage(cur);
        pageBean.setPageSize(size);

        //只在第一页计算总页数
        if(cur==1){
            int totalCount = getTotalCount(type,pet,ecu,searchKey,null,null,null);
            pageBean.setTotalCount(totalCount);
            int screenCount = getTotalCount(type,pet,ecu,searchKey,trackIdInt,playerIdInt,carIdInt);
            pageBean.setScreenCount(screenCount);
        }
        int start = (cur - 1) * size;
        //int totalPage = screenCount % size == 0 ? screenCount / size :(screenCount / size) + 1 ;
        //pageBean.setTotalPage(totalPage);
        List<Record> records = recordMapper.listRecentRecordsByParams(type,pet,ecu,start,size,searchKey,trackIdInt,playerIdInt,carIdInt,orderBy);
        List<RecordDTO> recordDTOs = new LinkedList<>();
        for (Record record : records) {
            recordDTOs.add(getRecordDTO(record));
        }
        pageBean.setRecords(recordDTOs);
        return pageBean;
    }
    public int getTotalCount(String type, String pet, String ecu,String searchKey,Integer trackId,
                             Integer playerId,
                             Integer carId) {
        return recordMapper.getTotalCount(type,pet,ecu,searchKey,trackId,playerId,carId);
    }

    /**
     * do转dto
     */
    public RecordDTO getRecordDTO(Record record){
        if (record==null) return null;
        RecordDTO dto = new RecordDTO();
        dto.setId(record.getId());
        dto.setType(record.getType());
        dto.setVideoUrl(record.getVideoUrl());
        dto.setShadowUrl(record.getShadowUrl());
        dto.setTrack(trackService.getTrackDTO(record.getTrackId()));
        dto.setPlayer(playerService.getPlayerDTO(record.getPlayerId()));
        dto.setCar(carService.getCarDTO(record.getCarId()));
        dto.setCarSkin(carService.getCarSkinDTO(record.getCarSkinId()));
        dto.setPet(petService.getPetDTO(record.getPetId()));
        dto.setPetSkin(petService.getPetSkinDTO(record.getPetSkinId()));
        dto.setEcu(carService.getEcuDTO(record.getEcuId()));
        dto.setComment(record.getComment());
        int minute = record.getTimeSecond()/60;
        int second = record.getTimeSecond()%60;
        int milliSecond = record.getTimeMillisecond();
        String timeStr = (minute<=9?"0"+minute:minute+"")
                +":"+(second<=9?"0"+second:second+"")
                +":"+(milliSecond<=9?"0"+milliSecond:(milliSecond>=100?milliSecond/10+"":milliSecond+""));
        dto.setTime(timeStr);
        dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(record.getDate()));
        dto.setAdmin(adminService.getAdminDTO(record.getUpdateAdminId()));
        return dto;
    }
    public RecordDTO getRecordDTO(Double id){
        if (id == null) return null;
        Record record  = recordMapper.getRecordById(id);
        return getRecordDTO(record);
    }


}
