package me.frank.manager.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.frank.manager.controller.vo.Result;
import me.frank.manager.logger.BaseLogger;
import me.frank.manager.server.mapper.RoleInfoMapper;
import me.frank.manager.server.mapper.bo.RoleInfo;
import me.frank.manager.server.service.IRoleService;
import me.frank.manager.server.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by frank on 2018/2/27.
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleInfoMapper roleInfoMapper;

    @Override
    public Object queryRoleList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime) {
        Result res = new Result();

        try {
            searchStartTime = TimeUtils.dateToStamp(searchStartTime);
            searchEndTime = TimeUtils.dateToStamp(searchEndTime);

            PageHelper.startPage(Integer.parseInt(nowPage), Integer.parseInt(pageSize));
            List<RoleInfo> list = roleInfoMapper.selectRoleList(searchField, searchValue,
                    searchStartTime, searchEndTime);

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
    public Object addOrUpdateRole(String roleId, String roleName, String roleAuth) {
        Result res = new Result();

        try {
            if (StringUtils.isEmpty(roleId)) {

                RoleInfo roleInfo = new RoleInfo();
                roleInfo.setRoleName(roleName);
                roleInfo.setRoleAuth(roleAuth);
                roleInfo.setCreateTime(new Date());

                roleInfoMapper.insertSelective(roleInfo);

            } else {

                RoleInfo roleInfo = roleInfoMapper.selectByPrimaryKey(Integer.valueOf(roleId));
                roleInfo.setRoleName(roleName);
                roleInfo.setRoleAuth(roleAuth);
                roleInfo.setUpdateTime(new Date());

                roleInfoMapper.updateByPrimaryKeySelective(roleInfo);

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
    public Object delRole(String[] roleIds) {
        Result res = new Result();

        try {

            if (StringUtils.isEmpty(roleIds)) {

                res.setCode(Result.failCode);
                res.setMsg("参数异常");
                return res;

            }

            for (String id : roleIds) {
                roleInfoMapper.deleteByPrimaryKey(id);
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
    public Object queryRoleInfo(String roleId) {
        Result res = new Result();

        try {

            RoleInfo roleInfo = roleInfoMapper.selectByPrimaryKey(Integer.valueOf(roleId));

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);
            res.setData(roleInfo);

        } catch (Exception e) {

            BaseLogger.error("异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }
}
