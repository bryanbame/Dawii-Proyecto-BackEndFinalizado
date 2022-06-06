package com.proyecto.service;

import java.util.List;

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

	@Override
	public List<Reclamo> listaReclamoConParametros(String fechaRegistro, int cliente, int TipoReclamo, int estado) {
		return Repository.listaReclamoConParametros(fechaRegistro, cliente, TipoReclamo, estado);
	}

}
