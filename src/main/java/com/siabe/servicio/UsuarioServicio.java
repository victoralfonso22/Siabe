package com.siabe.servicio;

import com.siabe.dao.UsuarioDAO;
import com.siabe.modelo.Usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class UsuarioServicio {
	
	@Autowired
    private UsuarioDAO UserDAO;

	public Usuario regresaUsuario(String usuario) {
		return UserDAO.findUserAccount(usuario);
	}
	
	public String insertUsuario(String userName, String password, String nombre, String correo) {
		return UserDAO.insertaUsuario(userName, password, nombre,correo);
	}
	
	public List<Usuario> todosUsuarios(){
		return UserDAO.obtenerUsuarios();
	}
	
	public List<Usuario> todosUsuariosId(Long id){
		return UserDAO.obtenerUsuariosId(id);
	}
	
	public String usuarioActualizaEstatus(int id, int estatus) {
		return UserDAO.actualizaEstatusUsuario(id, estatus);
	}
	
	public String usuarioActualizaPassword(String password, int id) {
		return UserDAO.actualizaPassword(password,id);
	}
	
	public Usuario rescueUP(String recuperacion) {
		return UserDAO.recuperaUP(recuperacion);
	}
}
