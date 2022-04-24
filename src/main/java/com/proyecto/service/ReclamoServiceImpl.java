package com.proyecto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Reclamo;
import com.proyecto.repository.ReclamoRepository;

@Service
public class ReclamoServiceImpl implements ReclamoService {

	@Autowired
	private ReclamoRepository Repository;
	
	@Override
	public Reclamo insertaReclamo(Reclamo obj) {
		return Repository.save(obj);
	}

}
