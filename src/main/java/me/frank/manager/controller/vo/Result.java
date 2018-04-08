package me.frank.manager.controller.vo;

/**
 * Created by frank on 2017/7/26.
 */
public class Result {

    private int code;

    private String msg;

    Object data;

    private long timeStamp;

    public static int successCode = 200;

    public static int failCode = 600;

    public static int loginCode = 610;

    public static String successMsg = "success";

    public static String failMsg = "fail";

    public static String loginMsg = "请重新登录";

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result() {
    }

    public Result(int code, String msg, long timeStamp) {
        this.code = code;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
