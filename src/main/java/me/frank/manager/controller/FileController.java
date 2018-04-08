package me.frank.manager.controller;

import me.frank.manager.server.service.IFileService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by frank on 2018/3/6.
 */
@RestController
@RequestMapping(value = "/manage/file", produces = "application/json", method = RequestMethod.POST)
public class FileController {

    @Autowired
    IFileService fileService;

    @RequestMapping("/uploadFile")
    Object uploadFile(MultipartFile file) {

        return fileService.uploadFile(file);

    }


}
