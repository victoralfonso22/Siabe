package com.siabe.servicio;

import com.siabe.dao.CatalogoMenuDAO;
import com.siabe.modelo.CatalogoMenu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class CatalogoMenuServicio {
	
	@Autowired
    private CatalogoMenuDAO catalogoMenuDAO;

	public List<CatalogoMenu> todosMenuSeccion(int idSeccion){
		return catalogoMenuDAO.obtenerCatalogoMenuSeccion(idSeccion);
	}
	
	public List<CatalogoMenu> todosMenu(){
		return catalogoMenuDAO.obtenerCatalogoMenu();
	}

}
