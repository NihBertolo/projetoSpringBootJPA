package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.entities.Pedido;
import com.nicholasbertolo.app.repositories.PedidoRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	
	public Pedido findById(Long id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Pedido insert(Pedido obj) {
		return pedidoRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		pedidoRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Pedido update(Long id, Pedido obj) {
		try {
		Pedido entity = pedidoRepository.getOne(id);
		updateData(entity, obj);
		return pedidoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void updateData(Pedido entity, Pedido obj) {
		entity.setPagamento(obj.getPagamento());
		entity.setPedidoStatus(obj.getPedidoStatus());
	}
}
