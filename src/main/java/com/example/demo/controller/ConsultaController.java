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

import com.example.demo.service.IConsultaService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Consulta;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;;

@RestController
@RequestMapping("/consultas")
@Api(value="Servicio REST para los consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService consultaService;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value="Retorna una lista de consultas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Consulta>> listar() {
		List<Consulta> consultas = new ArrayList<>();
		consultas = consultaService.listar();
		return new ResponseEntity<List<Consulta>>(consultas, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Resource<Consulta> listarId(@PathVariable("id") Integer id) {
		Consulta consulta = consultaService.listar(id);
		if(consulta == null) {
			throw new ModeloNotFoundException("ID" + id);
		} 
		Resource<Consulta> resource = new Resource<Consulta>(consulta);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		
		resource.add(linkTo.withRel("consulta-resource"));
 		//return new ResponseEntity<Consulta>(consulta, HttpStatus.OK);
		
		return resource;
	}

	/**
	 * 
	 * @param consulta
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Consulta consulta) {

		/**
		 * { "nombres":"ana belen" , "apellidos": "grimaut", "dni":"12345678",
		 * "direccion":"algo", "telefono":"123456789"
		 * 
		 * }
		 */
		Consulta con = new Consulta();
		con = consultaService.registrar(consulta);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(con.getIdConsulta())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	/**
	 * 
	 * @param consulta
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Consulta consulta) {
		consultaService.modificar(consulta);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Consulta consulta = consultaService.listar(id);

		if (consulta != null) {
			throw new ModeloNotFoundException("ID:" + id);

		} else {
			consultaService.eliminar(id);
		}
	}
}
