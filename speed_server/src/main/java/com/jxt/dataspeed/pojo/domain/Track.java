package com.jxt.dataspeed.pojo.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Track {
    private Integer id;
    private String name;
    private Integer stars;
    private Boolean isNew;
    private Boolean isLeague;
    private Boolean isSea;
    private Boolean isOld;
    private Date date;
    private Integer power;
}
