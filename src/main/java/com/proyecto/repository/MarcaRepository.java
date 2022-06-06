package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	@Query("select m from Marca m where ( ?1 is '' or m.nombre like ?1 ) and ( ?2 is '' or m.certificado = ?2 ) and ( ?3 is -1  or m.pais.idPais = ?3 ) and (m.estado = ?4) and (?5 is '' or ?6 is '' or (m.fechaRegistro between ?5  and ?6 ))")
	public abstract List<Marca> listaMarcaPorNombreDescCertPais(String nombre, String certificado, int idPais, int estado, String fecInicio, String fecFin);
	
	@Query("select m from Marca m where m.nombre like ?1")
	public List<Marca> listaPorNombreLike(String nombre);
}
