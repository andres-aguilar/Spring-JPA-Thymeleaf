package com.yosh.springboot.jpa.app.models.dao;

import java.util.List;

import com.yosh.springboot.jpa.app.models.entity.Client;

public interface IClientDao {
	public List<Client> findAll();
	
	public void save(Client client);
}
