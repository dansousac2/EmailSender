package com.learning.emailsender.enums;

public enum SmtpMail {

	MICROSOFFT("smtp-mail.outlook.com");
	
	public final String value;
	
	SmtpMail(String value) {
		this.value = value;
	}
}
