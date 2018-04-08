package me.frank.manager.server.util;



import me.frank.manager.logger.BaseLogger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 * Created by 77 on 2018/01/18.
 */
public class EmailUtils {

    private static String host = "smtp.exmail.qq.com";
    public static String user = "tanglinhui@kuanyinkj.com";
    private static String password = "Wolegequ88";

    public static void sendHtmlEmail(String emailAddress, String title, String html){
        sendEmail(emailAddress, title, null, html);
    }

    public static void sendTextEmail(String emailAddress, String title, String text){
        sendEmail(emailAddress, title, text, null);
    }

    public static void sendEmail(String emailAddress, String title, String text, String html){
        try {
            //从tomcat中获取JNDI容器
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            //JNDI中获取Mail的Session对象
            Session session = (Session) envCtx.lookup("mail/Session");

            //新建一个邮件
            Message message = new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(user));
            //设置多个收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));
            //设置主题
            message.setSubject(title);
            //设置文本
            if(text != null){
                message.setText(text);
            }else if(html != null){
                // MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象
                Multipart multiPart = new MimeMultipart();
                // 创建一个包含HTML内容的MimeBodyPart
                BodyPart bodyPart = new MimeBodyPart();
                // 设置html邮件消息内容
                bodyPart.setContent(html, "text/html; charset=utf-8");
                multiPart.addBodyPart(bodyPart);
                message.setContent(multiPart);
            }


            //邮件传送对象
            Transport transport = session.getTransport();
            //设置，邮件服务器地址、用户名、密码
            transport.connect(host, user, password);
            //发送邮件（邮件，地址）
            transport.sendMessage(message,  new Address[] {new InternetAddress(emailAddress)} );
            //关闭连接
            transport.close();

        } catch (Exception e) {
            BaseLogger.error("邮件发送失败", e);
        }
    }

}
