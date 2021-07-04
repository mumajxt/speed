package com.jxt.dataspeed.pojo.domain;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String nickname;
    private String password;
    private String describe;
    private String token;
}
