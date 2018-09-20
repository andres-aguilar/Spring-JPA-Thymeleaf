package com.yosh.springboot.jpa.app.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.yosh.springboot.jpa.app.models.entity.Client;
import com.yosh.springboot.jpa.app.models.service.ClientService;
import com.yosh.springboot.jpa.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")  // Guarda el cliente en session y lo pasa entre las vistas (Muy util para editar registros)
public class ClientController {
	
	@Autowired
	private ClientService client;
	
	@GetMapping("/view/{id}")
	public String view(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Client currentClient = client.findOne(id);
		
		if(currentClient == null) {
			flash.addAttribute("error", "Cliente no encontrado!");
			return "redirect:/list";
		}
		
		model.put("client", currentClient);
		model.put("title", currentClient.getName());
		
		return "view";
	}
	
	@GetMapping("/list")
	public String listClients(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		Pageable pageRequest = new PageRequest(page, 5);
		Page<Client> clients = client.findAll(pageRequest);
		PageRender<Client> pageRender = new PageRender<Client>("/list", clients);
		
		model.addAttribute("title", "Listado de clientes");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
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
	public String saveClient(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile photo, SessionStatus status, RedirectAttributes flash) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Registro de nuevo cliente");
			return "createClient"; 
		} 
		
		if(!photo.isEmpty()) {
			Path path = Paths.get("src//main//resources//static//uploads");
			String rootPath = path.toFile().getAbsolutePath();
			
			try {
				byte[] photoBytes = photo.getBytes();
				Path fullPath = Paths.get(rootPath + "//" + photo.getOriginalFilename());
				Files.write(fullPath, photoBytes);
				client.setPhoto(photo.getOriginalFilename());
				flash.addFlashAttribute("info", "Imagen guardada con éxito!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		this.client.save(client);
		status.setComplete();
		flash.addFlashAttribute("success", "Cliente agregado con éxito!");
		return "redirect:list";
	}
	
	@RequestMapping(value="/edit/{id}")
	public String editClient(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		model.addAttribute("title", "Editar cliente");
		
		Client currentClient = null;
		
		if (id > 0) {
			currentClient = client.findOne(id);
			
			if (currentClient == null) {
				flash.addFlashAttribute("error", "El cliente no existe!");
				return "redirect:list";
			}
			
			model.addAttribute("client", currentClient);
		} else {
			flash.addFlashAttribute("error", "El cliente no existe!");
			return "redirect:list";
		}
		
		return "createClient";
	}

	@RequestMapping(value="/delete/{id}")
	public String deleteClient(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			client.delete(id);
		}
		
		flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
		return "redirect:/list";
	}
}

