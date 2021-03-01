package com.nicholasbertolo.app.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nicholasbertolo.app.entities.Endereco;
import com.nicholasbertolo.app.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll() {
		List<Endereco> endereco = enderecoService.findAll();
		return ResponseEntity.ok().body(endereco);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Long id) {
		Endereco obj = enderecoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Endereco> insert (@RequestBody Endereco obj) {
		obj = enderecoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		enderecoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody Endereco obj) {
		obj = enderecoService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
