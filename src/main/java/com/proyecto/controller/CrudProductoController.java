package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudProducto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudProductoController {
	@Autowired
	private ProductoService service;
	
	@GetMapping("/listaProductoPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProductoPorNombreLike(@PathVariable("nom") String nom){
		List<Producto> lista= null;
		try {
			if(nom.equals("todos")) {
			 lista = service.listaProductoPorNombreLike("%");
			}else {
			 lista = service.listaProductoPorNombreLike("%" + nom + "%");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaProducto(@RequestBody Producto obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdProducto(0);// Para que registre, sino actualiza
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Producto objSalida = service.insertaProducto(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			} else {
				salida.put("mensaje", "Se registró correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(salida);
	}
	
	@PutMapping("/actualizaProducto")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaProducto(@RequestBody Producto obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Producto objSalida = service.insertaProducto(obj);
			if(objSalida == null) {
				salida.put("mensaje", "Error en la actualización");
			} else {
				salida.put("mensaje", "Se actualizó correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualización");
		}
		return ResponseEntity.ok(salida);
	}
	
	@DeleteMapping("/eliminaProducto/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaProducto(@PathVariable("id") int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaProducto(id);
			Optional<Producto> objSalida = service.buscaProducto(id);
			if(objSalida.isEmpty()) {
				salida.put("mensaje", "Se eliminó correctamente");
			} else {
				salida.put("mensaje", "Error en la eliminación");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la eliminación");
		}
		return ResponseEntity.ok(salida);
	}

}
