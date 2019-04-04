package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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

import com.example.demo.service.IMedicoService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Medico;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;;

@RestController
@RequestMapping("/medicos")
@Api(value = "Servicio REST para los medicos")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "Retorna una lista de medicos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> listar() {
		List<Medico> medicos = new ArrayList<>();
		medicos = medicoService.listar();
		return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Resource<Medico> listarId(@PathVariable("id") Integer id) {
		Medico medico = medicoService.listar(id);
		if (medico == null) {
			throw new ModeloNotFoundException("ID" + id);
		}
		Resource<Medico> resource = new Resource<Medico>(medico);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));

		resource.add(linkTo.withRel("medico-resource"));
		// return new ResponseEntity<Medico>(medico, HttpStatus.OK);

		return resource;
	}

	/**
	 * 
	 * @param medico
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Medico medico) {

		/**
		 * { "nombres":"ana belen" , "apellidos": "grimaut", "cmp": "123456789012" }
		 */
		Medico med = new Medico();
		med = medicoService.registrar(medico);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(med.getIdMedico())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	/**
	 * 
	 * @param medico
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Medico medico) {
		medicoService.modificar(medico);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Medico medico = medicoService.listar(id);

		if (medico == null) {
			throw new ModeloNotFoundException("ID:" + id);

		} else {
			medicoService.eliminar(id);
		}
	}
}
