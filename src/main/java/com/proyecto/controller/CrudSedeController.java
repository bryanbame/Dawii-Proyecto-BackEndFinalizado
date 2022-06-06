package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping("/rest/crudSede")
@CrossOrigin(origins= "http://localhost:4200")
public class CrudSedeController {

	@Autowired
	private SedeService service;
	
	@GetMapping("/listaSedePorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSedePorNombreLike(@PathVariable("nom") String nom){
		List<Sede> lista= null;
		try {
			 lista = service.listaSedePorNombreLike("%" + nom + "%");
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
			Sede objSalida = service.insertaActualizaSede(obj);
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
			Sede objSalida = service.insertaActualizaSede(obj);
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
}
