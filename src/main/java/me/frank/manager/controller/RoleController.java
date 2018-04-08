package me.frank.manager.controller;

import me.frank.manager.aop.annotation.NotEmpty;
import me.frank.manager.server.service.IAdminService;
import me.frank.manager.server.service.IRoleService;
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
@RequestMapping(value = "/manage/role", produces = "application/json", method = RequestMethod.POST)
public class RoleController {

    @Autowired
    IRoleService roleService;


    @RequestMapping("/queryRoleList")
    public Object queryRoleList(String nowPage, String pageSize,
                                String searchField, String searchValue,
                                String searchStartTime, String searchEndTime) {

        return roleService.queryRoleList(nowPage, pageSize, searchField, searchValue, searchStartTime, searchEndTime);

    }

    @RequestMapping("/queryRoleInfo")
    public Object queryRoleInfo(@NotEmpty(name = "roleId") String roleId) {

        return roleService.queryRoleInfo(roleId);

    }

    @RequestMapping("/addOrUpdateRole")
    public Object addOrUpdateRole(String roleId, String roleName, String roleAuth) {

        return roleService.addOrUpdateRole(roleId, roleName, roleAuth);

    }

    @RequestMapping("/delRole")
    public Object delRole(String[] roleIds) {

        return roleService.delRole(roleIds);

    }


}
