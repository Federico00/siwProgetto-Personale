package com.MyHotel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyHotel.model.Pacchetto;
import com.MyHotel.repository.PacchettoRepository;




@Service
public class PacchettoService {
	@Autowired
	private PacchettoRepository pacchettoRepository;
	
	@Transactional
	public void save(Pacchetto pacchetto) {
		 pacchettoRepository.save(pacchetto);
	}

	public Pacchetto getById(Long id) {
		
		return pacchettoRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Pacchetto> tutti(){
		return (List<Pacchetto>) pacchettoRepository.findAll();
	}
	

	//vedere nel repo
	public boolean aldreadyExist(Pacchetto pacchetto) {
		return this.pacchettoRepository.existsByNomeAndDescrizione(pacchetto.getNome(), pacchetto.getDescrizione());
	}
	
	public void inserisci(Pacchetto pacchetto) {
		this.pacchettoRepository.save(pacchetto);
	}
	public void elimina(Long id) {
		this.pacchettoRepository.deleteById(id);
		
	}



}
