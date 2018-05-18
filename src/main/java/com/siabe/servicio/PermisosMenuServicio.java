package com.siabe.servicio;

import com.siabe.dao.PermisosMenuDAO;
import com.siabe.modelo.PermisosMenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PermisosMenuServicio {
	
	@Autowired
    private PermisosMenuDAO permisosMenuDAO;
	
	public List<PermisosMenu> todosPermisosMenu(int idUsuario){
		return permisosMenuDAO.obtenerPermisosMenu(idUsuario);
	}
	
	public List<PermisosMenu> todosPermisosMenuTodo(){
		return permisosMenuDAO.obtenerPermisosMenuTodos();
	}

	public List<PermisosMenu> todosPermisosMenuXSeccion(int idUsuario){
		return permisosMenuDAO.obtenerPermisosMenuXSeccion(idUsuario);
	}

}
