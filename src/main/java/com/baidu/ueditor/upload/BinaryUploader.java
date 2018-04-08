package com.baidu.ueditor.upload;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import me.frank.manager.controller.vo.Result;
import me.frank.manager.server.util.HttpClientUtil;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.*;

@Component
public class BinaryUploader {

    private static String cmsUploadUrl;

    //Spring注入静态变量
    @Value("${cms_upload_url}")
    public void setCmsUploadUrl(String cmsUploadUrl) {
        BinaryUploader.cmsUploadUrl = cmsUploadUrl;
    }

    public static final State save(HttpServletRequest request,
                                   Map<String, Object> conf) {
        FileItemStream fileStream = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
        }

        ServletFileUpload upload = new ServletFileUpload(
                new DiskFileItemFactory());

        if (isAjaxUpload) {
            upload.setHeaderEncoding("UTF-8");
        }

        try {
            FileItemIterator iterator = upload.getItemIterator(request);

            while (iterator.hasNext()) {
                fileStream = iterator.next();

                if (!fileStream.isFormField())
                    break;
                fileStream = null;
            }

            if (fileStream == null) {
                return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
            }

            String savePath = (String) conf.get("savePath");
            String originFileName = fileStream.getName();
            String suffix = FileType.getSuffixByFilename(originFileName);

            originFileName = originFileName.substring(0,
                    originFileName.length() - suffix.length());
            savePath = savePath + suffix;

            long maxSize = ((Long) conf.get("maxSize")).longValue();

            if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
            }

            savePath = PathFormat.parse(savePath, originFileName);

            String physicalPath = (String) conf.get("rootPath") + savePath;

            InputStream is = fileStream.openStream();

            //State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
            //存文件改用http存到外部服务器
            List<Map<String, Object>> files = new ArrayList<>();
            Map<String, Object> filemap = new HashMap<>();
            filemap.put("inputStream", is);
            filemap.put("fileName", originFileName + suffix);
            files.add(filemap);
            String httpRes = HttpClientUtil.postFile(cmsUploadUrl, "file", files);

            JSONObject jsonObject = JSONObject.parseObject(httpRes);
//			JSONObject jsonResult = JSONObject.parseObject(jsonObject.get("result").toString());
//			JSONObject jsonData = null;
            State storageState = null;
            if ((int) jsonObject.get("code") == Result.successCode) {
//				jsonData = JSONObject.parseObject(jsonObject.get("data").toString());
                storageState = new BaseState(true);//远端正确，返回正确信息
                //storageState.putInfo( "size", targetFile.length() );
                //storageState.putInfo( "title", targetFile.getName() );
            } else {
                storageState = new BaseState(false, "远端错误");
            }
            //============================
            is.close();

            if (storageState.isSuccess()) {
                storageState.putInfo("url", jsonObject.get("data").toString());
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", originFileName + suffix);
            }

            return storageState;
        } catch (FileUploadException e) {
            return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);

        return list.contains(type);
    }
}
