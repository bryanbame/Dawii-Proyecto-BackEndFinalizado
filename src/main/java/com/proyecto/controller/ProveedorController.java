package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public ResponseEntity<List<Proveedor>> ListaProveedor(){
		List<Proveedor> lista= proveedorService.listaProveedor();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaProveedor(@RequestBody Proveedor obj){
		Map<String, Object> salida = new HashMap<>();
		try { 
			obj.setFechaRegistro(new Date());
			obj.setEstado(1);	
			Proveedor objSalida = proveedorService.insertaActualizaProveedor(obj);
			if (objSalida == null) {
				salida.put("mensaje", "No se registró proveedor");
			}else {
				salida.put("mensaje", "Se registró correctamente.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", "No se registró proveedor");
		}
		return ResponseEntity.ok(salida);
	}
}
