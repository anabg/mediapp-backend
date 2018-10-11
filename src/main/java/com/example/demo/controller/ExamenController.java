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

import com.example.demo.service.IExamenService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.example.demo.exception.ModeloNotFoundException;
import com.example.demo.model.Examen;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;;

@RestController
@RequestMapping("/examenes")
@Api(value="Servicio REST para los examenes")
public class ExamenController {

	@Autowired
	private IExamenService examenService;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value="Retorna una lista de examens")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Examen>> listar() {
		List<Examen> examens = new ArrayList<>();
		examens = examenService.listar();
		return new ResponseEntity<List<Examen>>(examens, HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public Resource<Examen> listarId(@PathVariable("id") Integer id) {
		Examen examen = examenService.listar(id);
		if(examen == null) {
			throw new ModeloNotFoundException("ID" + id);
		} 
		Resource<Examen> resource = new Resource<Examen>(examen);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		
		resource.add(linkTo.withRel("examen-resource"));
 		//return new ResponseEntity<Examen>(examen, HttpStatus.OK);
		
		return resource;
	}

	/**
	 * 
	 * @param examen
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Examen examen) {


		/*{
		    "nombre": "Rayos x",
		    "descripcion": "con contraste"
		}*/
		
		Examen med = new Examen();
		med = examenService.registrar(examen);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(med.getIdExamen())
				.toUri();
		return ResponseEntity.created(location).build();

	}

	/**
	 * 
	 * @param examen
	 * @return
	 */
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Examen examen) {
		examenService.modificar(examen);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Examen examen = examenService.listar(id);

		if (examen != null) {
			throw new ModeloNotFoundException("ID:" + id);

		} else {
			examenService.eliminar(id);
		}
	}
}
