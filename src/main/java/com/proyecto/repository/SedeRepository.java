package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Sede;

public interface SedeRepository extends JpaRepository<Sede, Integer> {

	@Query("select x from Sede x where x.nombre like ?1")
	public List<Sede> listaPorNombreLike(String nombre);

	@Query("select s from Sede s where ( ?1 is '' or s.nombre like ?1) and (?2 is '' or s.direccion like ?2) and (s.estado = ?3) and (?4 is '' or s.codigoPostal = ?4) and (?5 is -1 or s.pais.idPais = ?5) ")
	public abstract List<Sede> listaSedePorNomDirEstPais(String nombre, String direccion, int estado,String codigoPostal, int idPais);

}
