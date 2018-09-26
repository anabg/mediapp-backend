package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.IPacienteDAO;
import com.example.demo.model.Paciente;
import com.example.demo.service.IPacienteService;

public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteDAO pacienteDAO;
	
	@Override
	public Paciente registrar(Paciente t) {
		return pacienteDAO.save(t);
	}

	@Override
	public Paciente modificar(Paciente t) {
		return pacienteDAO.save(t);
	}

	@Override
	public void eliminar(int id) {
		pacienteDAO.delete(id);
	}

	@Override
	public Paciente listar(int id) {
		return pacienteDAO.findOne(id);
	}

	@Override
	public List<Paciente> listar() {
		return pacienteDAO.findAll();
	}

}
