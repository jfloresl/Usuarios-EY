package com.jfloresl.usuarios.service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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
	
	
	/**
	 * @param user
	 * @param headers
	 * @return
	 */
	public Boolean userValido(User user,Headers headers) {
		if(null!=user || null!=headers) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * @param email
	 * @return
	 */
	public Boolean existEmail(String email) {
		return userRepository.findByEmail(email).isEmpty();
	}

	/**
	 * @param user
	 * @return
	 */
	public ResponseEntity<Object> createdUser(User user) {
		if(!emailFormat(user.getEmail())) {
			return ResponseHandler.generateResponse(Constantes.emailInvalid, HttpStatus.BAD_REQUEST);
		}
		if(!existEmail(user.getEmail())) {
			return ResponseHandler.generateResponse(Constantes.emailExistente, HttpStatus.BAD_REQUEST);
		}
		if(!checkPasswordFormat(user.getPassword())) {
			return ResponseHandler.generateResponse(Constantes.passwordInvalid, HttpStatus.BAD_REQUEST);

		}
		user.setLast_login(LocalDate.now());
		user.setModified(LocalDate.now());
		user.setCreated(LocalDate.now());
		user.setIsactive("1");
		User user1 = userRepository.save(user);
		//return ResponseHandler.generateResponse(ResponseEntity.ok(user1), HttpStatus.ACCEPTED);
		return ResponseEntity.ok(user1);

	}

	/**
	 * @param password
	 * @return
	 */
	private boolean checkPasswordFormat(String password) {
		String regex = Constantes.passwordFormat;
		boolean isEmailValid = password.matches(regex);
		return isEmailValid;
	}

	/**
	 * @param email
	 * @return
	 */
	private boolean emailFormat(String email) {
		String regex = Constantes.emailFormat;
		boolean isEmailValid = email.matches(regex);
		return isEmailValid;
	}

	/**
	 * @param id
	 * @return
	 */
	public ResponseEntity<Object> findById(String id) {
		UUID uuid = UUID.fromString(id);
		Optional<User> user = userRepository.findById(uuid);
		if(user.isPresent()) {
			return ResponseEntity.ok(user);
		}
		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.BAD_REQUEST);

	}

	/**
	 * @param id
	 * @return
	 */
	public ResponseEntity<Object> deleteById(String id) {
		UUID uuid = UUID.fromString(id);
		userRepository.deleteById(uuid);
		return ResponseHandler.generateResponse(Constantes.userDeleted, HttpStatus.ACCEPTED);	
	}

	/**
	 * @param user
	 * @return
	 */
	public ResponseEntity<Object> updateUser(User user) {

        if (null==user.getId()) {
    		return ResponseHandler.generateResponse(Constantes.idInvalid, HttpStatus.BAD_REQUEST);
        }
        
        Optional<User> user1 = userRepository.findById(user.getId());
        if (!user1.isPresent()) {
    		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.BAD_REQUEST);
        }

        user1.get().setName(user.getName());
        user1.get().setEmail(user.getEmail());
        user1.get().setName(user.getName());
        user1.get().setModified(LocalDate.now());

        userRepository.save(user1.get());
        return ResponseEntity.ok(user1.get());
	}

	
}
