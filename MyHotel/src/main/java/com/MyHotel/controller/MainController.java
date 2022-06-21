package com.MyHotel.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {
	
	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public String getHomeAdmin(Model model) {
		return "admin_home";
    }
	@RequestMapping(value="/home", method = RequestMethod.GET)
    public String getHome(Model model) {
	return "/home";
    }

}