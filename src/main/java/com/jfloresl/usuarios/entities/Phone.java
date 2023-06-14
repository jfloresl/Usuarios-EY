package com.jfloresl.usuarios.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author jfloresl
 *
 */
@Entity
@Table(name="phone")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_phone",nullable = false, unique = true)
    private Long id;
	private String number;
	private String citycode;
	private String contrycode;
	@ManyToOne
	private User user;
	
	public Phone() {
	}

	/**
	 * @param id
	 * @param number
	 * @param citycode
	 * @param contrycode
	 * @param user
	 */
	public Phone(Long id, String number, String citycode, String contrycode, User user) {
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
		this.user = user;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the citycode
	 */
	public String getCitycode() {
		return citycode;
	}

	/**
	 * @param citycode the citycode to set
	 */
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	/**
	 * @return the contrycode
	 */
	public String getContrycode() {
		return contrycode;
	}

	/**
	 * @param contrycode the contrycode to set
	 */
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}


	
}
