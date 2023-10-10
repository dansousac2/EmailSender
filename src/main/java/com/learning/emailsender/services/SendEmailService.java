package com.learning.emailsender.services;

import java.util.Date;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learning.emailsender.enums.SmtpMail;

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
	
	public void sendEmail(String emailDestination[], String subject, String msgToSend) throws MessagingException {
		Properties props = new Properties();
		//props.put("mail.debug", "true");
		props.put("mail.smtp.host", SmtpMail.MICROSOFFT.value);
		// comando reconhecido por outros protocolos como IMAP, POP e SMTP.
		// utilizado para transformar uma ligação não encriptada numa ligação encriptada sem a necessidade de recorrer a uma porta segura especifica.
		props.put("mail.smtp.starttls.enable", "true");
		
		Session session = Session.getInstance(props, null);
		
		AddressToSend addressToSend[] = new AddressToSend [emailDestination.length];
		for(int i = 0; i < emailDestination.length; i++) {
			addressToSend[i] = new AddressToSend(emailDestination[i]);
		}
		
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(email);
		msg.setRecipients(Message.RecipientType.TO, addressToSend);
		msg.setSubject(subject);
		msg.setText(msgToSend);
		msg.setSentDate(new Date());

		Transport.send(msg, email, password);
	}
}
