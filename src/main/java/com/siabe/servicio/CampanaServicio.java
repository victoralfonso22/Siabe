package com.siabe.servicio;

import com.siabe.dao.CampanaDAO;
import com.siabe.modelo.Campana;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class CampanaServicio {
	
	@Autowired
    private CampanaDAO campanaDAO;

	public Campana regresaCampana(int idCampana) {
		return campanaDAO.regresarCampana(idCampana);
	}
	
	public String insertCampana(String nombre, int idRegion) {
		return campanaDAO.insertaCamapana(nombre, idRegion);
	}
	
	public List<Campana> todosCampana(){
		return campanaDAO.obtenerCampanas();
	}
	
	public List<Campana> todosCampanaRegion(int idRegion){
		return campanaDAO.obtenerCampanasRegion(idRegion);
	}
	
	public String actualizarCampana(int idCampana, String nombre, int estatus, int idRegion) {
		return campanaDAO.actualizaCampana(idCampana, nombre,estatus, idRegion);
	}
}
