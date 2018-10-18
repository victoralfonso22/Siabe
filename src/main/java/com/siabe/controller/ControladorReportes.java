package com.siabe.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siabe.modelo.Usuario;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.TipoBecaServicio;
import com.siabe.utils.UtilidadesWeb;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.RegionesServicio;



@Controller
public class ControladorReportes {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
	
	@Autowired
	private UtilidadesWeb utilidadesWeb;
	
	@Autowired
	private TipoBecaServicio tipoBecaServicio;
	
	@Autowired
	private PeriodoServicio periodoServicio;
	
	@Autowired
	private RegionesServicio regionesServicio;

	@ModelAttribute
	public void addAttributes(Model model, Principal principal) {
		if (principal != null) {
			Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
			model.addAttribute("seccionPermiso",
					permisosMenuServicio.todosPermisosMenuXSeccion(u.getIdUsuario().intValue()));
			model.addAttribute("menuPermiso", permisosMenuServicio.todosPermisosMenu(u.getIdUsuario().intValue()));
			model.addAttribute("permisoGlobal", utilidadesWeb.direccionActual(u.getIdUsuario().intValue()));
		}
	}
	
	@GetMapping(value = "/reportes/beneficiarios")
	public String campania(Model model, Principal principal) {

		model.addAttribute("tbecas", tipoBecaServicio.todosTipoBeca());		
		return "/reportes/beneficiarios";
	}
	
	@RequestMapping("/reportes/actualizaPeriodoRepGen")
	public String actualizaSelPeriodos(Model model, Principal principal,@RequestParam int idTipoBeca) {
		model.addAttribute("periodos", periodoServicio.todosPeridoIdBeca(idTipoBeca));
		
		if(principal == null) {
		return "/login";	
		}else {
		return "/reportes/beneficiarios :: #idPeriodoGeneral";
		}
	}
	
	@RequestMapping("/reportes/actualizaRegionRepGen")
	public String actualizaSelRegiones(Model model, Principal principal,@RequestParam int idPeriodo) {
		model.addAttribute("regiones", regionesServicio.regresaRegionesPeriodo(idPeriodo));
		return "/reportes/beneficiarios :: #idRegionGeneral";
	}
	
	

}
