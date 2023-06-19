package com.jfloresl.usuarios.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.jfloresl.usuarios.entities.User;
import com.jfloresl.usuarios.handler.ResponseHandler;
import com.jfloresl.usuarios.repository.UserRepository;
import com.jfloresl.usuarios.utils.Constantes;
import com.jfloresl.usuarios.utils.UserUtils;

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
	public ResponseEntity<Object> createdUser(User user){
		String validUser=parametersUser(user);
		if (!validUser.equals("0")) {
			return ResponseHandler.generateResponse(validUser, HttpStatus.BAD_REQUEST);
		}
		user.setLast_login(LocalDate.now());
		user.setModified(LocalDate.now());
		user.setCreated(LocalDate.now());
		user.setIsactive("1");
		user.setToken(tokenGenerator(user.getEmail(),user.getPassword()));
		//user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
		User user1 = userRepository.save(user);
    	return ResponseHandler.generateResponse(user1, HttpStatus.OK);

	}

	private String parametersUser(User user) {
		if(UserUtils.isNullOrEmpty(user.getName())){
			return Constantes.userInvalid;
		}
		if(UserUtils.isNullOrEmpty(user.getPhones())) {
			return Constantes.phoneInvalid;
		}
		if(!emailFormat(user.getEmail())) {
			return Constantes.emailInvalid;
		}
		if(!existEmail(user.getEmail())) {
			return Constantes.emailExistente;
		}
		if(!checkPasswordFormat(user.getPassword())) {
			return Constantes.passwordInvalid;
		}
		return "0";
	}

	private String tokenGenerator(String email, String password) {
        //String input = email + password;
        UUID uuid = UUID.randomUUID();
        //UUID uuid = UUID.nameUUIDFromBytes(input.getBytes(StandardCharsets.UTF_8));
		return uuid.toString();
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
	 * @param token 
	 * @return
	 */
	public ResponseEntity<Object> findById(Map<String, String> request) {
		if(UserUtils.isNullOrEmpty(request.get("id")) || UserUtils.isNullOrEmpty(request.get("token"))) {
			return ResponseHandler.generateResponse(Constantes.tokenInvalid, HttpStatus.BAD_REQUEST);
		}
		UUID uuid = UUID.fromString(request.get("id"));
		Optional<User> user = userRepository.findById(uuid);
		if(user.isPresent() && user.get().getToken().equals(request.get("token"))) {
				user.get().setPassword("*********");
		    	return ResponseHandler.generateResponse(user.get(), HttpStatus.OK);
		}
		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param id
	 * @param token 
	 * @return
	 */
	@Transactional
	public ResponseEntity<Object> deleteById(Map<String, String> request) {
		if(UserUtils.isNullOrEmpty(request.get("id")) || UserUtils.isNullOrEmpty(request.get("token"))) {
			return ResponseHandler.generateResponse(Constantes.tokenInvalid, HttpStatus.BAD_REQUEST);
		}
		UUID uuid = UUID.fromString(request.get("id"));

		Optional<User> user =userRepository.findById(uuid);
		if(user.isPresent() && user.get().getToken().equals(request.get("token")) ) {
			userRepository.deleteByIdAndToken(uuid,request.get("token"));
			return ResponseHandler.generateResponse(Constantes.userDeleted, HttpStatus.OK);
		}
		
		return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.OK);

	}

	/**
	 * @param user
	 * @return
	 */
	public ResponseEntity<Object> updateUser(User user) {
		String validUser=parametersUpdateUser(user);
		if (!validUser.equals("0")) {
			return ResponseHandler.generateResponse(validUser, HttpStatus.BAD_REQUEST);
		}		
        Optional<User> user1 = userRepository.findById(user.getId());
        if (user1.isPresent() && user.getToken().equals(user1.get().getToken()) ) {
        	user1.get().setName(UserUtils.ifFirstExistReturnFirst(user.getName(),user1.get().getName()));
            user1.get().setEmail(UserUtils.ifFirstExistReturnFirst(user.getEmail(),user1.get().getEmail()));
            user1.get().setModified(LocalDate.now());

            userRepository.save(user1.get());
			user1.get().setPassword("*********");
	    	return ResponseHandler.generateResponse(user1.get(), HttpStatus.OK);
        }
    	return ResponseHandler.generateResponse(Constantes.userNotFound, HttpStatus.BAD_REQUEST);

        
	}

	private String parametersUpdateUser(User user) {
		
		if (null==user.getId() || null==user.getToken()) {
    		return Constantes.idInvalid;
        }
		if(!UserUtils.isNullOrEmpty(user.getEmail())) {
			 if(!emailFormat(user.getEmail())) {
					return Constantes.emailInvalid;
				}
				if(!existEmail(user.getEmail())) {
					return Constantes.emailExistente;
				}
		}
       
		return "0";
	}

	/**
	 * @return
	 */
	public ResponseEntity<Object> findAll() {
		List<User> lista=userRepository.findAll();
		if(!lista.isEmpty()) {
			for(User u:lista) {
				u.setPassword("*********");
			}
	    	return ResponseHandler.generateResponse(lista, HttpStatus.OK);
		}
    	return ResponseHandler.generateResponse(Constantes.userEmpty, HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<Object> findAll(Map<String, String> id) {
		if(UserUtils.isNullOrEmpty(id.get("id")) || !id.get("id").equals("all")) {
	    	return ResponseHandler.generateResponse(Constantes.idInvalid, HttpStatus.BAD_REQUEST);
		}
		
		List<User> lista=userRepository.findAll();
		if(lista.isEmpty()) {
	    	return ResponseHandler.generateResponse(Constantes.userEmpty, HttpStatus.ACCEPTED);
		}
		
		for(User u:lista) {
			u.setPassword("*********");
		}
    	return ResponseHandler.generateResponse(lista, HttpStatus.OK);
	}

	
}