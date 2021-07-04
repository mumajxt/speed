package com.jxt.dataspeed.pojo.domain;

import lombok.Data;

import java.util.Date;
@Data
public class Record{
    private Double id;//记录ID
    private String type;//类型
    private String videoUrl;
    private String shadowUrl;//影子url
    private Integer trackId;//地图
    private Double playerId;//车手ID
    private Integer carId;//车ID
    private Integer carSkinId;//车皮肤ID
    private Integer petId;//宠物ID
    private Integer petSkinId;//宠物皮肤ID
    private Integer ecuId;//ECU ID
    private Integer timeSecond;//记录秒数
    private Integer timeMillisecond;//记录毫秒数
    private Date date;//记录产生时间
    private Date updateTime;//更新数据时间
    private Integer updateAdminId;
    private String comment;//备注
}