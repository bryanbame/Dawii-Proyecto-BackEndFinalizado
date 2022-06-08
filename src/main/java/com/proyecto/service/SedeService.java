package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Sede;

public interface SedeService {

	public abstract List<Sede> listaSede();
	public abstract Sede insertaSede(Sede obj);
	public abstract List<Sede> listaSedePorNomDirEstPais(String nombre, String direccion, int estado,String codigoPostal, int idPais);	
	public abstract List<Sede> listaSedePorNombreLike(String nombre);
	public abstract void eliminaSede(int id);
	public abstract Optional<Sede> buscaSede(int id);
}
