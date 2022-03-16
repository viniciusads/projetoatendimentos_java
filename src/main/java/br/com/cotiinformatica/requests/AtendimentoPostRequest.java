
package br.com.cotiinformatica.requests;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AtendimentoPostRequest {

	private Date dataHora;
	private Integer idMedico;
	private Integer idPaciente;
	private List<ReceitaMedicaPostRequest> receitas;
}
