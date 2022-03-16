package br.com.cotiinformatica.requests;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceitaMedicaPostRequest {

	private String medicamento;
	private String prescricao;

}
