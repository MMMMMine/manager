package me.frank.manager.server.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface IAdminService {

    Object adminLogin(String account, String password, String token, String saveTime, HttpServletRequest request, HttpServletResponse response);

    Object queryAdminInfo(String adminId, String account);

    Object queryAdminList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime);

    Object addOrUpdateAdmin(String adminId, String roleId, String account, String name, String password, String phone, String email);

    Object delAdmin(String[] adminIds);
}
