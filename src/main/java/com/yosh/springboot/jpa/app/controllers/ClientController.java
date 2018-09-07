package com.yosh.springboot.jpa.app.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.yosh.springboot.jpa.app.models.dao.IClientDao;

@Controller
public class ClientController {
	
	@Autowired
	private IClientDao client;
	
	@GetMapping("/list")
	public String listClients(Model model) {
		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("clients", client.findAll());
		return "listClients";
	}
}

