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
	 * ��ȡsessionʵ������
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
	 * �����ʼ�
	 * @param user
	 * @throws Exception
	 */
	public static void sendEmail(User user) throws Exception {
		Message message = new MimeMessage(getSession());
		message.setFrom(new InternetAddress("yydhcxxax@163.com"));
		message.setSubject("�һ�����");
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		message.setText("�𾴵�"+user.getUserName()+": ���ã����ڱ�վ��½������Ϊ��"+user.getPassWord()+" �뱣�ܺ�������룬��Ҫ����й¶����(��)��,��˷��ص�http://localhost:8080/MedicineManSys/");
		Transport.send(message);
	}
}
