package com.learning.emailsender.services;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendEmailService {

	@Value("${email.acount}")
	private String email;

	@Value("${email.password}")
	private String password;

	public void sendEmail() throws MessagingException {
		System.out.println(email);
		System.out.println(password);

		Properties props = new Properties();
		//props.put("mail.debug", "true");
		props.put("mail.smtp.host", "smtp-mail.outlook.com");
		// comando reconhecido por outros protocolos como IMAP, POP e SMTP.
		// utilizado para transformar uma ligação não encriptada numa ligação encriptada sem a necessidade de recorrer a uma porta segura especifica.
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props, null);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom("dansousac2@hotmail.com");
		msg.setRecipients(Message.RecipientType.TO, "dansousac2@gmail.com");
		msg.setSubject("JavaMail hello world example");
		msg.setSentDate(new Date());
		msg.setText("Hello, world!\n");

		Transport.send(msg, email, password);
	}
}
