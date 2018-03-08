package com.siabe.controller;

import java.security.Principal;
import com.siabe.utils.UtilidadesWeb;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
 
@Controller
public class Controlador {
	
	
	@RequestMapping(value =  { "/", "/login" }, method = RequestMethod.GET)
    public String loginPage(Model model) {
 
        return "login";
    }

	@RequestMapping(value = { "/inicio" }, method = RequestMethod.GET)
    public String welcomePage(Model model , Principal principal) {     
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = UtilidadesWeb.toString(loginedUser);
        model.addAttribute("usuario", userInfo);
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
    
    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String errorPage(Model model) {
        model.addAttribute("title", "Error");
        return "errores/404";
    }
 
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {
 
        // (1) (en)
        // After user login successfully.

        String userName = principal.getName();
 
        System.out.println("User Name: " + userName);
 
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
 
        String userInfo = UtilidadesWeb.toString(loginedUser);
        model.addAttribute("usuario", userInfo);
 
        return "usuario";
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