package com.MyHotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyHotel.model.Credentials;

import java.util.Optional;





public interface CredentialsRepository extends CrudRepository<Credentials, Long> {
	
	public Optional<Credentials> findByUsername(String username);

}