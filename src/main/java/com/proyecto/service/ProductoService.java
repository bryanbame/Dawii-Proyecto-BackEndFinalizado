package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Producto;

public interface ProductoService {
	public abstract List<Producto> listaProducto();
	public abstract Producto insertaProducto(Producto obj);
	public abstract List<Producto> listaProductosPorFiltro(String nombre, String serie, int idMarca, int idPais, int estado);
	public abstract List<Producto> listaProductoPorNombreLike(String nombre);
	public abstract void eliminaProducto(int id);
	public abstract Optional<Producto> buscaProducto(int id);
}
