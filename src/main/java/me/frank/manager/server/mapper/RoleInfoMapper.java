package me.frank.manager.server.mapper;

import me.frank.manager.server.mapper.bo.RoleInfo;
import me.frank.manager.server.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper extends MyMapper<RoleInfo> {

    List<RoleInfo> selectRoleList(@Param("searchField") String searchField,
                                  @Param("searchValue") String searchValue,
                                  @Param("searchStartTime") String searchStartTime,
                                  @Param("searchEndTime") String searchEndTime);
}