package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Especialidad;

@Repository
public interface IEspecialidadDAO extends JpaRepository<Especialidad, Integer>{

}
