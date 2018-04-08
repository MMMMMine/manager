package me.frank.manager.server.service;

/**
 * Created by frank on 2018/2/27.
 */
public interface IRoleService {
    Object queryRoleList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime);

    Object addOrUpdateRole(String roleId,String roleName, String roleAuth);

    Object delRole(String[] roleIds);

    Object queryRoleInfo(String roleId);
}
