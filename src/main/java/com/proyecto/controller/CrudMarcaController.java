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

import com.proyecto.entidad.Marca;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/crudMarca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class CrudMarcaController {

	@Autowired
	private MarcaService Mservice;

	@GetMapping("/listaMarcaPorNombreLike/{nom}")
	@ResponseBody
	public ResponseEntity<List<Marca>> listaMarcaPorNombreLike(@PathVariable("nom") String nom) {
		List<Marca> lista  = null;
		try {
			if (nom.equals("todos")) {
				lista = Mservice.listaMarcaPorNombreLike("%");
			}else {
				lista = Mservice.listaMarcaPorNombreLike("%" + nom + "%");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/registraMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> registraMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			obj.setIdMarca(0);// Para que registre, sino actualiza
			obj.setFechaRegistro(new Date()); //probando default de fecha registro - si funciona
			obj.setEstado(1); //probando default de estado - si funciona
			Marca objSalida = Mservice.insertaMarca(obj);
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
	
	@PutMapping("/actualizaMarca")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> actualizaMarca(@RequestBody Marca obj) {
		Map<String, Object> salida = new HashMap<>();
		try {
			Marca objSalida =  Mservice.insertaMarca(obj);
			if (objSalida == null) {
				salida.put("mensaje", "Error en la actualizacion");
			} else {
				salida.put("mensaje", "Se actualizo correctamente");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "Error en la actualizacion");
		}
		return ResponseEntity.ok(salida);
	}
}
