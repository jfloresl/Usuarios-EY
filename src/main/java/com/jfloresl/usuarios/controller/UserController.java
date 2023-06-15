package com.jfloresl.usuarios.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.response.ResponseHandler;
import com.jfloresl.usuarios.service.UserService;
import com.jfloresl.usuarios.utils.Constantes;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	//leer

	@GetMapping("/api/users/all")
    public List<User> findAll(){
        return userService.findAll();
    }

	@GetMapping("/api/users{id}{token}")
    public ResponseEntity<Object> findOneById(@RequestParam String id,@RequestParam String token){
		return userService.findById(id,token);
    }
	
	//crear
	@PostMapping("/api/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {		
		return userService.createdUser(user);
	}

	//borrar
	
	@DeleteMapping("/api/users/delete{id}{token}")
    public ResponseEntity<Object> deleteById(@RequestParam String id,@RequestParam String token){
		return userService.deleteById(id,token);
    }
	
	//modificar
	@PutMapping("/api/users/")
	public ResponseEntity<Object> updateUser(@RequestBody User user) {		
		return userService.updateUser(user);
	}
	
	//default
	@RequestMapping(value = {"*/*","*"}, method = {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT,RequestMethod.DELETE})
	public ResponseEntity<Object> test(HttpServletResponse response) throws IOException {
		return ResponseHandler.generateResponse(Constantes.serviceNotFound, HttpStatus.NOT_FOUND);
	}

}
