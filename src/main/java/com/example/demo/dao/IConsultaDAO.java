package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Consulta;
import com.example.demo.model.Medico;

@Repository
public interface IConsultaDAO extends JpaRepository<Consulta, Integer> {

}
