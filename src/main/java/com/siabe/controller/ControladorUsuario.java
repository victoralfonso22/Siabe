	package com.siabe.controller;

	import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.siabe.modelo.Permisos;
import com.siabe.modelo.PermisosMenu;
import com.siabe.modelo.CatalogoMenu;
import com.siabe.modelo.CatalogoSeccion;
import com.siabe.modelo.Usuario;
import com.siabe.servicio.CatalogoMenuServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.CatalogoSeccionServicio;
import com.siabe.servicio.MenuSeccionServicio;
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.PermisosServicio;
	import com.siabe.utils.UtilidadesWeb;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.security.core.Authentication;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

	 
	 
	@Controller
	public class ControladorUsuario {
		
		@Autowired
	    private UsuarioServicio usuarioServicio;
		
		
		@Autowired
	    private MenuSeccionServicio menuSeccionServicio;
		
		@Autowired
		private PermisosMenuServicio permisosMenuServicio;
		
		@Autowired
	    private UtilidadesWeb utilidadesWeb;
		
		@ModelAttribute
		public void addAttributes(Model model,Principal principal) {
				if(principal != null) {
			   Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());			   
		        model.addAttribute("nombreUsuario", u.getNombre());
		        model.addAttribute("idUsuario", u.getIdUsuario());
		        model.addAttribute("seccionPermiso", permisosMenuServicio.todosPermisosMenuXSeccion(u.getIdUsuario().intValue()));
		        model.addAttribute("menuPermiso", permisosMenuServicio.todosPermisosMenu(u.getIdUsuario().intValue()));
		        model.addAttribute("permisoGlobal",utilidadesWeb.direccionActual(u.getIdUsuario().intValue()));
				}
		}
		
		
		 @GetMapping(value = "/usuarios/alta")
		    public String usuarioAlta(Model model, Principal principal) {

		        String userName = principal.getName();        
		 
		  //      System.out.println("User Name: " + userName);
		 
		        User loginedUser = (User) ((Authentication) principal).getPrincipal();
		 
		        String userInfo = UtilidadesWeb.toString(loginedUser);
		        model.addAttribute("usuario", userInfo);
		 
		        return "/usuarios/alta";
		    }
		    
		    
		    @GetMapping(value = "/usuarios/suspenActivacion")
		    public String usuarioBaja(Model model, Principal principal) {
		    	Usuario u = new Usuario();
		    	if(principal != null) {
					    u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());					  
				        model.addAttribute("nombreUsuario", u.getNombre());
				        model.addAttribute("idUsuario", u.getIdUsuario());
						}
		    	model.addAttribute("usuarios", usuarioServicio.todosUsuariosId(u.getIdUsuario()));
		 
		        return "/usuarios/suspenActivacion";
		    }
		    
		    
		    @GetMapping(value = "/usuarios/password")
		    public String usuarioCambioPassword(Model model, Principal principal) {
		    	Usuario u = new Usuario();
		    	if(principal != null) {
					    u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());					  
				        model.addAttribute("nombreUsuario", u.getNombre());
				        model.addAttribute("idUsuario", u.getIdUsuario());
						}
		    	if(u.getSuperU() == 1) {
		    	model.addAttribute("usuarios", usuarioServicio.todosUsuarios());
		    	}else {
		    		model.addAttribute("usuarios", usuarioServicio.regresaUsuario(u.getUsuario()));
		    	}
		 
		        return "/usuarios/password";
		    }
		    
		    
		    @GetMapping(value = "/usuarios/permisos")
		    public String usuarioPermisos(Model model, Principal principal) {
		    	Usuario u = new Usuario();
		    	if(principal != null) {
					    u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());					  
				        model.addAttribute("nombre", u.getUsuario());
				        model.addAttribute("idUsuario", u.getIdUsuario());
						}
		    	if(u.getSuperU() == 1) {
		    	model.addAttribute("usuarios", usuarioServicio.todosUsuarios());
		    	}else {
		    		model.addAttribute("usuarios", usuarioServicio.regresaUsuario(u.getUsuario()));
		    	}
	    	
		    	
		    	model.addAttribute("secciones", menuSeccionServicio.todosMenuSeccion());
		    	model.addAttribute("menu", menuSeccionServicio.todosMenuSeccionPermisos());
		 
		        return "/usuarios/permisos";
		    }
		    
		    
		    @RequestMapping("/usuarios/refreshEstatusUsuario")
			public String refreshItem(Model model, Principal principal) {	    
		    	Usuario u = new Usuario();
		    	if(principal != null) {
					    u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());					  
				        model.addAttribute("nombreUsuario", u.getNombre());
				        model.addAttribute("idUsuario", u.getIdUsuario());
						}
		    	model.addAttribute("usuarios", usuarioServicio.todosUsuariosId(u.getIdUsuario()));

			    return "/usuarios/suspenActivacion :: usuarios";
			}
		    
		    
		    @RequestMapping("/usuarios/refreshPermisoUsuario")
			public String refreshPermisoItem(Model model, Principal principal) {	    
		    	Usuario u = new Usuario();
		    	if(principal != null) {
					    u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());					  
				        model.addAttribute("nombreUsuario", u.getNombre());
				        model.addAttribute("idUsuario", u.getIdUsuario());
						}
		    	model.addAttribute("usuarios", usuarioServicio.todosUsuariosId(u.getIdUsuario()));

			    return "/usuarios/permisos :: tablaPermisos";
			}
	    
	   
	}