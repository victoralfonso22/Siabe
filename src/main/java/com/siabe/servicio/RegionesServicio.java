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
	
	public Regiones regresaRegionUnica(int idRegion) {
		return regionesDAO.regresarRegionUnica(idRegion);
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
	
	public String insertRegion(String nombre, String abreviatura) {
		return regionesDAO.insertaRegion(nombre, abreviatura);
	}
	
	public String insertRegionPeriodoN(int idRegion, int idPeriodo) {
		return regionesDAO.insertaRegionPeridoN(idRegion, idPeriodo);
	}
	
	public List<Regiones> todosRegiones(){
		return regionesDAO.obtenerRegiones();
	}

	public List<Regiones> todosRegionesTP(){
		return regionesDAO.obtenerRegionesTP();
	}
	
	public List<Regiones> todosRegionesPrincipales(){
		return regionesDAO.obtenerRegionesPrincipales();
	}
	
	public List<Regiones> todosRegionesNoPeriodo(int idPeriodo){
		return regionesDAO.obtenerRegionesNoPeriodo(idPeriodo);
	}
	
	public String regionesActualizaDatos(int idRegion, String nombre, String abreviatura) {
		return regionesDAO.actualizaDatos(idRegion, nombre, abreviatura);
	}
	
	public String regionesActualizaDatosTR(int estatus, int idPeriodo, int idRegion) {
		return regionesDAO.actualizaDatosTR(estatus, idPeriodo, idRegion);
	}
	
	public List<Regiones> autocompletarRegiones(int idPeriodo, String termino){
		return regionesDAO.autocompletarRegion(idPeriodo, termino);
	}

}
