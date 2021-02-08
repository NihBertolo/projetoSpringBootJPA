package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {

}
