package com.cognizant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.entity.UserCredential;

public interface UserCredentialRepository extends JpaRepository<UserCredential, Integer> {
  Optional<UserCredential> findByName(String username);
	
	boolean existsByName(String name);
}
