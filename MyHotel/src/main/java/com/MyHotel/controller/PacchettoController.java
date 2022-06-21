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


import com.MyHotel.model.Pacchetto;
import com.MyHotel.model.Stanza;

import com.MyHotel.service.PacchettoService;
import com.MyHotel.service.StanzaService;
import com.MyHotel.validator.PacchettoValidator;


@Controller
public class PacchettoController {
	
	@Autowired
	private PacchettoService pacchettoService;

	@Autowired
	private PacchettoValidator validator;
	
	@Autowired
	private StanzaService stanzaService;

	@GetMapping("/admin/pacchetto")
	public String pacchettoPage(Model model) {
		model.addAttribute("pacchetto", new Pacchetto());
		List<Stanza> stanze = stanzaService.findAll();
		model.addAttribute("stanzaAll", stanze);

		return "pacchettoForm.html";
	}

	@PostMapping("/admin/pacchetto")
	public String addPacchetto(@Valid @ModelAttribute("pacchetto") Pacchetto pacchetto, BindingResult bindingResult, Model model,@RequestParam(required=false,name="stanza")Long id ) {

		validator.validate(pacchetto, bindingResult);
		if(!bindingResult.hasErrors()) {
			Stanza stanza = this.stanzaService.getById(id);
			List<Pacchetto> pacchettiStanza = stanza.getPacchetti();
			pacchettiStanza.add(pacchetto);
			stanza.setPacchetti(pacchettiStanza);
			
			this.pacchettoService.inserisci(pacchetto);
			model.addAttribute("pacchetto", pacchetto);

			return "pacchetto.html";
		}
		model.addAttribute("stanzaAll", stanzaService.tutti());
		return "pacchettoForm.html";
	}

	@GetMapping("/pacchettoAll/{id}")
	public String getPacchetto(@PathVariable("id") Long id, Model model) {
		Pacchetto pacchetto = pacchettoService.getById(id);
		model.addAttribute("pacchetto", pacchetto);
		return "pacchetto.html";
	}

	@GetMapping("/pacchettoAll")
	public String getPacchetti(Model model) {
		List<Pacchetto> pacchetti = pacchettoService.tutti();
		model.addAttribute("pacchettoAll", pacchetti);
		return "elencoPacchetti.html";
	}
	
	@GetMapping("/admin/deletePacchetto")
    public String deletePacchetto(Model model) {
    	model.addAttribute("pacchettoAll", pacchettoService.tutti());
		return "deletePacchetto.html";
    }
	
	@PostMapping("/admin/deletePacchetto")
	public String deleteDonePacchetto(Model model, @RequestParam(required=false,name="pacchetto")Long id) {
		if (id==null) {
			model.addAttribute("pacchettoAll", pacchettoService.tutti());
			return "deletePacchetto.html";
		}
		pacchettoService.elimina(id);
    	model.addAttribute("pacchettoAll", pacchettoService.tutti());
		return "elencoPacchetti.html";
	}

}
