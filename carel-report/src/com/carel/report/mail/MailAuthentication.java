package com.carel.report.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthentication extends Authenticator {
	
	private String userName;
	private String password;

	public MailAuthentication(String userName,String password){
		this.userName = userName;
		this.password = password;
	}
	
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}
	
}
