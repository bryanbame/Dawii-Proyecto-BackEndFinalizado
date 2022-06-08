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

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudSede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudSedeController {

	@Autowired
	private SedeService service;
	
	@GetMapping("/listaSedePorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSedePorNombreLike(@PathVariable("nom") String nom){
		List<Sede> lista= null;
		try {
			if(nom.equals("todos")) {
			 lista = service.listaSedePorNombreLike("%");
			}else {
			 lista = service.listaSedePorNombreLike("%" + nom + "%");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdSede(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Sede objSalida = service.insertaSede(obj);
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
	
	@PutMapping("/actualizaSede")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaSede(@RequestBody Sede obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Sede objSalida = service.insertaSede(obj);
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
	
	@DeleteMapping("/eliminaSede/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> eliminaSede(@PathVariable("id") int id){
		Map<String, Object> salida = new HashMap<>();
		try {
			service.eliminaSede(id);
			Optional<Sede> objSalida = service.buscaSede(id);
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
