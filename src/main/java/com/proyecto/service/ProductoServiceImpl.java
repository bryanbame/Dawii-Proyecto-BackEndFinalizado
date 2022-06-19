package com.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Producto;
import com.proyecto.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoRepository repository;

	@Override
	public List<Producto> listaProducto() {
		return repository.findAll();
	}

	@Override
	public Producto insertaProducto(Producto obj) {
		return repository.save(obj);
	}

	@Override
	public List<Producto> listaProductosPorFiltro(String nombre, String serie, int idMarca, int idPais, int estado) {
		
		return repository.listaProductosPorFiltro(nombre, serie, idMarca, idPais, estado);
	}

	@Override
	public List<Producto> listaProductoPorNombreLike(String nombre) {
		return repository.listaPorNombreLike(nombre);
	}

	@Override
	public void eliminaProducto(int id) {
		repository.deleteById(id); 
		
	}

	@Override
	public Optional<Producto> buscaProducto(int id) {
		return repository.findById(id);
	}
}
