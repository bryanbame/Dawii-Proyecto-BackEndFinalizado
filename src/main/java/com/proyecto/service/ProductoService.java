package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Producto;

public interface ProductoService {
	public abstract List<Producto> listaProducto();
	public abstract Producto insertaProducto(Producto obj);
	public abstract List<Producto> listaProductosPorFiltro(String nombre, String serie, int idMarca, int idPais, int estado);
}
