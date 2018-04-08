package me.frank.manager.server.mapper;

import me.frank.manager.server.mapper.bo.AdminInfo;
import me.frank.manager.server.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper extends MyMapper<AdminInfo> {

    AdminInfo selectAdminInfoByAccount(@Param("account") String account);

    AdminInfo selectAdminInfoByIdAndAccount(@Param("adminId") String adminId,
                                            @Param("account") String account);

    List<AdminInfo> selectAdminList(@Param("searchField") String searchField,
                                    @Param("searchValue") String searchValue,
                                    @Param("searchStartTime") String searchStartTime,
                                    @Param("searchEndTime") String searchEndTime);
}