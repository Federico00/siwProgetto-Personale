package com.MyHotel.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Stanza {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nome;

	private int numeroLettiSingoli;

	private int numeroLettiMatrimoniali;

	@OneToMany(mappedBy = "stanza",cascade = {CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Pacchetto> pacchetti;
	
	@ManyToOne
	private Hotel hotel;
	
	public Long getId() {
		return id;
	}


	public List<Pacchetto> getPacchetti() {
		return pacchetti;
	}


	public Hotel getHotel() {
		return hotel;
	}


	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}


	public void setPacchetti(List<Pacchetto> pacchetti) {
		this.pacchetti = pacchetti;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getNumeroLettiSingoli() {
		return numeroLettiSingoli;
	}


	public void setNumeroLettiSingoli(int numeroLettiSingoli) {
		this.numeroLettiSingoli = numeroLettiSingoli;
	}


	public int getNumeroLettiMatrimoniali() {
		return numeroLettiMatrimoniali;
	}


	public void setNumeroLettiMatrimoniali(int numeroLettiMatrimoniali) {
		this.numeroLettiMatrimoniali = numeroLettiMatrimoniali;
	}


}
