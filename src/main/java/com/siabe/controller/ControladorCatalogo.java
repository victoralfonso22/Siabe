package com.siabe.controller;

import java.security.Principal;

import com.siabe.modelo.Usuario;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.TipoBecaServicio;
import com.siabe.servicio.TipoDonativoServicio;
import com.siabe.servicio.CuentasBancariasServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.TiempoPromedioServicio;
import com.siabe.servicio.QuincenasServicio;

import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.RegionesServicio;
import com.siabe.utils.UtilidadesWeb;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControladorCatalogo {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private PermisosMenuServicio permisosMenuServicio;

	@Autowired
	private UtilidadesWeb utilidadesWeb;

	@Autowired
	private PeriodoServicio periodoServicio;

	@Autowired
	private TipoBecaServicio tipoBecaServicio;

	@Autowired
	private TipoDonativoServicio tipoDonativosServicio;

	@Autowired
	private CuentasBancariasServicio tipoMedioCobroServicio;

	@Autowired
	private RegionesServicio regionesServicio;

	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;
	
	@Autowired
	private QuincenasServicio quincenasServicio;

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

	@GetMapping(value = "/catalogos/campana")
	public String campania(Model model, Principal principal) {

		model.addAttribute("periodos", periodoServicio.todosPerido());
		return "/catalogos/campana";
	}

	@GetMapping(value = "/catalogos/periodos")
	public String periodos(Model model, Principal principal) {

		model.addAttribute("periodos", periodoServicio.todosPerido());
	//	model.addAttribute("tBeca", tipoBecaServicio.todosTipoBeca());
	
		return "/catalogos/periodos";
	}


	@GetMapping(value = "/catalogos/tBeca")
	public String tipoBeca(Model model, Principal principal) {

		model.addAttribute("tBeca", tipoBecaServicio.todosTipoBeca());

		return "/catalogos/tBeca";
	}

	@RequestMapping("/catalogos/refreshTablaPeriodo")
	public String refreshTablaPeriodos(Model model, Principal principal) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("periodos", periodoServicio.todosPerido());

		return "/catalogos/periodos :: periodos";
	}
	
	
	@RequestMapping("/catalogos/refreshTablatBeca")
	public String refreshTablaTBeca(Model model, Principal principal) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("tBeca", tipoBecaServicio.todosTipoBeca());

		return "/catalogos/tBeca :: tBecas";
	}

	@GetMapping(value = "/catalogos/regiones")
	public String regiones(Model model, Principal principal) {
		
		model.addAttribute("regiones", regionesServicio.todosRegionesPrincipales());
		model.addAttribute("periodos", periodoServicio.todosPerido());

		return "/catalogos/regiones";
	}
	
	@RequestMapping("/catalogos/refreshModalRegionesPeriodo")
	public String refreshModalRegionesPeriodo(Model model, Principal principal,@RequestParam int idPeriodo) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("regionesModal", regionesServicio.todosRegionesNoPeriodo(idPeriodo));

		return "/catalogos/regiones :: nuevaRegionPeriodo";
	}
	
	@RequestMapping("/catalogos/refreshTablaRegionesPrin")
	public String refreshTablaRegionesPrin(Model model, Principal principal) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("regiones", regionesServicio.todosRegionesPrincipales());

		return "/catalogos/regiones :: tablaRegiones";
	}

	@GetMapping(value = "/catalogos/tDonativo")
	public String tipoDonativo(Model model, Principal principal) {

		model.addAttribute("tDonativo", tipoDonativosServicio.todosTipoDonativo());

		return "/catalogos/tDonativo";
	}

	@RequestMapping("/catalogos/refreshTablatDonativo")
	public String refreshTablaTDonativo(Model model, Principal principal) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("tDonativo", tipoDonativosServicio.todosTipoDonativo());

		return "/catalogos/tDonativo :: tDonativos";
	}

	@GetMapping(value = "/catalogos/cuentasBancarias")
	public String tipoMedioCobro(Model model, Principal principal) {

		model.addAttribute("mCobro", tipoMedioCobroServicio.todosCuentasBancarias());

		return "/catalogos/cuentasBancarias";
	}

	@RequestMapping("/catalogos/refreshTablamCobro")
	public String refreshTablaMCobro(Model model, Principal principal) {
		Usuario u = new Usuario();
		if (principal != null) {
			u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
		}
		model.addAttribute("mCobro", tipoMedioCobroServicio.todosCuentasBancarias());

		return "/catalogos/cuentasBancarias :: cuentasBancarias";
	}

	@GetMapping(value = "/catalogos/tiemposPromedio")
	public String tiemposPromedio(Model model, Principal principal) {
		
	//	model.addAttribute("periodos", periodoServicio.todosPerido());
		model.addAttribute("regiones", regionesServicio.todosRegionesTP());
		model.addAttribute("areas", tiempoPromedioServicio.todosAreas());
		model.addAttribute("facultades", tiempoPromedioServicio.todosFacultades());
		
		model.addAttribute("tiemposPromedio", tiempoPromedioServicio.todosTiemposPromedio());

		return "/catalogos/tiemposPromedio";
	}
	
	@RequestMapping("/catalogos/refreshAreaNuevaTiemposPromedio")
	public String refreshAreaNuevaTiemposPromedio(Model model, Principal principal) {
		model.addAttribute("areas", tiempoPromedioServicio.todosAreas());
		return "/catalogos/tiemposPromedio :: #idArea";
	}
	
	@RequestMapping("/catalogos/refreshAreaModificarTiempoPromedio")
	public String refreshAreaModificarTiempoPromedio(Model model, Principal principal) {
		model.addAttribute("areas", tiempoPromedioServicio.todosAreas());
		return "/catalogos/tiemposPromedio :: #idAreaM";
	}
	
	@RequestMapping("/catalogos/refreshAreaNMTiempoPromedio")
	public String refreshAreaNMTiempoPromedio(Model model, Principal principal) {
		model.addAttribute("areas", tiempoPromedioServicio.todosAreas());
		return "/catalogos/tiemposPromedio :: #idAreaNM";
	}
	
	
	@RequestMapping("/catalogos/refreshFacNuevaTiemposPromedio")
	public String refreshFacNuevaTiemposPromedio(Model model, Principal principal) {
		model.addAttribute("facultades", tiempoPromedioServicio.todosFacultades());
		return "/catalogos/tiemposPromedio :: #idFac";
	}
	
	@RequestMapping("/catalogos/refreshFacModificarTiempoPromedio")
	public String refreshFacModificarTiempoPromedio(Model model, Principal principal) {
		model.addAttribute("facultades", tiempoPromedioServicio.todosFacultades());
		return "/catalogos/tiemposPromedio :: #idFacM";
	}
	
	@RequestMapping("/catalogos/refreshFacNMTiempoPromedio")
	public String refreshFacNMTiempoPromedio(Model model, Principal principal) {
		model.addAttribute("facultades", tiempoPromedioServicio.todosFacultades());
		return "/catalogos/tiemposPromedio :: #idFacNM";
	}
	
	@GetMapping(value = "/catalogos/quincenas")
	public String quincenas(Model model, Principal principal) {
		
		model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
		
		return "/catalogos/quincenas";
	}
	
	@RequestMapping("/catalogos/actualizaTablaQuincenas")
	public String actualizaTablaQuincenas(Model model, Principal principal) {
		model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
		return "/catalogos/quincenas :: #quincenasF";
	}
	
	
	
}