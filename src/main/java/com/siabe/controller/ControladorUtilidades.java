package com.siabe.controller;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siabe.modelo.Regiones;
import com.siabe.modelo.Usuario;
import com.siabe.modelo.Areas;
import com.siabe.modelo.Facultades;
import com.siabe.servicio.RegionesServicio;
import com.siabe.servicio.TiempoPromedioServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.utils.UtilidadesWeb;

@RestController
public class ControladorUtilidades {

	@Autowired
	private RegionesServicio regionesServicio;
	
	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired 
	private UtilidadesWeb utilidadesWeb;

	
	@PostMapping(value = "/catalogos/ajaxRegionesPeriodoSlt")
	public String postAjaxRegionesPeriodo(@RequestParam int idPeriodo, @RequestParam String nombreIdRegion,@RequestParam int idRegion, @RequestParam String nombreIdArea, @RequestParam String divIdArea, @RequestParam String nombreIdFac, @RequestParam String divIdFac) {
	
		String response = "";
		if (idPeriodo != 0) {

			List<Regiones> regionesPeriodo = new ArrayList<Regiones>(
					regionesServicio.regresaRegionesPeriodo(idPeriodo));
			String seleccionado = "";			
			if(idRegion == 0) {
				seleccionado = "selected";
			}
			
			response +=	""
					+ "<select class=\"form-control selectText\" required onchange=\"areasRegionesSlt('"+nombreIdArea+"',this.value,'"+divIdArea+"',0,'"+nombreIdFac+"','"+divIdFac+"')\" id='"+nombreIdRegion+"'> "
					+ "<option value='' "+seleccionado+">Selecciona región...</option>  ";
			for (int b = 0; b < regionesPeriodo.size(); b++) {

					if(regionesPeriodo.get(b).getIdRegion() == idRegion) {
						seleccionado = "selected";
					}else {
						seleccionado = "";
					}

				
				response += "<option "+seleccionado+" value=\"" + regionesPeriodo.get(b).getIdRegion() + "\">"
						+ regionesPeriodo.get(b).getNombre() + "</option>";
				
				
			}
			response += "</select>";
			
			

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	
	@PostMapping(value = "/catalogos/ajaxAreasRegionesSlt")
	public String postAjaxAreasRegiones(@RequestParam String nombreIdArea,@RequestParam int idRegion,@RequestParam int idArea,@RequestParam String nombreIdFac,@RequestParam String divIdFac) {
		String response = "";
		if (idRegion != 0) {
			
			List<Areas> areasRegion = new ArrayList<Areas>(
					tiempoPromedioServicio.todosAreasRegion(idRegion));
			String seleccionado = "";			
			if(idArea == 0) {
				seleccionado = "selected";
			}

			response +=	""
					+ "<select class=\"form-control selectText\" required onchange=\"facultadesAreasSlt('"+nombreIdFac+"',this.value,'"+divIdFac+"',0,"+idRegion+");\" id='"+nombreIdArea+"'> "
					+ "<option value='' "+seleccionado+">Selecciona area...</option>  ";
			for (int b = 0; b < areasRegion.size(); b++) {

					if(areasRegion.get(b).getIdArea() == idArea) {
						seleccionado = "selected";
					}else {
						seleccionado = "";
					}

				
				response += "<option "+seleccionado+" value=\"" + areasRegion.get(b).getIdArea()+ "\">"
						+ areasRegion.get(b).getNombre() + "</option>";
				
				
			}
			response += "</select>";
			
			

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	@PostMapping(value = "/catalogos/ajaxFacultadesAreasSlt")
	public String postAjaxFacultadesAreas(@RequestParam String nombreIdFac, @RequestParam int idArea,@RequestParam int idFacultad,@RequestParam int idRegion) {
		String response = "";
		if (idArea != 0) {
			
			List<Facultades> facultadesAreas = new ArrayList<Facultades>(
					tiempoPromedioServicio.todosFacultades(idArea,idRegion));
			String seleccionado = "";			
			if(idFacultad == 0) {
				seleccionado = "selected";
			}
			
			response +=	""
					+ "<select class=\"form-control selectText\" required  id='"+nombreIdFac+"'> "
					+ "<option value='' "+seleccionado+">Selecciona facultad...</option>  ";
			for (int b = 0; b < facultadesAreas.size(); b++) {

					if(facultadesAreas.get(b).getIdFacultad() == idFacultad) {
						seleccionado = "selected";
					}else {
						seleccionado = "";
					}

				
				response += "<option "+seleccionado+" value=\"" + facultadesAreas.get(b).getIdFacultad()+ "\">"
						+ facultadesAreas.get(b).getNombre() + "</option>";
				
				
			}
			response += "</select>";
			
			

		} else {
			response = "Nada";
		}
		// System.out.println(response);

		return response;

	}
	
	@PostMapping(value = "/ajaxRescueUP")
	public String postAjaxRescueUP(@RequestParam String recuperacion) throws Exception {
		Usuario us = usuarioServicio.rescueUP(recuperacion);
		if(us != null) {
			return utilidadesWeb.sendEmailRecuperacionUP(us.getCorreo(), us.getNombre(), us.getUsuario(), us.getPassword(), us.getIdUsuario().intValue());
			
		
		}else {
			return "nulo";
		}

	}
	
	

    
	
	
}
