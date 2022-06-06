package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Sede;

public interface SedeService {

	public abstract List<Sede> listaSede();
	public abstract Sede insertaSede(Sede obj);
	
	//CRUD
	public abstract Sede insertaActualizaSede(Sede sede);
	public abstract List<Sede> listaSedePorNombreLike(String nombre);
}
