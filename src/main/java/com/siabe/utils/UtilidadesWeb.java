package com.siabe.utils;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.siabe.modelo.PermisosMenu;
import com.siabe.servicio.PermisosMenuServicio;

@Service
public class UtilidadesWeb {
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    
    public boolean direccionActual(int idUsuario) {
    	boolean registrado = false;
    	
    	UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest(); 
	    List<PermisosMenu> pm = permisosMenuServicio.todosPermisosMenu(idUsuario);
	        
	        for(int i =0;i < pm.size(); i++) {
	    //    	System.out.println("link "+builder.buildAndExpand().getPath());
	        //	System.out.println("recorrido "+pm.get(i).getUrl().equals(builder.buildAndExpand().getPath()));
	        	if(builder.buildAndExpand().getPath().equals("/inicio") || pm.get(i).getUrl().equals(builder.buildAndExpand().getPath())) {
	        		
	        		registrado = true;
	        	}
	        }
	   //     System.out.println("recorrido "+registrado);
	  return registrado;
    }
}
