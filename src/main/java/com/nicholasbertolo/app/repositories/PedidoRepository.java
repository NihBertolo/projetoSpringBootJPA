package com.nicholasbertolo.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nicholasbertolo.app.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
