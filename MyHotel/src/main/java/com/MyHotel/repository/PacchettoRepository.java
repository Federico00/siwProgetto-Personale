package com.MyHotel.repository;

import org.springframework.data.repository.CrudRepository;


import com.MyHotel.model.Pacchetto;



public interface PacchettoRepository extends CrudRepository<Pacchetto, Long>{
	
	public boolean existsByNomeAndDescrizione(String nome,String descrizione);
}
