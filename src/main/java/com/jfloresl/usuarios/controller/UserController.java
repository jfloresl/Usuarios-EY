package com.jfloresl.usuarios.controller;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.response.ResponseHandler;
import com.jfloresl.usuarios.service.UserService;
import com.jfloresl.usuarios.utils.Constantes;

@RestController
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	/**
	 * @param userRepository
	 */
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	//leer
/*
	@PostMapping(value="/api/users")
    public List<User> findAll(@RequestBody String body){
        return userRepository.findAll();
    }
*/
	//crear
	@PostMapping("/api/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		return userService.createdUser(user);
	}

	
	
	//borrar
	
	
	//modificar
	
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<Object> test(HttpServletResponse response) throws IOException {
		return ResponseHandler.generateResponse(Constantes.notFoundService, HttpStatus.NOT_FOUND);
	}

}
