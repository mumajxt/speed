package com.jxt.dataspeed.pojo.dto;
import com.jxt.dataspeed.pojo.domain.Admin;
import lombok.Data;

@Data
public class RecordDTO {
    private Double id;//记录ID
    private String type;//类型
    private String videoUrl;
    private String shadowUrl;//影子url
    private TrackDTO track;//地图
    private PlayerDTO player;//车手
    private CarDTO car;//车
    private CarSkinDTO carSkin;
    private PetDTO pet;
    private PetSkinDTO petSkin;//宠物
    private EcuDTO ecu;//ECU
    private String time;//记录
    private String date;//记录产生时间
    private AdminDTO admin;//修改数据的管理员
    private String comment;//备注
}