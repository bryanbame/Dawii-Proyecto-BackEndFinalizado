package com.proyecto.service;

import java.util.List;
import java.util.Optional;

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
	@Override
	public List<Sede> listaSedePorNomDirEstPais(String nombre, String direccion, int estado,String codigoPostal, int idPais) {
		return repository.listaSedePorNomDirEstPais(nombre, direccion, estado, codigoPostal, idPais);

	}

	@Override
	public List<Sede> listaSedePorNombreLike(String nombre) {

		return repository.listaPorNombreLike(nombre);

	}
	@Override
	public void eliminaSede(int id) {
		
		repository.deleteById(id); 
		
	}
	@Override
	public Optional<Sede> buscaSede(int id) {

		return repository.findById(id);
	}
	
	
}
