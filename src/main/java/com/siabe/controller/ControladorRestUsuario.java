package com.siabe.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.siabe.modelo.Usuario;
import com.siabe.modelo.Permisos;
import com.siabe.modelo.CatalogoSeccion;
import com.siabe.modelo.CatalogoMenu;
import com.siabe.responseJSON.Response;
import com.siabe.servicio.CatalogoMenuServicio;
import com.siabe.servicio.CatalogoSeccionServicio;
import com.siabe.servicio.PermisosServicio;
import com.siabe.servicio.UsuarioServicio;
import com.siabe.utils.PasswordEncryptado;



@RestController
public class ControladorRestUsuario {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private PermisosServicio permisosServicio;
	
	@Autowired
    private CatalogoMenuServicio catalogoMenuServicio;
	
	@Autowired
    private CatalogoSeccionServicio catalogoSeccionServicio;
	
	
	
	@ModelAttribute
	public void addAttributes(Model model,Principal principal) {
			if(principal != null) {
		   Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
	        model.addAttribute("nombreUsuario", u.getNombre());
	        model.addAttribute("idUsuario", u.getIdUsuario());
	        
	        
			}
	}
	
	@PostMapping(value = "/usuarios/ajaxAlta")
	public Response postUsuarioAlta(@RequestBody Usuario u) {
		
		PasswordEncryptado passwordEncryptado = new PasswordEncryptado();
		
		Response response = new Response(usuarioServicio.insertUsuario(u.getUsuario(), passwordEncryptado.encrytePassword(u.getPassword()), u.getNombre()), null);
		
		return response;
		
	}
	
	@PostMapping(value = "/usuarios/ajaxBaja")
	public String postUsuarioBaja(@RequestParam int id, @RequestParam int estatus) {
		
		String respuesta = usuarioServicio.usuarioActualizaEstatus(id, estatus);
		
		return respuesta;
		
	}
	
	
	@PostMapping(value = "/usuarios/ajaxPassword")
	public String postUsuarioPassword(@RequestParam String password, @RequestParam int id) {
		
		PasswordEncryptado passwordEncryptado = new PasswordEncryptado();
		
		String response = usuarioServicio.usuarioActualizaPassword(passwordEncryptado.encrytePassword(password), id);
		
		return response;
		
	}
	
	
	@PostMapping(value = "/usuarios/ajaxPermisos")
	public String postUsuarioPermisos(@RequestParam int id) {
		String response = "";
		if(id != 0) {
		
		 response = "<table class=\"table tabla\" th:fragment=\"tablaPermisos\" id=\"tablaPermisos\"  >\r\n" + 
				"				<tr >";
				
		List<Permisos> permisosUsuario = new ArrayList<Permisos>(permisosServicio.todosPermisos(id));
				
		catalogoSeccionServicio.regresaSecciones();
		
		for(int i = 0; i < catalogoSeccionServicio.regresaSecciones().size();i++) {
			response+="<th>"+catalogoSeccionServicio.regresaSecciones().get(i).getSeccion()+"</th>";			
		}
		response+="</tr><tr>";
		
		
		List<CatalogoSeccion> lcs = catalogoSeccionServicio.regresaSecciones();
		
		for(int i = 0; i < lcs.size();i++) {
			
			List<CatalogoMenu> lcm = catalogoMenuServicio.todosMenuSeccion(lcs.get(i).getIdCatalogoSeccion());
			
			response+="<td><table class=\"table tabla\">";
			
			for(int a = 0; a < lcm.size();a++) {
				
				String claseTD = "tdSencillo";
				int estatusPermiso = 0;
				for(int b = 0; b < permisosUsuario.size();b++) {
					if(permisosUsuario.get(b).getIdCatalogoMenu() == lcm.get(a).getIdCatalogoMenu()) {
						claseTD = "tdSencilloPermiso";
						estatusPermiso = 1;
					}
				}
				response+="<tr><td title='Cambiar permiso' class='"+claseTD+"' id="+lcm.get(a).getIdCatalogoMenu()+" onclick='cambiaPermiso("+lcm.get(a).getIdCatalogoMenu()+","+id+","+estatusPermiso+");'>"+lcm.get(a).getModulo()+"</td></tr>";
				
			}
			
			
			response+="</table></td>";		
		}
		
		response+="</tr></th:block>" +
				"</table>";
		
		}else {
			response = "Nada";
		}
	//	System.out.println(response);
		return response;
	}
	
	
	@PostMapping(value = "/usuarios/ajaxCambioPermiso")
	public String postUsuarioCambiaPermiso(@RequestParam int id, @RequestParam int idMenu, @RequestParam int estatus) {
		
		String respuesta = permisosServicio.actualizaPermiso(id, idMenu, estatus);

		return respuesta;
		
	}
	 
	/* @RequestMapping(value = "/usuarios/ajaxAlta", method = RequestMethod.POST) 
	 public String ajaxAlta(Model model,@RequestParam("nombreCompleto") String nombreCompleto, @RequestParam("username") String usuario) {		 	
		 
	        model.addAttribute("NomC",nombreCompleto);
	 
	        return "/usuarios/alta";
	    }*/
}
