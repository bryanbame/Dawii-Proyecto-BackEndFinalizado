package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService {

	@Autowired
	private ProveedorRepository repository;
	
	@Override
	public Proveedor insertaActualizaProveedor(Proveedor obj) {
		return repository.save(obj);
	}

	@Override
	public List<Proveedor> listaProveedor() {
		return repository.findAll();
	}

	@Override
	public List<Proveedor> listaProveedorPorRazonSRucUbigeoEstado(String razonsocial, String ruc, int idUbigeo,	String contacto, int estado) {
		return repository.listaProveedorPorRazonSRucUbigeoEstado(razonsocial, ruc, idUbigeo, contacto, estado);
	}

	@Override
	public List<Proveedor> listaProveedorPorRazonLike(String razonsocial) {
		// TODO Auto-generated method stub
		return repository.listadoProveedorlike(razonsocial);
	}

	@Override
	public void eliminaProveedor(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public Optional<Proveedor> buscaProveedor(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	
}
