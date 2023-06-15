package com.jfloresl.usuarios.entities;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

/**
 * @author jfloresl
 *
 */
@Entity
@Table(name="phones")
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "phone_id",nullable = false, unique = true)
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
	public Phone(Long id, String number, String citycode, String contrycode) {
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(citycode, contrycode, id, number, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Phone other = (Phone) obj;
		return Objects.equals(citycode, other.citycode) && Objects.equals(contrycode, other.contrycode)
				&& Objects.equals(id, other.id) && Objects.equals(number, other.number)
				&& Objects.equals(user, other.user);
	}

	/**
	 * @param number
	 * @param citycode
	 * @param contrycode
	 */
	public Phone(String number, String citycode, String contrycode) {
		this.number = number;
		this.citycode = citycode;
		this.contrycode = contrycode;
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
