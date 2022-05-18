package com.proyecto.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.entidad.Producto;
import com.proyecto.service.ProductoService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/producto")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class ProductoController {

	@Autowired
	private ProductoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Producto>> listaProducto() {
		List<Producto> lista = service.listaProducto();
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaProducto(@RequestBody Producto obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			
				obj.setIdProducto(0);// Para que registre, sino actualiza
				obj.setFechaRegistro(new Date());
				obj.setEstado(1);
				Producto objSalida = service.insertaProducto(obj);
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
	
	@GetMapping("/filtroProductop")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaPorFiltros(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "serie", required = false, defaultValue = "") String serie,
			@RequestParam(value = "idMarca", required = false, defaultValue = "-1") int idMarca,
			@RequestParam(value = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Producto> lista = service.listaProductosPorFiltro("%"+nombre+"%", serie, idMarca, idPais, estado);
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Existen " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
}
