package com.nicholasbertolo.app.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nicholasbertolo.app.dto.UsuarioDTO;
import com.nicholasbertolo.app.entities.Usuario;
import com.nicholasbertolo.app.services.UserService;

@RestController
@RequestMapping(value = "/usuarios")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);	
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@Valid @PathVariable Long id, @RequestBody UsuarioDTO objDTO) {
		Usuario obj = service.fromDTO(objDTO);
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<UsuarioDTO>> findPage(
			@RequestParam (value = "Page", defaultValue = "0") Integer page,
			@RequestParam (value = "LinesPerPage", defaultValue = "24") Integer linesPerPage,
		    @RequestParam (value = "direction", defaultValue = "ASC") String direction,
			@RequestParam (value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Usuario> userPage = service.findPage(page, linesPerPage, direction, orderBy);
		Page<UsuarioDTO> userPageDTO = userPage.map(obj -> new UsuarioDTO(obj));
		return ResponseEntity.ok().body(userPageDTO);
	}
}
