package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.cotiinformatica.entities.Atendimento;
import br.com.cotiinformatica.entities.Medico;
import br.com.cotiinformatica.entities.Paciente;
import br.com.cotiinformatica.entities.ReceitaMedica;
import br.com.cotiinformatica.repositories.IAtendimentoRepository;
import br.com.cotiinformatica.repositories.IMedicoRepository;
import br.com.cotiinformatica.repositories.IPacienteRepository;
import br.com.cotiinformatica.requests.AtendimentoPostRequest;
import br.com.cotiinformatica.requests.ReceitaMedicaPostRequest;
import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class AtendimentoController {

	@Autowired
	private IPacienteRepository pacienteRepository;

	@Autowired
	private IMedicoRepository medicoRepository;

	@Autowired
	private IAtendimentoRepository atendimentoRepository;

	@ApiOperation("Serviço para cadastro de atendimentos.")
	@RequestMapping(value = "/api/atendimentos", method = RequestMethod.POST)
	@CrossOrigin
	public ResponseEntity<String> post(@RequestBody AtendimentoPostRequest request) {

		try {
			
			//obter os dados do médico
			Optional<Medico> medico = medicoRepository.findById(request.getIdMedico());
			if(medico.isEmpty())
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Médico não encontrado, verifique o ID informado.");
			
			//obter os dados do paciente
			Optional<Paciente> paciente = pacienteRepository.findById(request.getIdPaciente());
			if(paciente.isEmpty())
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Paciente não encontrado, verifique o ID informado.");
			
			//cadastrando o atendimento
			Atendimento atendimento = new Atendimento();
			
			atendimento.setDataHora(request.getDataHora());
			atendimento.setMedico(medico.get());
			atendimento.setPaciente(paciente.get());
			
			List<ReceitaMedica> receitas = new ArrayList<ReceitaMedica>();
			for(ReceitaMedicaPostRequest item : request.getReceitas()) {
				
				ReceitaMedica receitaMedica = new ReceitaMedica();
				receitaMedica.setMedicamento(item.getMedicamento());
				receitaMedica.setPrescricao(item.getPrescricao());
				
				receitas.add(receitaMedica);
			}
			
			atendimento.setReceitas(receitas);
			
			//gravando o atendimento no banco de dados
			atendimentoRepository.save(atendimento);
			
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body("Atendimento cadastrado com sucesso!");
		}
		catch(Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}
	
	@ApiOperation("Serviço para consulta de atendimentos.")
	@RequestMapping(value = "/api/atendimentos", method = RequestMethod.GET)
	@CrossOrigin
	public ResponseEntity<List<Atendimento>> getAll() {
		
		try {
			
			List<Atendimento> atendimentos = atendimentoRepository.findAll();
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(atendimentos);
		}
		catch(Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}	
	}

}



