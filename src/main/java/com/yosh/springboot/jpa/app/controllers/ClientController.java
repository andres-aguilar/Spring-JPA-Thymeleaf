package com.yosh.springboot.jpa.app.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.yosh.springboot.jpa.app.models.entity.Client;
import com.yosh.springboot.jpa.app.models.service.ClientService;

@Controller
@SessionAttributes("client")  // Guarda el cliente en session y lo pasa entre las vistas (Muy util para editar registros)
public class ClientController {
	
	@Autowired
	private ClientService client;
	
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
	public String saveClient(@Valid Client client, BindingResult result, Model model, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Registro de nuevo cliente");
			return "createClient"; 
		} 
		
		this.client.save(client);
		status.setComplete();
		return "redirect:list";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editClient(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("title", "Editar cliente");
		
		Client currentClient = null;
		
		if (id > 0) {
			currentClient = client.findOne(id);
			model.addAttribute("client", currentClient);
		} else {
			return "redirect:list";
		}
		
		return "createClient";
	}

	@RequestMapping(value="/delete/{id}")
	public String deleteClient(@PathVariable(value="id") Long id) {
		if (id > 0) {
			client.delete(id);
		}
		
		return "redirect:/list";
	}
}

