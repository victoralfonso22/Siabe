package com.siabe.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.siabe.modelo.Usuario;
import com.siabe.servicio.UsuarioServicio;

import com.siabe.utils.UtilidadesWeb;
import com.siabe.servicio.ArchivoStorageServicio;
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
	private ArchivoStorageServicio archivoStorageServicio;
	
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
	public String campania(Model model, Principal principal) throws IOException {
		
		System.out.println("2323223 "+archivoStorageServicio.listarArchivos().get(0).getFilename());
		System.out.println("2323223sdfd "+archivoStorageServicio.listarArchivos().get(0).getURI());
		
		model.addAttribute("lista", archivoStorageServicio.listarArchivos());		
		return "/administracion/comprobanteFiscal";
	}
	
	
	
	

}
