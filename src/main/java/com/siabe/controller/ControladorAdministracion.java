package com.siabe.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;
import com.siabe.modelo.Usuario;
import com.siabe.servicio.UsuarioServicio;

import com.siabe.utils.UtilidadesWeb;
import com.siabe.servicio.ArchivoStorageServicio;
import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.DonativosServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.PermisosMenuServicio;




@Controller
public class ControladorAdministracion {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
	
	@Autowired
	private UtilidadesWeb utilidadesWeb;
	
	@Autowired
	private PeriodoServicio periodoServicio;
	
	@Autowired
	private BeneficiariosServicio beneficiariosServicio;
	
	@Autowired
	private DonativosServicio donativosServicio;

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
	
	@GetMapping(value = "/administracion/comprobanteFiscal")
	public String comprobante(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPerido());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/comprobanteFiscal";
	}
	
	
	@GetMapping(value = "/administracion/asignacion")
	public String asignacion(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPerido());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/asignacion";
	}
	
	@RequestMapping("/administracion/actualizaRegistrosAsig")
	public String actualizaRegistrosAsig(Model model, Principal principal,@RequestParam int idPeriodo, @RequestParam int tipoAsig) {
	//	model.addAttribute("periodos", periodoServicio.todosPeridoIdBeca(idTipoBeca));
		
		if(tipoAsig == 1) {
		
			model.addAttribute("registrosTab", beneficiariosServicio.beneficiariosActivosPeriodo(idPeriodo));
				
		
		}else {
			
			model.addAttribute("registrosTab", donativosServicio.regresaDonantesPeriodoActivas(idPeriodo));
						
		}
		
		
		
		if(principal == null) {
		return "/login";	
		}else {
		return "/administracion/asignacion :: #registrosAs";
		}
	}
	
	
	
	

}
