package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query("select m from Marca m where ( ?1 is '' or m.nombre like ?1 ) and ( ?2 is '' or m.descripcion like ?2 ) and ( ?3 is '' or m.certificado = ?3 ) and ( ?4 is -1  or m.pais.idPais = ?4 ) ")
	public abstract List<Marca> listaMarcaPorNombreDescCertPais(String nombre, String descripcion, String certificado, int idPais);
}
