package com.yosh.springboot.jpa.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.yosh.springboot.jpa.app.models.entity.Client;

public interface IClientDao extends CrudRepository<Client, Long>{

}
