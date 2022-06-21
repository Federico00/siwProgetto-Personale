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
import org.springframework.web.bind.annotation.RequestParam;

import com.MyHotel.model.Hotel;
import com.MyHotel.model.Stanza;
import com.MyHotel.service.HotelService;
import com.MyHotel.service.StanzaService;
import com.MyHotel.validator.StanzaValidator;

@Controller
public class StanzaController {
	@Autowired
	private StanzaService stanzaService;

	@Autowired
	private StanzaValidator validator;
	
	@Autowired
	private HotelService hotelService;

	@GetMapping("/admin/stanza")
	public String stanzaPage(Model model) {
		model.addAttribute("stanza", new Stanza());
		List<Hotel> hotels = hotelService.findAll();
		model.addAttribute("hotelAll", hotels);

		return "stanzaForm.html";
	}

	@PostMapping("/admin/stanza")
	public String addStanza(@Valid @ModelAttribute("stanza") Stanza stanza, BindingResult bindingResult, Model model,@RequestParam(required=false,name="hotel")Long id ) {

		validator.validate(stanza, bindingResult);
		if(!bindingResult.hasErrors()) {
			Hotel hotel = this.hotelService.getById(id);
			List<Stanza> stanzeHotel = hotel.getStanze();
			stanzeHotel.add(stanza);
			hotel.setStanze(stanzeHotel);
			
			this.stanzaService.inserisci(stanza);
			model.addAttribute("stanza", stanza);

			return "stanza.html";
		}
		model.addAttribute("hotelAll", hotelService.tutti());

		return "stanzaForm.html";
	}

	@GetMapping("/stanzaAll/{id}")
	public String getStanza(@PathVariable("id") Long id, Model model) {
		Stanza stanza = stanzaService.getById(id);
		model.addAttribute("stanza", stanza);
		model.addAttribute("pacchettoAll", stanza.getPacchetti());
		return "stanza.html";
	}

	@GetMapping("/stanzaAll")
	public String getStanze(Model model) {
		List<Stanza> stanze =stanzaService.findAll();
		model.addAttribute("stanzaAll", stanze);
		return "elencoStanze.html";
	}
	
	@GetMapping("/admin/deleteStanza")
    public String deleteStanza(Model model) {
    	model.addAttribute("stanzaAll", stanzaService.findAll());
		return "deleteStanza.html";
    }
	
	@PostMapping("/admin/deleteStanza")
	public String deleteDoneStanza(Model model, @RequestParam(required=false,name="stanza")Long id) {
		if (id==null) {
			model.addAttribute("stanzaAll", stanzaService.findAll());
			return "deleteStanza.html";
		}
		stanzaService.elimina(id);
    	model.addAttribute("stanzaAll", stanzaService.findAll());
		return "elencoStanze.html";
	}
}
