package com.MyHotel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.MyHotel.model.Hotel;
import com.MyHotel.model.Stanza;
import com.MyHotel.repository.HotelRepository;



@Service
public class HotelService {
	@Autowired
	private HotelRepository hotelRepository;
	
	@Transactional
	public void save(Hotel hotel) {
		 hotelRepository.save(hotel);
	}

	public Hotel getById(Long id) {
		
		return hotelRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Hotel> tutti(){
		return (List<Hotel>) hotelRepository.findAll();
	}
	
	public List<Hotel> findAll() {
		List<Hotel > hotels = new ArrayList<Hotel>();
		for(Hotel b : hotelRepository.findAll()) {
			hotels.add(b);
		}
		return hotels;
	
	}

	public boolean aldreadyExist(Hotel hotel) {
		return this.hotelRepository.existsByNomeAndIndirizzo(hotel.getNome(), hotel.getIndirizzo());
	}
	
	public void inserisci(Hotel hotel) {
		this.hotelRepository.save(hotel);
	}
	public void elimina(Long id) {
		this.hotelRepository.deleteById(id);
		
	}

	



}
