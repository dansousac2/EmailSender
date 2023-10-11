package com.learning.emailsender.controllers;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.util.ReflectionTestUtils;

import com.learning.emailsender.services.SendEmailService;

class EmailSenderControllerTest {
	
	@InjectMocks
	private static EmailSenderController controller;
	
	@Spy
	private static SendEmailService service;

	@BeforeAll
	static void setUp() throws Exception {
		controller = new EmailSenderController();
		service = new SendEmailService();
		
		ReflectionTestUtils.setField(controller, "emailService", service);
	}
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
