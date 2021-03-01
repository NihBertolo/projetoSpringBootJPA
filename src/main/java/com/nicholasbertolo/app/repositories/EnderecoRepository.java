package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
