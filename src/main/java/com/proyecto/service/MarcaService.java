package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Marca;

public interface MarcaService {

	public abstract List<Marca> listaMarca();
	public abstract Marca insertaMarca(Marca obj);
	public abstract List<Marca> listaMarcaPorNombreDescCertPais(String nombre, String certificado, int idPais, int estado, String fecInicio, String fecFin);
	public abstract List<Marca> listaDocentePorNombreLike(String nombre);
}
