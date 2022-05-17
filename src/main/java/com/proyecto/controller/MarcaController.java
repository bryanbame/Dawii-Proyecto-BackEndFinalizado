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

import com.proyecto.entidad.Marca;
import com.proyecto.service.MarcaService;
import com.proyecto.util.AppSettings;

@RestController
@RequestMapping("/url/marca")
@CrossOrigin(origins = AppSettings.URL_CROSS_ORIGIN)
public class MarcaController {
	
	@Autowired
	private MarcaService Mserv;

	@PostMapping
	@ResponseBody
	public ResponseEntity<HashMap<String, Object>> insertaMarca(@RequestBody Marca obj) {
		HashMap<String, Object> salida = new HashMap<String, Object>();
		try {
			obj.setIdMarca(0);// Para que registre, sino actualiza
			obj.setFechaRegistro(new Date()); //probando default de fecha registro - si funciona
			obj.setEstado(1); //probando default de estado - si funciona
			Marca objSalida = Mserv.insertaMarca(obj);
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
	
	@GetMapping("/porNombreDescCertPaisConParametros")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listaNombreDescCertPaisConParametros(
			@RequestParam(value = "nombre", required = false, defaultValue = "") String nombre,
			@RequestParam(value = "descripcion", required = false, defaultValue = "") String descripcion,
			@RequestParam(value = "certificado", required = false, defaultValue = "") String certificado,
			@RequestParam(value = "idPais", required = false, defaultValue = "-1") int idPais,
			@RequestParam(name = "estado", required = true, defaultValue = "1") int estado){
		
		Map<String, Object> salida = new HashMap<String, Object>();
		try {
			List<Marca> lista = Mserv.listaMarcaPorNombreDescCertPais("%"+nombre+"%", "%"+descripcion+"%",certificado, idPais, estado);
			if(CollectionUtils.isEmpty(lista)){
				salida.put("mensaje", "No existe elementos para la consulta");
			}else {
				salida.put("lista", lista);
				salida.put("mensaje", "Se tiene " + lista.size() + " elementos");
			}
		} catch (Exception e) {
			salida.put("mensaje", "Error : " + e.getMessage());
		}
		
		return ResponseEntity.ok(salida);
	}
}
