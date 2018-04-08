package me.frank.manager.server.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import me.frank.manager.controller.vo.order.QueryConfirmDemandInfoVo;
import me.frank.manager.controller.vo.order.QueryMyDemandChargeVo;
import me.frank.manager.controller.vo.Result;
import me.frank.manager.logger.BaseLogger;
import me.frank.manager.server.mapper.AdminInfoMapper;
import me.frank.manager.server.mapper.DemandInfoMapper;
import me.frank.manager.server.mapper.bo.AdminInfo;
import me.frank.manager.server.mapper.bo.DemandInfo;
import me.frank.manager.server.service.IOrderService;
import me.frank.manager.server.util.EmailUtils;
import me.frank.manager.server.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by frank on 2018/3/6.
 */
@Service("orderService")
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private DemandInfoMapper demandInfoMapper;

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public Object addOrUpDemand(String demandId, String adminId, String demandTitle, String demandContent) {
        Result res = new Result();

        try {

            if (StringUtils.isEmpty(demandId)) {

                DemandInfo demandInfo = new DemandInfo();
                demandInfo.setPubAdminId(Integer.valueOf(adminId));
                demandInfo.setDemandTitle(demandTitle);
                demandInfo.setDemandContent(demandContent);
                demandInfo.setIsConfirm(0);
                demandInfo.setCreateTime(new Date());

                demandInfoMapper.insertSelective(demandInfo);

                String title = "您有一份新的客户需求";
                String text = "您有一份新的客户需求,请及时确认";
                new Thread(() -> EmailUtils.sendTextEmail(EmailUtils.user, title, text)).start();

            } else {

                DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(Integer.valueOf(demandId));

                demandInfo.setDemandTitle(demandTitle);
                demandInfo.setDemandContent(demandContent);
                demandInfo.setUpdateTime(new Date());
                demandInfoMapper.updateByPrimaryKeySelective(demandInfo);

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
    public Object queryDemand(String demandId) {
        Result res = new Result();

        try {

            DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(Integer.valueOf(demandId));

            if (demandInfo == null) {

                res.setCode(Result.failCode);
                res.setMsg("需求id不存在");
                return res;

            }

            res.setCode(Result.successCode);
            res.setMsg(Result.successMsg);
            res.setData(demandInfo);

        } catch (Exception e) {

            BaseLogger.error("异常", e);
            res.setCode(Result.failCode);
            res.setMsg("服务器异常");
        }

        return res;
    }

    @Override
    public Object queryDemandList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime) {
        Result res = new Result();

        try {
            searchStartTime = TimeUtils.dateToStamp(searchStartTime);
            searchEndTime = TimeUtils.dateToStamp(searchEndTime);

            PageHelper.startPage(Integer.parseInt(nowPage), Integer.parseInt(pageSize));
            List<DemandInfo> list = demandInfoMapper.selectDemandList(searchField, searchValue,
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
    public Object queryConfirmDemandList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime) {
        Result res = new Result();

        try {
            searchStartTime = TimeUtils.dateToStamp(searchStartTime);
            searchEndTime = TimeUtils.dateToStamp(searchEndTime);

            PageHelper.startPage(Integer.parseInt(nowPage), Integer.parseInt(pageSize));
            List<QueryConfirmDemandInfoVo> list = demandInfoMapper.selectConfirmDemandList(searchField, searchValue,
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
    public Object confirmDemand(String adminId, String demandId, String isConfirm, String chargeAdminId) {
        Result res = new Result();

        try {

            DemandInfo demandInfo = demandInfoMapper.selectByPrimaryKey(Integer.valueOf(demandId));

            if (demandInfo == null) {
                res.setCode(Result.failCode);
                res.setMsg("需求不存在");
                return res;
            }
            if (!demandInfo.getIsConfirm().equals(0)) {

                res.setCode(Result.failCode);
                res.setMsg("该需求已审核,请勿重复操作");
                return res;

            }

            demandInfo.setConfirmAdminId(Integer.valueOf(adminId));
            demandInfo.setIsConfirm(Integer.valueOf(isConfirm));
            demandInfo.setConfirmTime(new Date());
            demandInfo.setChargeAdminId(Integer.valueOf(chargeAdminId));
            demandInfoMapper.updateByPrimaryKeySelective(demandInfo);

            String pubAdminId = demandInfo.getPubAdminId().toString();

            AdminInfo pubAdminInfo = adminInfoMapper.selectAdminInfoByIdAndAccount(pubAdminId, null);

            AdminInfo chargeAdminInfo = adminInfoMapper.selectAdminInfoByIdAndAccount(chargeAdminId, null);

            String address = pubAdminInfo.getEmail();

            String confirmRes = isConfirm.equals("1") ? "通过" : "未通过";

            //有自动受理的订单，发邮件给聂勤、马锦锦
            String title = "客户需求审核结果";
            String text = "您提交的客户需求已成功审核,审核结果为:" + confirmRes;

            new Thread(() -> EmailUtils.sendTextEmail(address, title, text)).start();

            String address2 = chargeAdminInfo.getEmail();
            String title2 = "您有一份新的项目进度计划需要处理";
            String text2 = "您有一份新的项目进度计划需要处理,请登录系统查看";

            new Thread(() -> EmailUtils.sendTextEmail(address2, title2, text2)).start();


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
    public Object queryMyDemandChargeList(String chargeAdminId, String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime) {
        Result res = new Result();

        try {
            searchStartTime = TimeUtils.dateToStamp(searchStartTime);
            searchEndTime = TimeUtils.dateToStamp(searchEndTime);

            PageHelper.startPage(Integer.parseInt(nowPage), Integer.parseInt(pageSize));
            List<QueryMyDemandChargeVo> list = demandInfoMapper.selectChargeDemandList(chargeAdminId, searchField, searchValue,
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
}
