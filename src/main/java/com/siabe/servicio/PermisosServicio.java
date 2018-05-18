package com.siabe.servicio;

import com.siabe.dao.PermisosDAO;
import com.siabe.modelo.Permisos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PermisosServicio {
	
	@Autowired
    private PermisosDAO permisosDAO;
	
	public List<Permisos> todosPermisos(int idUsuario){
		return permisosDAO.obtenerPermisos(idUsuario);
	}
	
	public String actualizaPermiso(int idUsuario, int idMenu, int estatus) {
		return permisosDAO.cambiaPermisoU(idUsuario, idMenu, estatus);
		
	}

}
