package com.siabe.controller;

import java.security.Principal;

import com.siabe.modelo.Usuario;
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.utils.UtilidadesWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

 
 
@Controller
public class Controlador {
	
	@Autowired
    private UsuarioServicio usuarioServicio;
	
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
	
	
	@RequestMapping(value =  { "/", "/login" }, method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "login";
    }

	@RequestMapping(value = { "/inicio" }, method = RequestMethod.GET)
    public String welcomePage(Model model , Principal principal) {     
	//	User loginedUser = (User) ((Authentication) principal).getPrincipal();
	//	String userInfo = UtilidadesWeb.toString(loginedUser);
        model.addAttribute("usuario", principal.getName());
        
        
        return "inicio";
    }
 
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model, Principal principal) {
         
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = UtilidadesWeb.toString(loginedUser);
        model.addAttribute("usuario", userInfo);
        
        return "admin";
    }
    
    
   /* @RequestMapping(value = "/salir", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Salir");
        return "salir";
    }*/
    
    /*@RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(Model model) {
        model.addAttribute("title", "Error");
        return "errores/404";
    }*/
 
    
    @RequestMapping(value = "/sesionExpirada", method = RequestMethod.GET)
    public String sesionExpired(Model model, Principal principal) {
 
        if (principal != null) {
        	return null;
        }else {
 
        return "/sesion/adios";}
    }
    
  
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
 
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
            String userInfo = UtilidadesWeb.toString(loginedUser);
 
            model.addAttribute("usuario", userInfo);
 
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
 
        }
 
        return "/error/403";
    }
    
   
}