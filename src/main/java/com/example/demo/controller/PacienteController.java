package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.service.IPacienteService;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Paciente;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;

	/**
	 * 
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> listar() {
		List<Paciente> pacientes = new ArrayList<>();
		pacientes = pacienteService.listar();
		return new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Paciente> listarId(@PathVariable("id") Integer id) {
		Paciente paciente = pacienteService.listar(id);
		return new ResponseEntity<Paciente>(paciente, HttpStatus.OK);
	}

	/**
	 * 
	 * @param paciente
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Paciente paciente) {

		/**
		 * { "nombres":"ana belen" , "apellidos": "grimaut", "dni":"12345678",
		 * "direccion":"algo", "telefono":"123456789"
		 * 
		 * }
		 */
		Paciente pac = new Paciente();
		pac = pacienteService.registrar(paciente);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(pac.getIdPaciente())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	/**
	 * 
	 * @param paciente
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Paciente paciente) {
		pacienteService.modificar(paciente);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Paciente paciente = pacienteService.listar(id);
		
		if(paciente!=null) {
			
			throw new ModeloNotFoundException("ID:" + id);
			
		}
		else {
			pacienteService.eliminar(id);
		}
	}
}
