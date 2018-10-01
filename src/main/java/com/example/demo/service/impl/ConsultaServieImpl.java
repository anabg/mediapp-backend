package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IConsultaDAO;
import com.example.demo.model.Consulta;
import com.example.demo.service.IConsultaService;

@Service
public class ConsultaServieImpl implements IConsultaService{

	@Autowired
	private IConsultaDAO consultaDAO;
	
	@Override
	public Consulta registrar(Consulta t) {
		return consultaDAO.save(t);
	}

	@Override
	public Consulta modificar(Consulta t) {
		return consultaDAO.save(t);
	}

	@Override
	public void eliminar(int id) {
		consultaDAO.delete(id);
	}

	@Override
	public Consulta listar(int id) {
		return consultaDAO.findOne(id);
	}

	@Override
	public List<Consulta> listar() {
		return consultaDAO.findAll();
	}

}
