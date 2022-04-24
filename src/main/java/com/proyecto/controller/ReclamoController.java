package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Reclamo;
import com.proyecto.service.ReclamoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/reclamo")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ReclamoController {

	
	@Autowired
	private ReclamoService reclamoService;
	
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaReclamo(@RequestBody Reclamo obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setIdReclamo(0);// Para que registre, sino actualiza
			obj.setFechaRegistro(new Date()); //fecha registro 
			obj.setEstado(1); //probando default de estado
			Reclamo objSalida = reclamoService.insertaReclamo(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en el registro");
			} else {
					salida.put("mensaje", "Se registro correctamente");
			}			
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(salida);
	}
}
