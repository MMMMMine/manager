package me.frank.manager.server.service.impl;

import me.frank.manager.logger.BaseLogger;
import me.frank.manager.server.mapper.FileInfoMapper;
import me.frank.manager.server.mapper.bo.FileInfo;
import me.frank.manager.server.service.IFileService;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by frank on 2018/3/6.
 */
@Service("fileService")
public class FileServiceImpl implements IFileService {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Value("${file.upload.path}")
    private String fileUploadPath;


    @Override
    public Object uploadFile(MultipartFile file) {
        Map<String, Object> res = new HashMap<>();
        try {
            String fileName = file.getOriginalFilename();

            String fileSize = String.valueOf(file.getSize());

            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

            byte[] bytes = file.getBytes();

            String path = fileUploadPath;

            //获取md5
            String md5 = getMD5(bytes);

            //根据md5判断文件是否存在
            FileInfo sysFileInfo = fileInfoMapper.selectFileByMD5(md5);

            if (sysFileInfo == null) {

                //保存文件
                path = saveFile(fileName, fileSize, fileType, bytes, md5, path);

            } else {

                //获取原路径
                path = sysFileInfo.getPath();

            }

            if (path != null && !path.equals("")) {

                res.put("code", 200);
                res.put("msg", "success");
                res.put("data", path);
            } else {
                res.put("code", 500);
                res.put("msg", "上传文件失败");
            }

        } catch (Exception e) {

            BaseLogger.error("上传文件异常", e);
            res.put("code", 500);
            res.put("msg", "服务器异常");
        }

        return res;
    }

    private String saveFile(String fileName, String fileSize, String fileType, byte[] bytes, String md5, String path) throws IOException {

        DateTime dateTime = new DateTime(new Date());

        String yyymmdd = dateTime.toString("yyyyMMdd");

        path += "/" + yyymmdd;

        File folder = new File(path);

        if (folder.exists()) {

            BaseLogger.info(path + "已存在");

        } else {

            folder.mkdir();

        }

        //文件最终名
        String finalName = md5 + "." + fileType;

        //文件最终路径
        path += "/" + finalName;

        String relativePath = path.substring(fileUploadPath.length());

        File targetFile = new File(path);

        FileUtils.writeByteArrayToFile(targetFile, bytes);

        //保存上传记录
        FileInfo sysFileInfo = new FileInfo();
        sysFileInfo.setPath(relativePath);
        sysFileInfo.setFileName(fileName);
        sysFileInfo.setFileType(fileType);
        sysFileInfo.setFileSize(fileSize);
        sysFileInfo.setMd5(md5);
        sysFileInfo.setCreateTime(new Date());
        fileInfoMapper.insertSelective(sysFileInfo);

        return relativePath;

    }


    //得到md5值的类
    private String getMD5(byte[] uploadBytes) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(uploadBytes);
        String hashString = new BigInteger(1, digest).toString(16);
        String md5_z = hashString.toUpperCase();
        return md5_z;
    }

}
