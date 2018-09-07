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
	@Transactional()
	@Override
	public List<Client> findAll() {
		return em.createQuery("FROM Client").getResultList();
	}
}
