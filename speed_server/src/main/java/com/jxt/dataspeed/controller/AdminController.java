package com.jxt.dataspeed.controller;

import com.jxt.dataspeed.pojo.domain.Admin;
import com.jxt.dataspeed.pojo.domain.Donate;
import com.jxt.dataspeed.pojo.domain.Donate;
import com.jxt.dataspeed.pojo.dto.AdminDTO;
import com.jxt.dataspeed.pojo.dto.DonateDTO;
import com.jxt.dataspeed.pojo.dto.EcuDTO;
import com.jxt.dataspeed.pojo.vo.ResultInfo;
import com.jxt.dataspeed.service.*;
import com.jxt.dataspeed.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/admin/login",method = RequestMethod.POST)
    public ResultInfo login(Admin admin, HttpServletRequest request, HttpServletResponse response){
        /*验证码*/
        /*String rightCode = (String) request.getSession().getAttribute("rightCode");
        String tryCode = request.getParameter("tryCode");
        System.out.println("rightCode:" + rightCode + " ———— tryCode:" + tryCode);
        if (!rightCode.equals(tryCode)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
        } else {
            Admin adminReturn = adminService.login(admin);
            if (adminReturn==null){
                info.setFlag(false);
                info.setErrorMsg("账号或密码错误！");
            }else {
                info.setFlag(true);
                info.setData(adminReturn);
                //request.getSession().setAttribute("admin",adminReturn);
            }
        }*/
        ResultInfo info = new ResultInfo();
        //System.out.println(admin);
        Admin adminReturn = adminService.login(admin);
        if (adminReturn==null){
            info.setFlag(false);
            info.setErrorMsg("账号或密码错误！");
        }else {
            info.setFlag(true);
            adminReturn.setPassword(null);
            Cookie cookie = new Cookie("token",JWTUtil.createToken(adminReturn.getId()+""));
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
            info.setData(adminReturn);
        }
        return info;
    }

    @RequestMapping(value = "/admin/verify",method = RequestMethod.GET)
    public ResultInfo verify(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        Integer userId = adminService.verifyAdmin(request);
        //System.out.println(userId);
        if (userId!=null){
            Admin admin = adminService.getAdmin(userId);
            if (admin==null){
                info.setFlag(false);
                info.setErrorMsg("错误的用户ID");
            }else {
                AdminDTO adminDTO = adminService.getAdminDTO(admin);
                info.setFlag(true);
                info.setData(adminDTO);
            }

        }else {
            info.setFlag(false);
            info.setErrorMsg("需要登录");
        }
        return info;
    }


    @RequestMapping(value = "/admin/getAdmin",method = RequestMethod.GET)
    public ResultInfo getAdmin(HttpServletRequest request){
        ResultInfo info = new ResultInfo();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin!=null){
            info.setFlag(true);
            info.setData(admin);
        }else {
            info.setFlag(false);
        }
        return info;
    }
    
    
    /*
    捐助
     */

    /**
     * 增
     * @return
     */

    @RequestMapping(value = "/donate",method = RequestMethod.POST)
    public ResultInfo saveDonate(DonateDTO dto){
        ResultInfo info = new ResultInfo();
        Integer id = adminService.saveDonateDTO(dto);
        if (id!=null){
            info.setFlag(true);
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

    @RequestMapping(value = "donate/{id}",method = RequestMethod.DELETE)
    public ResultInfo deleteDonate(@PathVariable(name = "id") String id){
        ResultInfo info = new ResultInfo();
        if(id==null||"".equals(id)){
            info.setErrorMsg("错误");
            return info;
        }
        boolean flag = adminService.deleteDonate(Integer.parseInt(id));
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
    @RequestMapping(value = "/donate",method = RequestMethod.PUT)
    public ResultInfo updateDonate(DonateDTO dto){
        ResultInfo info = new ResultInfo();
        boolean flag = adminService.updateDonateDTO(dto);
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
     */

    @RequestMapping(value = "/donates",method = RequestMethod.GET)
    public ResultInfo listDonates(){
        ResultInfo info = new ResultInfo();
        List<DonateDTO> donates = adminService.listDonateDTOs();
        if (donates!=null){
            info.setFlag(true);
            info.setData(donates);
        }else {
            info.setFlag(false);
            info.setErrorMsg("查询失败！");
        }
        return info;
    }

}
