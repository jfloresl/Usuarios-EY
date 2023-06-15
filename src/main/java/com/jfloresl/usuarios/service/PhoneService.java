package com.jfloresl.usuarios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jfloresl.usuarios.entities.Phone;
import com.jfloresl.usuarios.repository.PhoneRepository;
import com.jfloresl.usuarios.response.ResponseHandler;
import com.jfloresl.usuarios.utils.Constantes;

@Service
public class PhoneService {
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	/**
	 * @param id
	 * @return
	 */
	public ResponseEntity<Object> findById(String id) {
		long l = Long.parseLong(id);
		Optional<Phone> phone= phoneRepository.findById(l);
		if(phone.isPresent()) {
			return ResponseEntity.ok(phone);
		}
		return ResponseHandler.generateResponse(Constantes.phoneNotFound, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param phone
	 * @return
	 */
	public ResponseEntity<Object> createdPhone(Phone phone) {
		if(null==phone) {
			return ResponseHandler.generateResponse(Constantes.phoneInvalid, HttpStatus.BAD_REQUEST);
		}
		Phone phone1 = phoneRepository.save(phone);
		return ResponseEntity.ok(phone1);
	}

	public ResponseEntity<Object> deleteById(Long id) {
			phoneRepository.deleteById(id);
			return ResponseHandler.generateResponse(Constantes.phoneDeleted, HttpStatus.ACCEPTED);	
		}

	public ResponseEntity<Object> updatePhones(Phone phone) {
		if (null==phone.getId()) {
    		return ResponseHandler.generateResponse(Constantes.idInvalid, HttpStatus.BAD_REQUEST);
        }
        
        Optional<Phone> phone1 = phoneRepository.findById(phone.getId());
        if (!phone1.isPresent()) {
    		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.BAD_REQUEST);
        }

        phone1.get().setNumber(phone.getNumber());
        phone1.get().setContrycode(phone.getContrycode());
        phone1.get().setCitycode(phone.getCitycode());

        phoneRepository.save(phone1.get());
        return ResponseEntity.ok(phone1.get());
	}

	

}
