package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Proveedor;

public interface ProveedorService {
	
	public Proveedor insertaActualizaProveedor(Proveedor obj);
	public abstract List<Proveedor> listaProveedor();

}
