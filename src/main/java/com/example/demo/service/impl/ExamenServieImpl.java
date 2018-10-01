package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.IExamenDAO;
import com.example.demo.model.Examen;
import com.example.demo.service.IExamenService;

@Service
public class ExamenServieImpl implements IExamenService{

	@Autowired
	private IExamenDAO examenDAO;
	
	@Override
	public Examen registrar(Examen t) {
		return examenDAO.save(t);
	}

	@Override
	public Examen modificar(Examen t) {
		return examenDAO.save(t);
	}

	@Override
	public void eliminar(int id) {
		examenDAO.delete(id);
	}

	@Override
	public Examen listar(int id) {
		return examenDAO.findOne(id);
	}

	@Override
	public List<Examen> listar() {
		return examenDAO.findAll();
	}

}
