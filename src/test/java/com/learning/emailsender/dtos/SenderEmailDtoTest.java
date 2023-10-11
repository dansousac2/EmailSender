package com.learning.emailsender.dtos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.learning.emailsender.exceptions.EmailSignatureException;
import com.learning.emailsender.services.EmailUtilsService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class SenderEmailDtoTest {

	private SenderEmailDto dto;
	private Set<ConstraintViolation<SenderEmailDto>> violations;
	private static Validator validator;

	@BeforeAll
	static void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@BeforeEach
	void beforeEach() {
		dto = new SenderEmailDto();
	}

	// ok
	@Test
	void emailValid() {
		String emails[] = { "dansousac2@gmail.com", "abc@hotmail.com.br", "123@bol.com.br", "123@outlook.com" };
		dto.setEmailsList(emails);

		violations = validator.validateProperty(dto, "emailsList");

		String violation = "";
		if (violations.size() != 0) {
			violation = violations.stream().findFirst().get().getMessage();
		}
		assertEquals(0, violations.size(), "Email inválido encontrado\n" + violation);

		assertDoesNotThrow(() -> {
			EmailUtilsService.verifyEmails(emails);
		});
	}
	
	// invalid emails
	@ParameterizedTest
	@ValueSource(strings = { "vazia", " ", "abchotmail.com.br", "123@.com.br", "123@outlook.", "qualquer_um @gmail.com", "123qwe@hotmail" })
	void emailInvalid(String email) {
		String emails[] = {"valido@gmail.com", email};
		String emptyArray[] = {};

		dto.setEmailsList(emails);
		
		if(email.equals("vazia")) {
			dto.setEmailsList(emptyArray);
			
			violations = validator.validateProperty(dto, "emailsList");
			
			assertNotEquals(0, violations.size(), "\nA lista não está sendo considerada vazia");

		} else {
			assertThrows(EmailSignatureException.class, () -> {
				EmailUtilsService.verifyEmails(emails);
			});
		}
		
	}

}
