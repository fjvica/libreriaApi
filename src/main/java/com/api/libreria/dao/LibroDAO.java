package com.api.libreria.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.libreria.entity.Libro;

public interface LibroDAO extends JpaRepository<Libro, Long>{

}
