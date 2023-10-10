package com.learning.emailsender.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.emailsender.services.SendEmailService;

@RestController
@RequestMapping("api/emailsender")
public class EmailSenderController {
	
	@Autowired
	private SendEmailService emailService;

	@GetMapping
	public ResponseEntity sendEmail() {
		try {
			emailService.sendEmail();
			return ResponseEntity.ok("Processo de envio concluído");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
