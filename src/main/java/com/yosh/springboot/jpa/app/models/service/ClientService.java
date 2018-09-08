package com.yosh.springboot.jpa.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yosh.springboot.jpa.app.models.dao.IClientDao;
import com.yosh.springboot.jpa.app.models.entity.Client;

@Service
public class ClientService implements IClientService {
	@Autowired
	private IClientDao clientDao;

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public void save(Client client) {
		clientDao.save(client);
	}

	@Override
	public Client findOne(Long id) {
		return clientDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		clientDao.delete(id);
	}

}
