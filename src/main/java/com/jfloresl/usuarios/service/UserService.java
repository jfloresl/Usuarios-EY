package com.jfloresl.usuarios.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.response.ResponseHandler;
import com.jfloresl.usuarios.utils.Constantes;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	public Boolean userValido(User user,Headers headers) {
		if(null!=user || null!=headers) {
			return true;
		}
		
		return false;
	}
	
	public Boolean existEmail(String email) {
		return userRepository.findByEmail(email).isEmpty();
	}

	public ResponseEntity<Object> createdUser(User user) {
		if(!emailFormat(user.getEmail())) {
			return ResponseHandler.generateResponse(Constantes.emailInvalid, HttpStatus.BAD_REQUEST);
		}
		if(!existEmail(user.getEmail())) {
			return ResponseHandler.generateResponse(Constantes.emailExistente, HttpStatus.BAD_REQUEST);
		}
		if(!checkPasswordFormat(user.getPassword())) {
			return ResponseHandler.generateResponse(Constantes.passwrdInvalid, HttpStatus.BAD_REQUEST);

		}
		user.setLast_login(LocalDate.now());
		user.setModified(LocalDate.now());
		user.setCreated(LocalDate.now());
		user.setIsactive("1");
		User user1 = userRepository.save(user);
		return ResponseEntity.ok(user1);

	}

	private boolean checkPasswordFormat(String password) {
		String regex = Constantes.passwordFormat;
		boolean isEmailValid = password.matches(regex);
		return isEmailValid;
	}

	private boolean emailFormat(String email) {
		String regex = Constantes.emailFormat;
		boolean isEmailValid = email.matches(regex);
		return isEmailValid;
	}
	
	
}
