package com.yosh.springboot.jpa.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.yosh.springboot.jpa.app.models.entity.Client;

public interface IClientDao extends PagingAndSortingRepository<Client, Long>{

}
