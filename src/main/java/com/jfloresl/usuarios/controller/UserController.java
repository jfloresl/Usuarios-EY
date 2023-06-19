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
	/**
	 * Funcion para obtener todos los usuarios existentes
	 * @param request
	 * @return
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
	/**
	 * Funcion para obtener a un usurio en base a su id y su token
	 * @param request
	 * @return
	 */
	@PostMapping({"/api/users/find","/api/users/find/"})
    public ResponseEntity<Object> findOneByIdPost(@RequestBody Map<String, String> request){
		return userService.findById(request);
    }
		
	//crear
	/**
	 * Funcion para crear un usuario
	 * @param user
	 * @return
	 */
	@PostMapping({"/api/users/create","/api/users/create/"})
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		return userService.createdUser(user);
	}

	//borrar
	/**
	 * Funcion para crear un usuario
	 * @param request
	 * @return
	 */
	@DeleteMapping({"/api/users/delete","/api/users/delete/"})
    public ResponseEntity<Object> deleteById(@RequestBody Map<String, String> request){
		return userService.deleteById(request);
    }

	//modificar
	/**
	 * Funcion para modificar los datos de un usuario (nombre y email)
	 * @param user
	 * @return
	 */
	@PutMapping({"/api/users/edit","/api/users/edit/"})
	public ResponseEntity<Object> updateUser(@RequestBody User user) {		
		return userService.updateUser(user);
	}
	
	//default
	/**
	 * Funcion que captura todas las otras posibles urls para entregar el error
	 * de que no existen
	 * @param response
	 * @return
	 */
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<Object> notMappingUrl(HttpServletResponse response) {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.NOT_FOUND);
	}
	
}