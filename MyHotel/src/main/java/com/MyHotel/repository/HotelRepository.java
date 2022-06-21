package com.MyHotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.MyHotel.model.Hotel;




public interface HotelRepository extends CrudRepository<Hotel, Long>{
	
	public boolean existsByNomeAndIndirizzo(String nome,String indirizzo);
}
