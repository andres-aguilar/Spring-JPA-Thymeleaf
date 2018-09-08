package com.yosh.springboot.jpa.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.yosh.springboot.jpa.app.models.entity.Client;

@Repository
public class ClientDAO implements IClientDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	
	@Override
	public List<Client> findAll() {
		return em.createQuery("FROM Client").getResultList();
	}

	@Transactional
	@Override
	public void save(Client client) {
		if (client.getId() != null && client.getId() > 0) {
			em.merge(client);
		} else {			
			em.persist(client);		
		}
	}

	@Override
	@Transactional
	public Client findOne(Long id) {
		return em.find(Client.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		Client client = findOne(id);
		em.remove(client);
	}
}
