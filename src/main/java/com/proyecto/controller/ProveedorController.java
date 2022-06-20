package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorController {

	@Autowired
	private ProveedorService proveedorService;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Proveedor>> ListaProveedor() {
		List<Proveedor> lista = proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró proveedor");
			} else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró proveedor");
		}
		return ResponseEntity.ok(salida);
	}

	// Consultas
	@GetMapping("/listaProveedorConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaDocenteNombreDniUbigeo(
			@RequestParam(name = "razonsocial", required = false, defaultValue = "") String razonsocial,
			@RequestParam(name = "ruc", required = false, defaultValue = "") String ruc,
			@RequestParam(name = "idUbigeo", required = false, defaultValue = "-1") int idUbigeo,
			@RequestParam(name = "contacto", required = false, defaultValue = "") String contacto,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado) {
		Map<String, Object> salida = new HashMap<>();
		try {
			List<Proveedor> lista = proveedorService.listaProveedorPorRazonSRucUbigeoEstado("%" + razonsocial + "%",
					ruc, idUbigeo, "%" + contacto + "%", estado);
			if (CollectionUtils.isEmpty(lista)) {
				salida.put("mensaje", "No existen datos para mostrar");
			} else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error al Buscar");
		}
		return ResponseEntity.ok(salida);
	}//fin de consulta
	
	//Grud de proveedor consultar
	@GetMapping("/listaProveedorPorRazonLike/{razon}")
	@ResponseBody
	public ResponseEntity<List<Proveedor>> listaProveedorPorRazonLike(@PathVariable("razon") String razon) {
		List<Proveedor> lista = null;
		try {
			if (razon.equals("todos")) {
				lista = proveedorService.listaProveedorPorRazonLike("%");
			}else {
				lista = proveedorService.listaProveedorPorRazonLike("%"+razon + "%");	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}//fin Grud de proveedor consultar
	
	//Grud de proveedor registrar
	@PostMapping("/registraProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registraProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProveedor(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Proveedor objSalida =  proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró proveedor");
			} else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró proveedor");
		}
		return ResponseEntity.ok(salida);
	}// fin de registar 
	
	//Grup de actualizar
	@PutMapping("/actualizaProveedor")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProveedor(@RequestBody Proveedor obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Proveedor objSalida =  proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se actualizo proveedor");
			} else {
				salida.put("mensaje", "Se actualizo correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se actualizo proveedor");
		}
		return ResponseEntity.ok(salida);
	}
	
	// Grud eliminar
    @DeleteMapping("/eliminaProveedor/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProveedor(@PathVariable("id")int id) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Optional<Proveedor> opt = proveedorService.buscaProveedor(id);
			if (opt.isPresent()) {
				proveedorService.eliminaProveedor(id);
				Optional<Proveedor> optDocente = proveedorService.buscaProveedor(id);
				if (optDocente.isEmpty()) {
					salida.put("mensaje", "Se elimino correctamente.");
				} else {
					salida.put("mensaje", "No se elimino proveedor");
				}
			}else {
				salida.put("mensaje", "No existe ID proveedor");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se elimino proveedor");
		}
		return ResponseEntity.ok(salida);
	}
	

}
