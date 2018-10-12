package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.ConsultaExamen;

public interface IConsultaExamenDAO extends JpaRepository<ConsultaExamen, Integer>{
	
	//uso modifying solo si el query implicado es DML  - insert, update, delete, - sql select
	//@Transactional
	@Modifying
	@Query(value="INSERT INTO consulta_examen(id_consulta, id_name) VALUES (:idConsulta, :idExamen)", nativeQuery=true) 
	Integer registrar(@Param("idConsulta") Integer idConsulta, @Param("idExamen") Integer idExamen);
	

}
