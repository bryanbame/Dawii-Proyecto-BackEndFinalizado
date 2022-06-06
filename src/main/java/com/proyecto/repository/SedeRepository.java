package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {
	
	
	@Query("select x from Sede x where x.nombre like ?1")
	public List<Sede> listaPorNombreLike(String nombre);
}
