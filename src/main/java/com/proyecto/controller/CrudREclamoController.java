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

import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudReclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudREclamoController {
	
	@Autowired
	private ReclamoService service;
	
	@GetMapping("/listaReclamoPorDescripcioLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Reclamo>> listaDocentePorNombreLike(@PathVariable("nom") String nom) {
		List<Reclamo> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = service.listaReclamoPorNombreLike("%");
			}else {
				lista = service.listaReclamoPorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertaDocente(@RequestBody Reclamo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdReclamo(0);
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);
			Reclamo objSalida =  service.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error, no se registró");
			} else {
				salida.put("mensaje", "Se registró correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error, no se registró");
		}
		return ResponseEntity.ok(salida);
	}

	@PutMapping("/actualizaReclamo")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaDocente(@RequestBody Reclamo obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Reclamo objSalida =  service.insertaActualizaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error, no se actualizó");
			} else {
				salida.put("mensaje", "Se actualizó correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error, no se actualizó");
		}
		return ResponseEntity.ok(salida);
	}
}
