package com.MyHotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.MyHotel.model.User;




public interface UserRepository extends CrudRepository<User, Long> {

}