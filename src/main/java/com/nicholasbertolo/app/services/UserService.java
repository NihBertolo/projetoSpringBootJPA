package com.nicholasbertolo.app.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nicholasbertolo.app.dto.UsuarioDTO;
import com.nicholasbertolo.app.entities.Usuario;
import com.nicholasbertolo.app.repositories.UserRepository;
import com.nicholasbertolo.app.services.exceptions.DatabaseException;
import com.nicholasbertolo.app.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<Usuario> findAll() {
		return userRepository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Usuario insert(Usuario obj) {
		return userRepository.save(obj);
	}
	
	public void delete(Long id) {
		try {
		userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Usuario update(Long id, Usuario obj) {
		try {
		Usuario entity = userRepository.getOne(id);
		updateData(entity, obj);
		return userRepository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}

	private void updateData(Usuario entity, Usuario obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return userRepository.findAll(pageRequest);
	}
	
	public Usuario fromDTO(UsuarioDTO objDTO) {
		return new Usuario(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), objDTO.getPhone(), null, null);
	}
}
