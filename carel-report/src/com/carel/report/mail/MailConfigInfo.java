package com.carel.report.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class MailConfigInfo {
	private String user;
	private String password;
	private String mailTo;
	private String mailFrom;
	private String host;
	private String port;
	
	public MailConfigInfo(String user,String password){
		this.user = user;
		this.password = password;
	}
	
	public Authenticator getAuthenticator(){
		return new MailAuthentication(this.user, this.password);
	}
	
	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}
	
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public Properties getProperties(){
		Properties properties = new Properties();
		properties.put("mail.smtp.host", this.host);
		properties.put("mail.smtp.port", this.port);
		properties.put("mail.smtp.auth", true);
		return properties;
	}

	public Address getMailFrom() throws AddressException {
		return new InternetAddress(mailFrom);
	}

	public Address getMailTo() throws AddressException {
		return new InternetAddress(mailTo);
	}


}
