package com.siabe.servicio;


import java.util.ArrayList;
import java.util.List;
 
import com.siabe.dao.RolesDAO;
import com.siabe.dao.UsuarioDAO;
import com.siabe.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class UsuarioServicio {
	
	@Autowired
    private UsuarioDAO UserDAO;

	public Usuario regresaUsuario(String usuario) {
		return UserDAO.findUserAccount(usuario);
	}
}
