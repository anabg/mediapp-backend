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

import com.example.demo.service.IEspecialidadService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Especialidad;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;;

@RestController
@RequestMapping("/especialidades")
@Api(value="Servicio REST para los especialidades")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService especialidadService;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value="Retorna una lista de especialidads")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Especialidad>> listar() {
		List<Especialidad> especialidads = new ArrayList<>();
		especialidads = especialidadService.listar();
		return new ResponseEntity<List<Especialidad>>(especialidads, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Resource<Especialidad> listarId(@PathVariable("id") Integer id) {
		Especialidad especialidad = especialidadService.listar(id);
		if(especialidad == null) {
			throw new ModeloNotFoundException("ID" + id);
		} 
		Resource<Especialidad> resource = new Resource<Especialidad>(especialidad);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		
		resource.add(linkTo.withRel("especialidad-resource"));
 		//return new ResponseEntity<Especialidad>(especialidad, HttpStatus.OK);
		
		return resource;
	}

	/**
	 * 
	 * @param especialidad
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Especialidad especialidad) {

		/**
		 * { "nombres":"ana belen" , "apellidos": "grimaut", "dni":"12345678",
		 * "direccion":"algo", "telefono":"123456789"
		 * 
		 * }
		 */
		Especialidad esp = new Especialidad();
		esp = especialidadService.registrar(especialidad);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(esp.getIdEspecialidad())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	/**
	 * 
	 * @param especialidad
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Especialidad especialidad) {
		especialidadService.modificar(especialidad);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Especialidad especialidad = especialidadService.listar(id);

		if (especialidad != null) {
			throw new ModeloNotFoundException("ID:" + id);

		} else {
			especialidadService.eliminar(id);
		}
	}
}
