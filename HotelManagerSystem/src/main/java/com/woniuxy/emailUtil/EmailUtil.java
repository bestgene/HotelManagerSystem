
package com.woniuxy.emailUtil;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EmailUtil implements Runnable{
	    private String email;// 收件人邮箱
	    private String user_acc;
	    private String user_pwd;

	public EmailUtil(String email, String user_acc, String user_pwd) {
			super();
			this.email = email;
			this.user_acc = user_acc;
			this.user_pwd = user_pwd;
		}




	@Override
	public void run() {
		String from = "464584097@qq.com";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)
 
        Properties properties = System.getProperties();// 获取系统属性
 
        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证
 
        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
 
 
            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "dcwkeisfpzzncacg"); // 发件人邮箱账号、授权码
                }
            });
 
            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>点击下列链接进行验证</h1><h3><a href='http://192.168.8.194:8080/HotelManagerSystem/user/register?user_acc="
                    + user_acc+"&user_pwd="+user_pwd+"'>http://192.168.8.194:8080/HotelManagerSystem/user/register?user_acc="+ user_acc+"&user_pwd="+user_pwd;
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");      
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}

