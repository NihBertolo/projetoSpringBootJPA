package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.dto.CategoriaDTO;
import com.nicholasbertolo.app.entities.Categoria;
import com.nicholasbertolo.app.repositories.CategoriaRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categRepository;
	
	public List<Categoria> findAll() {
		return categRepository.findAll();
	}
	
	public Categoria findById (Long id) {
		Optional<Categoria> obj = categRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Categoria insert(Categoria obj) {
		return categRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			categRepository.deleteById(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Categoria update(Long id, Categoria obj) {
		try {
			Categoria entity = categRepository.getOne(id);
			updateDate(entity, obj);
			return categRepository.save(entity);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	private void updateDate(Categoria entity, Categoria obj) {
		entity.setId(obj.getId());
		entity.setName(obj.getName());	
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String derection) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(derection), orderBy);
		return categRepository.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getName());
	}
}
