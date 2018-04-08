package me.frank.manager.server.mapper;

import me.frank.manager.controller.vo.order.QueryConfirmDemandInfoVo;
import me.frank.manager.controller.vo.order.QueryMyDemandChargeVo;
import me.frank.manager.server.mapper.bo.DemandInfo;
import me.frank.manager.server.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DemandInfoMapper extends MyMapper<DemandInfo> {

    List<DemandInfo> selectDemandList(@Param("searchField") String searchField,
                                      @Param("searchValue") String searchValue,
                                      @Param("searchStartTime") String searchStartTime,
                                      @Param("searchEndTime") String searchEndTime);

    List<QueryConfirmDemandInfoVo> selectConfirmDemandList(@Param("searchField") String searchField,
                                                           @Param("searchValue") String searchValue,
                                                           @Param("searchStartTime") String searchStartTime,
                                                           @Param("searchEndTime") String searchEndTime);

    List<QueryMyDemandChargeVo> selectChargeDemandList(@Param("chargeAdminId") String chargeAdminId,
                                                       @Param("searchField") String searchField,
                                                       @Param("searchValue") String searchValue,
                                                       @Param("searchStartTime") String searchStartTime,
                                                       @Param("searchEndTime") String searchEndTime);
}