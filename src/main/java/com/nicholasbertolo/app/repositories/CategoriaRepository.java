package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
