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
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.TipoBecaServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.utils.UtilidadesWeb;

@Controller
public class ControladorEgresos {
	
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
	
	@GetMapping(value = "/egresos/beneficiarios")
	public String campania(Model model, Principal principal) {

		model.addAttribute("tbecas", tipoBecaServicio.todosTipoBeca());		
	//	model.addAttribute("periodos", periodoServicio.todosPeridosActivos());
		return "/egresos/beneficiarios";
	}
	
	@RequestMapping("/egresos/actualizaPeriodosTBecaMod")
	public String actualizaSelPeriodos(Model model, Principal principal,@RequestParam String nombreCompletoBene,@RequestParam int idPeriodo) {
		model.addAttribute("periodosMof", periodoServicio.periodosXBeneficiario(nombreCompletoBene));
		model.addAttribute("tbecasMod", tipoBecaServicio.tipoBecaXBeneficiario(nombreCompletoBene, idPeriodo));
		
		if(principal == null) {
		return "/login";	
		}else {
		return "/egresos/beneficiarios :: #periodoTBecaMod";
		}
	}
	
	@RequestMapping("/egresos/periodosActivoDeportiva")
	public String periodosActivoDeportiva(Model model, Principal principal,@RequestParam int idTipoBeca,@RequestParam String atributo, @RequestParam String link) {

		model.addAttribute(atributo, periodoServicio.periodoActivoDeportiva(idTipoBeca));
			
		return link;
	}

}
