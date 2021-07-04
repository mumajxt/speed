package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AdminMapper {
    Admin login(Admin admin);

    Admin getAdmin(String username);
    Admin getAdminById(int id);

    /**
     * 捐助
     */
    int saveDonate(Donate donate);
    int deleteDonate(int donateId);
    int updateDonate(Donate donate);
    List<Donate> listDonates();
}
