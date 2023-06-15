package com.jfloresl.usuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jfloresl.usuarios.entities.Phone;

public interface PhoneRepository extends JpaRepository<Phone,Long> {
	Optional<Phone> findById(Long id);

}
