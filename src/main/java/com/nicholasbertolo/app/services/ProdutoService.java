package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.entities.Produto;

import com.nicholasbertolo.app.repositories.ProdutoRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public Produto findById (Long id) {
		Optional<Produto> obj = produtoRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Produto insert(Produto obj) {
		return produtoRepository.save(obj);
	}
	
	public void delete (Long id) {
		try {
		produtoRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Produto update(Long id, Produto obj) {
		try {
		Produto entity = produtoRepository.getOne(id);
		updateData(entity, obj);
		return produtoRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void updateData(Produto entity, Produto obj) {
		entity.setName(obj.getName());
		entity.setDescription(obj.getDescription());
		entity.setPrice(obj.getPrice());
		entity.setImgUrl(obj.getImgUrl());
		
	}
	
}
