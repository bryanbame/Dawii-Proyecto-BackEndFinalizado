package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Sede;

public interface SedeService {

	public abstract List<Sede> listaSede();
	public abstract Sede insertaSede(Sede obj);
	public abstract List<Sede> listaSedePorNomDirEstPais(String nombre, String direccion, int estado,String codigoPostal, int idPais);
	
}
