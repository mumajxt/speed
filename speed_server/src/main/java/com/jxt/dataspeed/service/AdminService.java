package com.jxt.dataspeed.service;


import com.jxt.dataspeed.mapper.AdminMapper;
import com.jxt.dataspeed.pojo.domain.Admin;
import com.jxt.dataspeed.pojo.domain.Donate;
import com.jxt.dataspeed.pojo.domain.Donate;
import com.jxt.dataspeed.pojo.domain.Record;
import com.jxt.dataspeed.pojo.dto.AdminDTO;
import com.jxt.dataspeed.pojo.dto.DonateDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    public Admin getAdmin(String username) {
        return adminMapper.getAdmin(username);
    }

    public Admin getAdmin(Integer adminId) {
        return adminMapper.getAdminById(adminId);
    }

    public AdminDTO getAdminDTO(Integer adminId) {
        if (adminId==null) return null;
        Admin admin = getAdmin(adminId);
        return getAdminDTO(admin);
    }

    public AdminDTO getAdminDTO(Admin admin) {
        if (admin==null) return null;
        AdminDTO dto = new AdminDTO();
        dto.setId(admin.getId());
        dto.setNickname(admin.getNickname());
        return dto;
    }

    /**
     *
     * @param request
     * @return 管理员ID
     */
    public Integer verifyAdmin(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            //System.out.println("cookie不为空");
            String token = "";
            for (Cookie cookie : cookies) {
                //System.out.println(cookie.getName()+":"+cookie.getValue());
                if (cookie.getName().equals("token")){
                    token=cookie.getValue();
                }
            }
            if (token.equals("")){
                return null;
            }
            String userIdStr = JWTUtil.verifyToken(token);
            if (userIdStr==null){
                return null;
            }else {
                return Integer.parseInt(userIdStr);
            }
        }else {
            //System.out.println("cookie为空");
            return null;
        }
    }
    




    /**
     增
     */
    public Integer saveDonateDTO(DonateDTO dto) {
        Donate donate = getDonate(dto);
        int i = adminMapper.saveDonate(donate);
        if (i==1){
            return donate.getId();
        }else {
            return null;
        }
    }

    /**
     * 删
     */
    public boolean deleteDonate(int id) {
        return adminMapper.deleteDonate(id)>0;
    }


    /**
     * 改
     */
    public boolean updateDonateDTO(DonateDTO dto) {
        Donate donate = getDonate(dto);
        return adminMapper.updateDonate(donate)>0;
    }

    /**
     * 查
     */
    public List<DonateDTO> listDonateDTOs() {
        List<Donate> donates = adminMapper.listDonates();
        LinkedList<DonateDTO> donateDTOS = new LinkedList<>();
        donates.forEach(donate -> donateDTOS.push(getDonateDTO(donate)));
        return donateDTOS;
    }

    /**
     * domain转dto
     */
    public DonateDTO getDonateDTO(Donate donate){
        DonateDTO dto = new DonateDTO();
        dto.setId(donate.getId());
        dto.setName(donate.getName());
        dto.setMoney(donate.getMoney());
        dto.setName(donate.getName());
        Date date = donate.getDate();
        if (date != null) {
            dto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
        return dto;
    }
    /**
     * dto转do
     */
    public Donate getDonate(DonateDTO dto){
        Donate donate = new Donate();
        donate.setId(dto.getId());
        donate.setName(dto.getName());
        donate.setMoney(dto.getMoney());
        try {
            donate.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDate()));
        } catch (ParseException e) {
            return null;
        }
        return donate;
    }
}
