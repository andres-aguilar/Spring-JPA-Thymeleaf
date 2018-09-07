package com.yosh.springboot.jpa.app.models.entity;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.PrePersist;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="Clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty  // Valida que el campo no esté vacío
	private String name;
	
	@NotEmpty
	@Email	 // Valida el formato del email
	private String email;
	
	@NotEmpty
	@Column(name="last_name") // Renombra el campo en la tabla de la BD
	private String lastName;
	
	@Column(name="created_at")  // Renombra el campo en la tabla de la BD
	@Temporal(TemporalType.DATE)  // Asigna automaticamente fecha
	private Date createdAt;
	
	@PrePersist
	public void prePersist() {
		this.createdAt = new Date();
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
		
}