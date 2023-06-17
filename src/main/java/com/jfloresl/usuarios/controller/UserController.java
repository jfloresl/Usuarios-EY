package com.jfloresl.usuarios.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.service.UserService;
import com.jfloresl.usuarios.utils.Constantes;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	//leer
    /*
	@GetMapping({"/api/users/all","/api/users/all/"})
    public ResponseEntity<Object>  findAll(){
        return userService.findAll();
    }
	*/
	@PostMapping({"/api/users/all","/api/users/all/"})
    public ResponseEntity<Object> findAll(@RequestBody Map<String, String> request){
        return userService.findAll(request);
    }
	/*
	@GetMapping("/api/users/find{id}{token}")
    public ResponseEntity<Object> findOneById(@RequestParam String id,@RequestParam String token){
		return userService.findById(id,token);
    }
	*/
	@PostMapping({"/api/users/find","/api/users/find/"})
    public ResponseEntity<Object> findOneByIdPost(@RequestBody Map<String, String> request){
		return userService.findById(request);
    }
		
	//crear
	@PostMapping({"/api/users","/api/users/"})
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		return userService.createdUser(user);
	}

	//borrar
	@DeleteMapping("/api/users/delete")
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, String> request){
		return userService.deleteById(request);
    }

	//modificar
	@PutMapping("/api/users/")
	public ResponseEntity<Object> updateUser(@RequestBody User user) {		
		return userService.updateUser(user);
	}
	
	//default
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<Object> notMappingUrl(HttpServletResponse response) {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.NOT_FOUND);
	}
	
}