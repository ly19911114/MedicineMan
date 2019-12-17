package com.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.entity.User;

public class EmailUtil {
	
	/**
	 * 获取session实例对象
	 * @return
	 */
	public static Session getSession(){
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.host", "smtp.163.com");
		
		Session session = Session.getDefaultInstance(props, 
			
			new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication("yydhcxxax@163.com","827216417dxxax");
				}
		});
		session.setDebug(true);
		return session;
	}
	
	/**
	 * 发送邮件
	 * @param user
	 * @throws Exception
	 */
	public static void sendEmail(User user) throws Exception {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress("yydhcxxax@163.com"));
		message.setSubject("找回密码");
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setText("尊敬的"+user.getUserName()+": 您好！您在本站登陆的密码为："+user.getPassWord()+" 请保管好你的密码，不要轻易泄露给他(她)人,点此返回登http://localhost:8080/MedicineManSys/");
		Transport.send(message);
	}
}
