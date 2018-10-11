package com.example.demo.service;

import com.example.demo.dto.ConsultaListaExamenDTO;
import com.example.demo.model.Consulta;

public interface IConsultaService extends ICRUD<Consulta> {

	Consulta registrar(ConsultaListaExamenDTO consultaDTO);
	
}
