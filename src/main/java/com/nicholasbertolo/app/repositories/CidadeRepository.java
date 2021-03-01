package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
