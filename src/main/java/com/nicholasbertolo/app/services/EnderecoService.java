package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.entities.Endereco;
import com.nicholasbertolo.app.repositories.EnderecoRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	EnderecoRepository repository;
	
	public List<Endereco> findAll() {
		return repository.findAll();
	}
	
	public Endereco findById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	
	public Endereco insert (Endereco obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Endereco update(Long id, Endereco obj) {
		try {
			Endereco entity = repository.getOne(id);
			update(entity,obj);
			return repository.save(entity);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void update(Endereco entity, Endereco obj) {
		entity.setLogradouro(obj.getLogradouro());
		entity.setNumero(obj.getNumero());
		entity.setComplemento(obj.getComplemento());
		entity.setBairro(obj.getBairro());
		entity.setCep(obj.getCep());
		entity.setCidade(obj.getCidade());	
	}
}
