package me.frank.manager.controller;

import me.frank.manager.aop.annotation.NotEmpty;
import me.frank.manager.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 77 on 2017/9/4.
 */
@RestController
@RequestMapping(value = "/manage/admin", produces = "application/json", method = RequestMethod.POST)
public class AdminController {

    @Autowired
    IAdminService adminService;

    @RequestMapping("/adminLogin")
    public Object adminLogin(String account, String password, String token, String saveTime, HttpServletRequest request, HttpServletResponse response) {

        return adminService.adminLogin(account, password, token, saveTime, request, response);

    }

    @RequestMapping("/queryAdminInfo")
    public Object queryAdminInfo(String adminId, String account) {

        return adminService.queryAdminInfo(adminId, account);

    }


    @RequestMapping("/queryAdminList")
    public Object queryAdminList(String nowPage, String pageSize,
                                 String searchField, String searchValue,
                                 String searchStartTime, String searchEndTime) {

        return adminService.queryAdminList(nowPage, pageSize, searchField, searchValue, searchStartTime, searchEndTime);

    }

    @RequestMapping("/addOrUpdateAdmin")
    public Object addOrUpdateAdmin(String adminId,
                                   @NotEmpty(name = "角色") String roleId,
                                   @NotEmpty(name = "账号") String account,
                                   @NotEmpty(name = "姓名") String name,
                                   String password,
                                   @NotEmpty(name = "手机号") String phone,
                                   @NotEmpty(name = "邮箱") String email) {

        return adminService.addOrUpdateAdmin(adminId, roleId, account, name, password, phone, email);

    }

    @RequestMapping("/delAdmin")
    public Object delAdmin(String[] adminIds) {

        return adminService.delAdmin(adminIds);

    }


}
