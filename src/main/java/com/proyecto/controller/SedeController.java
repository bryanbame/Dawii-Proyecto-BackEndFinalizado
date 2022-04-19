package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Sede;
import com.proyecto.service.SedeService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/sede")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class SedeController {

	@Autowired
	private SedeService service;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Sede>> listaSede(){
		List<Sede> lista = service.listaSede();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>>  insertaSede(@RequestBody Sede obj){
		HashMap<String, Object> salida = new HashMap<String, Object>();
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
		}catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en el registro");
		}
		return ResponseEntity.ok(salida);
	}
	
	
	
}
