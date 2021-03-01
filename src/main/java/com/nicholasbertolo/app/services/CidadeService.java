package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.entities.Cidade;
import com.nicholasbertolo.app.repositories.CidadeRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> findAll() {
		return cidadeRepository.findAll();
	}
	
	public Cidade findById(Long id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public Cidade insert(Cidade obj) {
		return cidadeRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Cidade update(Long id, Cidade obj) {
		try {
			Cidade entity = cidadeRepository.getOne(id);
			updateData(entity, obj);
			return cidadeRepository.save(entity);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void updateData(Cidade entity, Cidade obj) {
		entity.setNome(obj.getNome());
		entity.setEstado(obj.getEstado());	
	}
	
}
