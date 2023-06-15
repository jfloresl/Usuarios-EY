package com.jfloresl.usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jfloresl.usuarios.entities.Phone;
import com.jfloresl.usuarios.repository.PhoneRepository;
import com.jfloresl.usuarios.service.PhoneService;


@RestController
public class PhoneController {
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private PhoneService phoseService;

	//leer

	@GetMapping("/api/phones/")
    public List<Phone> findAll(){
        return phoneRepository.findAll();
    }

	@GetMapping("/api/phones/{id}")
    public ResponseEntity<Object> findOneById(@PathVariable String id){
		return phoseService.findById(id);
    }
	
	//crear
	@PostMapping("/api/phones")
	public ResponseEntity<Object> createPhone(@RequestBody Phone phone) {		
		return phoseService.createdPhone(phone);
	}

	//borrar
	
	@DeleteMapping("/api/phones/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){
		return phoseService.deleteById(id);
    }
	
	//modificar
	@PutMapping("/api/phones/")
	public ResponseEntity<Object> updatePhone(@RequestBody Phone phone) {		
		return phoseService.updatePhones(phone);
	}
	
	
}
