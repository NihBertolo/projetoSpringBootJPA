package com.nicholasbertolo.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import com.nicholasbertolo.app.entities.Usuario;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=120, message="O tamanho do nome deve ser entre 5 e 120 caracteres.")
	private String name;
	
	@NotEmpty(message="Preenchimento de e-mail obrigatório.")
	@Email(message="Formato de e-mail inválido.")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=10, max=28, message="O número de telefone deve ter entre 10 e 28 caracteres.")
	private String phone;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
		phone = obj.getPhone();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
