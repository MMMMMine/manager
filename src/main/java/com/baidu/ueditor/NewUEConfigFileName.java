package com.baidu.ueditor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewUEConfigFileName {


    //Spring注入静态变量
    public static String ueditorConfigFileName;
    @Value("${ueditor.config.fileName}")
    public void setUeditorConfigFileName(String ueditorConfigFileName) {
        NewUEConfigFileName.ueditorConfigFileName = ueditorConfigFileName;
    }

}
