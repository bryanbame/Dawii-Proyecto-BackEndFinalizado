package com.proyecto.service;

import java.util.List;

import com.proyecto.entidad.Proveedor;

public interface ProveedorServiceCon {
	
public abstract List<Proveedor> listaProveedorPorRazonSRucUbigeoEstado(String razonsocial, String ruc, int idUbigeo,String contacto, int estado);
	
	public abstract List<Proveedor> listaProveedor();

}
