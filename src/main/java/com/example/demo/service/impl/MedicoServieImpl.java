package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IMedicoDAO;
import com.example.demo.model.Medico;
import com.example.demo.service.IMedicoService;

@Service
public class MedicoServieImpl implements IMedicoService{

	@Autowired
	private IMedicoDAO medicoDAO;
	
	@Override
	public Medico registrar(Medico t) {
		return medicoDAO.save(t);
	}

	@Override
	public Medico modificar(Medico t) {
		return medicoDAO.save(t);
	}

	@Override
	public void eliminar(int id) {
		medicoDAO.delete(id);
	}

	@Override
	public Medico listar(int id) {
		return medicoDAO.findOne(id);
	}

	@Override
	public List<Medico> listar() {
		return medicoDAO.findAll();
	}

}
