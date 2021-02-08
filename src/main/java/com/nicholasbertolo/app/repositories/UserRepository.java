package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
