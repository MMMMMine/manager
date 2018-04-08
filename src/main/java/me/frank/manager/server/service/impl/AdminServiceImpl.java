package me.frank.manager.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.frank.manager.controller.vo.Result;
import me.frank.manager.logger.BaseLogger;
import me.frank.manager.server.mapper.AdminInfoMapper;
import me.frank.manager.server.mapper.bo.AdminInfo;
import me.frank.manager.server.service.IAdminService;
import me.frank.manager.server.util.*;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service("adminService")
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public Object adminLogin(String account, String password, String token, String saveTime, HttpServletRequest request, HttpServletResponse response) {

        Result res = new Result();
        try {
            if (account == null || "".equals(account)) {
                res.setCode(Result.failCode);
                res.setMsg("用户名不能为空");
                return res;
            }
            if (saveTime == null || "".equals(saveTime)) {
                saveTime = "0";
            }
            if (password == null || "".equals(password)) {
                //密码为空，尝试token登陆
                if (token == null || "".equals(token)) {
                    //token为空，返回错误
                    res.setCode(Result.failCode);
                    res.setMsg("密码不能为空");
                    return res;
                }
                //token登陆
                AdminInfo adminInfo = adminInfoMapper.selectAdminInfoByAccount(account);
                if (adminInfo == null) {
                    //管理员account不存在
                    res.setCode(Result.failCode);
                    res.setMsg("管理员" + account + "不存在");
                    return res;
                } else if (token.equals(adminInfo.getToken())) {
                    //token正确，登陆成功
                    //修改登陆次数
                    int loginCount = adminInfo.getLoginCount() + 1;
                    adminInfo.setLoginCount(loginCount);
                    //修改上次登陆ip、上次登陆时间、本次登陆ip、本次登陆时间
                    String loginLastIp = adminInfo.getLoginCurrIp();
                    Date loginLastTime = adminInfo.getLoginCurrTime();
                    String loginCurrIp = WebUtils.getClientIpAddr(request);
                    Date loginCurrTime = DateTime.now().toDate();
                    adminInfo.setLoginCurrIp(loginCurrIp);
                    adminInfo.setLoginCurrTime(loginCurrTime);
                    adminInfo.setLoginLastIp(loginLastIp);
                    adminInfo.setLoginLastTime(loginLastTime);

                    adminInfoMapper.updateByPrimaryKeySelective(adminInfo);

                } else {
                    //token错误，登陆失败
                    res.setCode(Result.failCode);
                    res.setMsg("登录信息无效");
                    return res;
                }
            } else {
                //密码登陆
                AdminInfo adminInfo = adminInfoMapper.selectAdminInfoByAccount(account);
                if (adminInfo == null) {
                    //管理员account不存在
                    res.setCode(Result.failCode);
                    res.setMsg("管理员" + account + "不存在");
                    return res;
                } else if (password.equals(adminInfo.getPassword())) {
                    //密码正确，登陆成功
                    //修改token
                    String newToken = newToken();
                    adminInfo.setToken(newToken);
                    //修改登陆次数
                    int loginCount = adminInfo.getLoginCount() + 1;
                    adminInfo.setLoginCount(loginCount);
                    //修改上次登陆ip、上次登陆时间、本次登陆ip、本次登陆时间
                    String loginLastIp = adminInfo.getLoginCurrIp();
                    Date loginLastTime = adminInfo.getLoginCurrTime();
                    String loginCurrIp = WebUtils.getClientIpAddr(request);
                    Date loginCurrTime = DateTime.now().toDate();
                    adminInfo.setLoginCurrIp(loginCurrIp);
                    adminInfo.setLoginCurrTime(loginCurrTime);
                    adminInfo.setLoginLastIp(loginLastIp);
                    adminInfo.setLoginLastTime(loginLastTime);

                    adminInfoMapper.updateByPrimaryKeySelective(adminInfo);

                    int maxAge = 0;
                    if (ValidateUtil.isPostiveInteger(saveTime) || "0".equals(saveTime)) {
                        maxAge = Integer.parseInt(saveTime);
                    } else {
                        BaseLogger.error("admin登陆saveTime参数为：" + saveTime + ",非整数");
                    }
                    //修改cookie
                    CookieUtils.addCookie(response, "website-account", account, maxAge);
                    CookieUtils.addCookie(response, "website-adminId", String.valueOf(adminInfo.getAdminId()), maxAge);
                    CookieUtils.addCookie(response, "website-roleId", String.valueOf(adminInfo.getRoleId()), maxAge);
                    CookieUtils.addCookie(response, "website-token", newToken, maxAge);
                } else {
                    //密码错误，登陆失败
                    res.setCode(Result.failCode);
                    res.setMsg("密码错误");
                    return res;
                }
            }

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);
        } catch (Exception e) {

            BaseLogger.error("admin登陆异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }


    @Override
    public Object queryAdminInfo(String adminId, String account) {

        Result res = new Result();

        try {
            if ((adminId == null || "".equals(adminId)) &&
                    (account == null || "".equals(account))) {
                res.setCode(Result.failCode);
                res.setMsg("管理员ID和管理员账号不能都为空");
                return res;
            }

            AdminInfo adminInfo = adminInfoMapper.selectAdminInfoByIdAndAccount(adminId, account);

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);
            res.setData(adminInfo);
        } catch (Exception e) {

            BaseLogger.error("admin查询异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }

    @Override
    public Object queryAdminList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime) {
        Result res = new Result();

        try {
            searchStartTime = TimeUtils.dateToStamp(searchStartTime);
            searchEndTime = TimeUtils.dateToStamp(searchEndTime);

            PageHelper.startPage(Integer.parseInt(nowPage), Integer.parseInt(pageSize));
            List<AdminInfo> list = adminInfoMapper.selectAdminList(searchField, searchValue,
                    searchStartTime, searchEndTime);
            for (AdminInfo item : list) {

                int roleId = item.getRoleId();


            }
            PageInfo pageInfo = new PageInfo(list);

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);
            res.setData(pageInfo);

        } catch (Exception e) {

            BaseLogger.error("异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }


    @Override
    public Object addOrUpdateAdmin(String adminId, String roleId, String account, String name, String password, String phone, String email) {
        Result res = new Result();

        try {
            if (StringUtils.isEmpty(adminId)) {

                if (StringUtils.isEmpty(password)) {

                    res.setCode(Result.failCode);
                    res.setMsg("初始密码不能为空");
                    return res;

                }

                AdminInfo adminInfo = new AdminInfo();
                adminInfo.setRoleId(Integer.valueOf(roleId));
                adminInfo.setAccount(account);
                adminInfo.setName(name);
                adminInfo.setPassword(password);
                adminInfo.setPhone(phone);
                adminInfo.setEmail(email);
                adminInfo.setCreateTime(new Date());

                adminInfoMapper.insertSelective(adminInfo);

            } else {

                AdminInfo adminInfo = adminInfoMapper.selectAdminInfoByIdAndAccount(adminId, account);

                adminInfo.setRoleId(Integer.valueOf(roleId));
                adminInfo.setAccount(account);
                adminInfo.setName(name);
                if (!StringUtils.isEmpty(password)) {
                    adminInfo.setPassword(password);
                }
                adminInfo.setPhone(phone);
                adminInfo.setEmail(email);
                adminInfo.setUpdateTime(new Date());

                adminInfoMapper.updateByPrimaryKeySelective(adminInfo);

            }

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);

        } catch (Exception e) {

            BaseLogger.error("异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }

    @Override
    public Object delAdmin(String[] adminIds) {
        Result res = new Result();

        try {

            if (StringUtils.isEmpty(adminIds)) {

                res.setCode(Result.failCode);
                res.setMsg("参数异常");
                return res;

            }

            for (String id : adminIds) {

                adminInfoMapper.deleteByPrimaryKey(id);

            }

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);

        } catch (Exception e) {

            BaseLogger.error("异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }

    /**
     * 生成随机token，32位MD5
     *
     * @return
     */
    public String newToken() {
        String uuid = UUID.randomUUID().toString();
        return EncryptUtils.MD5Encode(uuid, "UTF-8");
    }

}
