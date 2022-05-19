package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.entidad.Marca;
import com.proyecto.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

	@Autowired
	private MarcaRepository Repository;

	@Override
	public List<Marca> listaMarca() {
		return Repository.findAll();
	}

	@Override
	public Marca insertaMarca(Marca obj) {
		return Repository.save(obj);
	}

	@Override
	public List<Marca> listaMarcaPorNombreDescCertPais(String nombre, String certificado,
			int idPais, int estado, String fecInicio, String fecFin) {
		return Repository.listaMarcaPorNombreDescCertPais(nombre, certificado, idPais, estado, fecInicio, fecFin);
	}

}
