package com.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.entidad.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer>  {

	@Query("select r from Reclamo r where "
			+ "( ?1 is '' or r.fechaRegistro like ?1 ) AND "
			+ "( ?2 is 0 or r.cliente.idCliente = ?2 ) AND "
			+ "( ?3 is 0  or r.tipoReclamo.idTipoReclamo = ?3 ) AND "
			+ " r.estado = ?4 ")
		public abstract List<Reclamo> listaReclamoConParametros(String fechaRegistro, int cliente, int TipoReclamo, int estado);
	@Query("select r from Reclamo r where "
			+ "( ?1 is '' or r.descripcion like ?1 )" )
		public abstract List<Reclamo> listaReclamoPorNombreLike(String nombre);
}
