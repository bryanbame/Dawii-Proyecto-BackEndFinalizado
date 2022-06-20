package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import com.proyecto.entidad.Proveedor;

public interface ProveedorService {
	
	public Proveedor insertaActualizaProveedor(Proveedor obj);
	public abstract List<Proveedor> listaProveedor();
	public List<Proveedor> listaProveedorPorRazonSRucUbigeoEstado(String razonsocial, String ruc, int idUbigeo, String contacto, int estado);

    //crud de proveedor
	public List<Proveedor> listaProveedorPorRazonLike(String razonsocial);
	public abstract void eliminaProveedor(int id);
	public abstract Optional<Proveedor> buscaProveedor(int id);
}
