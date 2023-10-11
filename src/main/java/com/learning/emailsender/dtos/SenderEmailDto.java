package com.learning.emailsender.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SenderEmailDto {

	@NotEmpty(message = "A lista de destinatários está vazia")
	private String emailsList[];
	
	@NotBlank(message = "Assunto não pode ser nulo")
	private String subject;
	
	@NotBlank(message = "Mensagem não pode ser  nula")
	private String msg;
}
