package com.jfloresl.usuarios.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jfloresl.usuarios.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone,Long> {
	
	Optional<Phone> findById(Long id);

	@Query("SELECT u FROM Phone u WHERE u.user=?1")	
	 List<Phone> findByUser(UUID uuid);

}
