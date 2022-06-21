package com.MyHotel.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.MyHotel.model.Hotel;
import com.MyHotel.model.Stanza;
import com.MyHotel.service.HotelService;
import com.MyHotel.validator.HotelValidator;



@Controller
public class HotelController {
	
	@Autowired
	private HotelService hotelService;

	@Autowired
	private HotelValidator validator;


	@GetMapping("/admin/hotel")
	public String hotelPage(Model model) {
		model.addAttribute("hotel", new Hotel());
		return "hotelForm.html";
	}

	@PostMapping("/admin/hotel")
	public String addHotel(@Valid @ModelAttribute("hotel") Hotel hotel, BindingResult bindingResult, Model model ) {

		validator.validate(hotel,bindingResult);
		if(!bindingResult.hasErrors()) {
			this.hotelService.save(hotel);
			model.addAttribute("hotel", hotel);
			return "hotel.html";
		}
		return "hotelForm.html";
	}

	@GetMapping("/hotelAll/{id}")
	public String getHotel(@PathVariable("id") Long id, Model model) {
		Hotel hotel = hotelService.getById(id);
		model.addAttribute("hotel", hotel);
		model.addAttribute("stanze", hotel.getStanze());
		return "hotel.html";
	}

	@GetMapping("/hotelAll")
	public String getHotels(Model model) {
		List<Hotel> hotels = hotelService.findAll();
		model.addAttribute("hotelAll", hotels);
		return "elencoHotel.html";
	}
	
	@GetMapping("/admin/deleteHotel")
    public String deleteHotel(Model model) {
    	model.addAttribute("hotelAll", hotelService.findAll());
		return "deleteHotel.html";
    }
	
	@PostMapping("/admin/deleteHotel")
	public String deleteDoneHotel(Model model, @RequestParam(required=false,name="hotel")Long id) {
		if (id==null) {
			model.addAttribute("hotelAll", hotelService.findAll());
			return "deleteHotel.html";
		}
		hotelService.elimina(id);
    	model.addAttribute("hotelAll", hotelService.findAll());
		return "elencoHotel.html";
	}
	

	
	@GetMapping("/admin/modificaHotelAll")
	public String modificaHotel(Model model) {
		List<Hotel> hotels = hotelService.findAll();
		model.addAttribute("hotelAll", hotels);
		return "ElencoModificaHotel.html";
	}
	
	@GetMapping("/admin/modificaHotel/{id}")
	public String getModificaHotel(@PathVariable("id") Long id, Model model) {
		Hotel hotel = hotelService.getById(id);
		model.addAttribute("hotelNuovo", new Hotel());
		model.addAttribute("hotel", hotel);
		model.addAttribute("stanze", hotel.getStanze());
		return "modificaHotel.html";
	}
	
	@PostMapping("/admin/modificaHotel")
	public String modificaHotel(@PathVariable("id") Long id, Model model,@ModelAttribute("hotel") Hotel hotel, @RequestParam(required=false,name="stanze")List<Stanza> stanze) {
		Hotel hotelvecchio = hotelService.getById(id);
		
		if(hotel.getNome()!= null)
			hotelvecchio.setNome(hotel.getNome());
		if(hotel.getNazione()!= null)
			hotelvecchio.setNazione(hotel.getNazione());
		if(hotel.getIndirizzo()!= null)
			hotelvecchio.setIndirizzo(hotel.getIndirizzo());
		if(!hotel.getStanze().isEmpty())
			hotelvecchio.setStanze(stanze);
		
		this.hotelService.save(hotelvecchio);
		model.addAttribute("hotelvecchio", hotelvecchio);
		model.addAttribute("stanze", hotelvecchio.getStanze());
		return "hotel.html";
	}

	
	

	
	
}
