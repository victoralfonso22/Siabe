package com.siabe.servicio;

import com.siabe.dao.MenuSeccionDAO;
import com.siabe.modelo.MenuSeccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class MenuSeccionServicio {
	
	@Autowired
    private MenuSeccionDAO menuSeccionDAO;

	public List<MenuSeccion> todosMenuSeccion(){
		return menuSeccionDAO.obtenerMenuSeccion();
	}
	
	public List<MenuSeccion> todosMenuSeccionPermisos(){
		return menuSeccionDAO.obtenerMenuSeccionPermisos();
	}
	
	

}
