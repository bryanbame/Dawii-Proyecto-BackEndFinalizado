package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Sede;
import com.proyecto.repository.SedeRepository;

@Service
public class SedeServiceImpl implements SedeService{

	@Autowired
	private SedeRepository repository;

	
	@Override
	public List<Sede> listaSede() {
		
		return repository.findAll();
	}
	@Override
	public Sede insertaSede(Sede obj) {
		
		return repository.save(obj);
	}

	
	
}