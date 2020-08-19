package com.carel.report.mail;

import java.util.Date;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.carel.report.Config;

public class SmtpClient {

	private MailConfigInfo info;

	public SmtpClient() {
		init();
	}

	private void init() {
		this.info = new MailConfigInfo(Config.MAIL_USR, Config.MAIL_PWD);
		this.info.setHost(Config.MAIL_SVR);
		this.info.setPort(Config.MAIL_PORT);
		this.info.setMailFrom(Config.MAIL_FROM);
		this.info.setMailTo(Config.MAIL_TO);

	}

	public void send(String subject, String content) throws MessagingException {
		Session sendMailSession = Session.getDefaultInstance(
				info.getProperties(), info.getAuthenticator());

		Message mailMessage = new MimeMessage(sendMailSession);
		mailMessage.setFrom(info.getMailFrom());
		mailMessage.setRecipient(Message.RecipientType.TO, info.getMailTo());
		mailMessage.setSubject(subject);
		mailMessage.setSentDate(new Date());

		Multipart mainPart = new MimeMultipart();
		BodyPart html = new MimeBodyPart();
		html.setContent(content, "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);

	}
	
	public static void send(){
		//TODO
	}
	
}
