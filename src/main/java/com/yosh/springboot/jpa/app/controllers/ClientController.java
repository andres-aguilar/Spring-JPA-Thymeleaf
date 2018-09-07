package com.yosh.springboot.jpa.app.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.yosh.springboot.jpa.app.models.dao.IClientDao;
import com.yosh.springboot.jpa.app.models.entity.Client;

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
	
	@GetMapping("/create")
	public String createClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		model.addAttribute("title", "Registro de nuevo cliente");
		
		return "createClient";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveClient(Client client) {
		this.client.save(client);
		return "redirect:list";
	}
	
}

