package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.IEspecialidadDAO;
import com.example.demo.model.Especialidad;
import com.example.demo.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

	private IEspecialidadDAO especialidadDAO;
	
	@Override
	public Especialidad registrar(Especialidad t) {
		return especialidadDAO.save(t);
	}

	@Override
	public Especialidad modificar(Especialidad t) {
		return especialidadDAO.save(t);
	}

	@Override
	public void eliminar(int id) {
		especialidadDAO.delete(id);
	}

	@Override
	public Especialidad listar(int id) {
		return especialidadDAO.findOne(id);
	}

	@Override
	public List<Especialidad> listar() {
		return especialidadDAO.findAll();
	}

}
