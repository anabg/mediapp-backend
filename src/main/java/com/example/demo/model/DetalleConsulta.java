package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class DetalleConsulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalleConsulta;
	@ManyToOne
	@JoinColumn(name="id_consulta", nullable=false)
	private Consulta consulta;
	private String diagnostico;
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
