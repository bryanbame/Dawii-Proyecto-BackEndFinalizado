package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Proveedor;
import com.proyecto.repository.ProveedorRepositoryCon;

@Service
public class ProveedorServiceImp implements ProveedorServiceCon {
	
	@Autowired
	private ProveedorRepositoryCon repository;

	@Override
	public List<Proveedor> listaProveedorPorRazonSRucUbigeoEstado(String razonsocial, String ruc, int idUbigeo,
			String contacto, int estado) {
		// TODO Auto-generated method stub
		return repository.listaProveedorPorRazonSRucUbigeoEstado(razonsocial, ruc,idUbigeo,contacto, estado);
	}

	@Override
	public List<Proveedor> listaProveedor() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}
	

}
