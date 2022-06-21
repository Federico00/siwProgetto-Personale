package com.MyHotel.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Pacchetto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String nome;

	private String descrizione;
	
	private int costo;
	
	@ManyToOne
	private Stanza stanza;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public Stanza getStanza() {
		return stanza;
	}


	public void setStanza(Stanza stanza) {
		this.stanza = stanza;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public int getCosto() {
		return costo;
	}


	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	
}
