package br.com.cotiinformatica.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.entities.Paciente;
import br.com.cotiinformatica.repositories.IPacienteRepository;
import io.swagger.annotations.ApiOperation;

@Controller
@Transactional
public class PacienteController {

	@Autowired
	private IPacienteRepository pacienteRepository;
	
	@ApiOperation("Serviço para consulta de pacientes.")
	@RequestMapping(value = "/api/pacientes", method = RequestMethod.GET)
	@ResponseBody
	@CrossOrigin
	public ResponseEntity<List<Paciente>> getAll() {
		
		try {
			
			List<Paciente> pacientes = pacienteRepository.findAll();
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(pacientes);
		}
		catch(Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
}



