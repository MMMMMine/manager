package me.frank.manager.server.mapper;

import me.frank.manager.server.mapper.bo.FileInfo;
import me.frank.manager.server.util.MyMapper;

public interface FileInfoMapper extends MyMapper<FileInfo> {

    FileInfo selectFileByMD5(String md5);
}