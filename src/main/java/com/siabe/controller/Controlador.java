package com.siabe.controller;

import java.security.Principal;

import com.siabe.dao.UsuarioDAO;
import com.siabe.modelo.Usuario;
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
    private UsuarioDAO UserDAO;
	
	@ModelAttribute
	public void addAttributes(Model model,Principal principal) {
			if(principal != null) {
		   Usuario u = (Usuario) this.UserDAO.findUserAccount(principal.getName());
	        model.addAttribute("nombreUsuario", u.getNombre());
	        model.addAttribute("idUsuario", u.getid());
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
 
    
    @RequestMapping(value = "/salir", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Salir");
        return "salir";
    }
    
    /*@RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(Model model) {
        model.addAttribute("title", "Error");
        return "errores/404";
    }*/
 
    @RequestMapping(value = "/usuarios/alta", method = RequestMethod.GET)
    public String usuarioAlta(Model model, Principal principal) {
 
        // (1) (en)
        // After user login successfully.

        String userName = principal.getName();        
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = UtilidadesWeb.toString(loginedUser);
        model.addAttribute("usuario", userInfo);
 
        return "/usuarios/alta";
    }
    
    
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
 
        return "/errores/403";
    }
 
}