package com.nicholasbertolo.app.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

import com.nicholasbertolo.app.dto.CategoriaDTO;
import com.nicholasbertolo.app.entities.Categoria;
import com.nicholasbertolo.app.services.CategoriaService;

@RestController
@Validated
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categService;
	
	@GetMapping
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> categoria = categService.findAll();
		List<CategoriaDTO> categoriaDTO = categoria.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(categoriaDTO);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = categService.fromDTO(objDto);
		obj = categService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		categService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> update(@Valid @PathVariable Long id, @RequestBody CategoriaDTO objDto) {
		Categoria obj = categService.fromDTO(objDto);
		obj = categService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = categService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> pageCat = categService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> pageCatDTO = pageCat.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(pageCatDTO);
		
	}
}
