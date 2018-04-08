package me.frank.manager.server.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    /*
     * 将时间转换为时间戳,精确到秒
     */
    public static String dateToStamp(String s) {
        if(s == null || "".equals(s)){
            return null;
        }
        String res = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts/1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
}
