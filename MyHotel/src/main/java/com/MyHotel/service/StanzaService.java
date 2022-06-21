package com.MyHotel.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyHotel.model.Stanza;
import com.MyHotel.repository.StanzaRepository;





@Service
public class StanzaService {
	@Autowired
	private StanzaRepository stanzaRepository;
	
	@Transactional
	public void save(Stanza stanza) {
		 stanzaRepository.save(stanza);
	}

	public Stanza getById(Long id) {
		
		return stanzaRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public List<Stanza> tutti(){
		return (List<Stanza>) stanzaRepository.findAll();
	}
	
	public List<Stanza> findAll() {
		List<Stanza > stanze = new ArrayList<Stanza>();
		for(Stanza b : stanzaRepository.findAll()) {
			stanze.add(b);
		}
		return stanze;
	
	}

	
	public void inserisci(Stanza stanza) {
		this.stanzaRepository.save(stanza);
	}
	public void elimina(Long id) {
		this.stanzaRepository.deleteById(id);
		
	}



}
