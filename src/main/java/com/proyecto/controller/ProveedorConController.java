package com.proyecto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Proveedor;
import com.proyecto.service.ProveedorServiceCon;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/proveedor")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProveedorConController {
	
	 @Autowired
		
	    //Listado
		private ProveedorServiceCon proveedorService;
		@GetMapping
		@ResponseBody
		public ResponseEntity<List<Proveedor>> listaProveedor(){
			List<Proveedor> lista = proveedorService.listaProveedor();
			return ResponseEntity.ok(lista);
		}
		
		//Consultas
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
				List<Proveedor>lista = proveedorService.listaProveedorPorRazonSRucUbigeoEstado("%"+razonsocial+"%",ruc , idUbigeo, "%"+contacto+"%",estado);
				if (CollectionUtils.isEmpty(lista)) {
					salida.put("mensaje", "No existen datos para mostrar");
				}else {
					salida.put("lista", lista);
					salida.put("mensaje", "Existen " + lista.size() + " elementos para mostrar");
				}
			} catch (Exception e) {
				e.printStackTrace();
				salida.put("mensaje", "Error al Buscar");
			}
			return ResponseEntity.ok(salida);
		}

}
