package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Producto;

public interface ProductoService {
	public abstract List<Producto> listaProducto();
	public abstract Producto insertaProducto(Producto obj);
}
