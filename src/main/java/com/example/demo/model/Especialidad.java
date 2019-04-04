package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "especialidad")
public class Especialidad {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEspecialidad;
	
	@ApiModelProperty(notes = "El nombre de la especialidad debe tener minimo 3 caracteres")
	@Size(min = 3, message = "El nombre de la especialidad debe tener minimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;

	public int getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
