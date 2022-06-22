package com.proyecto.service;


import java.util.List;

import com.proyecto.entidad.Reclamo;

public interface ReclamoService {

	
	public abstract Reclamo insertaReclamo(Reclamo obj);
	public abstract List<Reclamo> listaReclamoConParametros(String fechaRegistro, int cliente, int TipoReclamo, int estado);
	public abstract Reclamo insertaActualizaReclamo(Reclamo obj);
	public abstract List<Reclamo> listaReclamoPorNombreLike(String nombre);
}
