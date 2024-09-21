package com.prueba.users.infraestructure.outbound.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class Users {

	@Id
	private String usr;	
	
	@Column(name = "password")
	private String password;

}
