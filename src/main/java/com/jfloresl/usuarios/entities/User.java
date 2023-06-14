package com.jfloresl.usuarios.entities;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "VARCHAR(255)")
    private UUID id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	@NonNull
	private String password;
	private LocalDate created;
	private LocalDate modified;
	private LocalDate last_login;
	private String token;
	private String isactive;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@NonNull
	private List<Phone> phones= new ArrayList<>();

	/**
	 * 
	 */
	public User() {
	}

	
	
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 */
	public User(UUID id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}



	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param password
	 * @param created
	 * @param modified
	 * @param last_login
	 * @param token
	 * @param isactiveString
	 * @param phone
	 */
	public User(UUID id, String name, String email, String password, LocalDate created, LocalDate modified, LocalDate last_login,
			String token, String isactiveString, List<Phone> phones) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created = created;
		this.modified = modified;
		this.last_login = last_login;
		this.token = token;
		this.isactive = isactiveString;
		this.phones = phones;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the created
	 */
	public LocalDate getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(LocalDate created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public LocalDate getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(LocalDate modified) {
		this.modified = modified;
	}

	/**
	 * @return the last_login
	 */
	public LocalDate getLast_login() {
		return last_login;
	}

	/**
	 * @param last_login the last_login to set
	 */
	public void setLast_login(LocalDate last_login) {
		this.last_login = last_login;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the isactive
	 */
	public String getIsactive() {
		return isactive;
	}

	/**
	 * @param isactive the isactive to set
	 */
	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	/**
	 * @return the phone
	 */
	public List<Phone> getPhones() {
		return phones;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	
	
}
