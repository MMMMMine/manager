package me.frank.manager.server.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebUtils {
    public static String getRemoteIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip!=null && (ip.indexOf(","))>0){
            ip = ip.substring(ip.indexOf(","));
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * 获取客户IP
     * @param request
     * @return
     */
    public static String getClientIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        String proxy=request.getHeader("Client-IP");
        if( proxy == null || proxy.length() == 0 || "unknown".equalsIgnoreCase(proxy) )
            proxy=request.getHeader("x-forwarded-for");

        if( proxy == null || proxy.length() == 0 || "unknown".equalsIgnoreCase(proxy) )
            proxy=request.getHeader("Proxy-Client-IP");

        if( proxy == null || proxy.length() == 0 || "unknown".equalsIgnoreCase(proxy) )
            proxy = request.getHeader("WL-Proxy-Client-IP");

        if(proxy != null && proxy.length() > 0 && !"unknown".equalsIgnoreCase(proxy))
            ipAddress = proxy.split(",")[0];

        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }

        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

}
