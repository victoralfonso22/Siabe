package com.siabe.servicio;

import com.siabe.dao.CatalogoSeccionDAO;
import com.siabe.modelo.CatalogoSeccion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class CatalogoSeccionServicio {
	
	@Autowired
    private CatalogoSeccionDAO catalogoSeccionDAO;

	public  List<CatalogoSeccion> regresaSecciones() {
		return catalogoSeccionDAO.obtenerCatalogoSeccion();
	}

}
