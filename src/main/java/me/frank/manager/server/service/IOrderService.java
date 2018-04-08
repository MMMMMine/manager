package me.frank.manager.server.service;

/**
 * Created by frank on 2018/3/6.
 */
public interface IOrderService {

    Object addOrUpDemand(String demandId, String adminId, String demandTitle, String demandContent);

    Object queryDemand(String demandId);

    Object queryDemandList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime);

    Object queryConfirmDemandList(String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime);

    Object confirmDemand(String adminId, String demandId, String isConfirm, String chargeAdminId);

    Object queryMyDemandChargeList(String chargeAdminId, String nowPage, String pageSize, String searchField, String searchValue, String searchStartTime, String searchEndTime);
}
