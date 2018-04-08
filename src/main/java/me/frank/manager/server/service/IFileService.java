package me.frank.manager.server.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by frank on 2018/3/6.
 */
public interface IFileService {

    Object uploadFile(MultipartFile file);

}
