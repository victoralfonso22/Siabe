package com.siabe.servicio;

import com.siabe.dao.RegionesDAO;
import com.siabe.modelo.Regiones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class RegionesServicio {
	
	@Autowired
    private RegionesDAO regionesDAO;

	public Regiones regresaRegion(int idRegion) {
		return regionesDAO.regresarRegion(idRegion);
	}
	
	public List<Regiones> regresaRegionesPeriodo(int idPeriodo) {
		return regionesDAO.regresarRegionesPeriodo(idPeriodo);
	}
	
	public List<Regiones> regresaRegionesPeriodoActivas(int idPeriodo) {
		return regionesDAO.regresarRegionesPeriodoActiva(idPeriodo);
	}
	
	public List<Regiones> regresaRegionesPeriodoActivasNoId(int idPeriodo, int idRegion) {
		return regionesDAO.regresarRegionesPeriodoActivaNoId(idPeriodo, idRegion);
	}
	
	public String insertRegion(String nombre, String abreviatura, int idPeriodo) {
		return regionesDAO.insertaRegion(nombre, abreviatura, idPeriodo);
	}
	
	public List<Regiones> todosRegiones(){
		return regionesDAO.obtenerRegiones();
	}
	
	public String regionesActualizaDatos(int idRegion, String nombre, String abreviatura, int idPeriodo, int estatus) {
		return regionesDAO.actualizaDatos(idRegion, nombre, abreviatura, idPeriodo, estatus);
	}
	
	public List<Regiones> autocompletarRegiones(int idPeriodo, String termino){
		return regionesDAO.autocompletarRegion(idPeriodo, termino);
	}
}
