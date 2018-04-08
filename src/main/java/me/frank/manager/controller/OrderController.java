package me.frank.manager.controller;

import me.frank.manager.aop.annotation.NotEmpty;
import me.frank.manager.server.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by frank on 2018/3/6.
 */
@RestController
@RequestMapping(value = "/manage/order", produces = "application/json", method = RequestMethod.POST)
public class OrderController {

    @Autowired
    IOrderService orderService;

    @RequestMapping("/addOrUpDemand")
    public Object addOrUpDemand(String demandId,
                                @NotEmpty(name = "人员id") String adminId,
                                @NotEmpty(name = "需求标题") String demandTitle,
                                @NotEmpty(name = "需求内容") String demandContent) {

        return orderService.addOrUpDemand(demandId, adminId, demandTitle, demandContent);

    }

    @RequestMapping("/queryDemand")
    public Object queryDemand(@NotEmpty(name = "需求id") String demandId) {

        return orderService.queryDemand(demandId);

    }


    @RequestMapping("/queryDemandList")
    public Object queryDemandList(String nowPage, String pageSize,
                                  String searchField, String searchValue,
                                  String searchStartTime, String searchEndTime) {

        return orderService.queryDemandList(nowPage, pageSize, searchField, searchValue, searchStartTime, searchEndTime);

    }

    @RequestMapping("/queryConfirmDemandList")
    public Object queryConfirmDemandList(String nowPage, String pageSize,
                                         String searchField, String searchValue,
                                         String searchStartTime, String searchEndTime) {

        return orderService.queryConfirmDemandList(nowPage, pageSize, searchField, searchValue, searchStartTime, searchEndTime);

    }

    @RequestMapping("/confirmDemand")
    public Object confirmDemand(@NotEmpty(name = "人员id") String adminId,
                                @NotEmpty(name = "需求id") String demandId,
                                @NotEmpty(name = "是否通过") String isConfirm,
                                String chargeAdminId) {

        return orderService.confirmDemand(adminId, demandId, isConfirm, chargeAdminId);

    }

    @RequestMapping("/queryMyDemandChargeList")
    public Object queryMyDemandChargeList(String chargeAdminId, String nowPage, String pageSize,
                                          String searchField, String searchValue,
                                          String searchStartTime, String searchEndTime) {

        return orderService.queryMyDemandChargeList(chargeAdminId, nowPage, pageSize, searchField, searchValue, searchStartTime, searchEndTime);

    }

}
