package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wordnik.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "consulta")
public class DetalleConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleConsulta;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable=false)
	private Consulta consulta;
	@ApiModelProperty(notes = "Diagnostico debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Diagnostico debe tener minimo 3 caracteres")
	@Column(name = "diagnostico", nullable = false, length = 70)
	private String diagnostico;
	@ApiModelProperty(notes = "Tratamiento debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Tratamiento debe tener minimo 3 caracteres")
	@Column(name = "tratamiento", nullable = false, length = 70)
	private String tratamiento;
	public int getIdDetalleConsulta() {
		return idDetalleConsulta;
	}
	public void setIdDetalleConsulta(int idDetalleConsulta) {
		this.idDetalleConsulta = idDetalleConsulta;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public String getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getTratamiento() {
		return tratamiento;
	}
	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}
	
	
}
