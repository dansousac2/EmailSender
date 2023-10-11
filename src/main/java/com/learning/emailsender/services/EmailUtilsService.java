package com.learning.emailsender.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.learning.emailsender.exceptions.EmailSignatureException;

public class EmailUtilsService {

	public static void verifyEmails(String emails[]) throws EmailSignatureException {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern patt = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher;
		
		for(String email : emails) {
			matcher = patt.matcher(email);
			if(!matcher.find()) {
				throw new EmailSignatureException("O seguinte email não está dentro dos padrões: " + email);
			}
		}
	}
}
