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

import com.nicholasbertolo.app.entities.Produto;
import com.nicholasbertolo.app.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produto = produtoService.findAll();
		return ResponseEntity.ok().body(produto);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		Produto obj = produtoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping 
	public ResponseEntity<Produto> insert(@RequestBody Produto obj) {
		obj = produtoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		produtoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping
	public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto obj) {
		obj = produtoService.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
