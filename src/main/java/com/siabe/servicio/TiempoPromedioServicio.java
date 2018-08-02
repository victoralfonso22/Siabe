package com.siabe.servicio;

import com.siabe.dao.TiempoPromedioDAO;
import com.siabe.modelo.TiempoPromedio;

import com.siabe.modelo.Facultades;
import com.siabe.modelo.Areas;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class TiempoPromedioServicio {
	
	@Autowired
    private TiempoPromedioDAO tiempoPromedioDAO;
	
	public TiempoPromedio regresaTiempoPromedio(int idTiempoPromedio) {
		return tiempoPromedioDAO.regresarTiempoPromedio(idTiempoPromedio);
	}
	
	public List<TiempoPromedio> todosTiemposPromedio(){
		return tiempoPromedioDAO.obtenerTiemposPromedio();
	}
	
	public List<TiempoPromedio> todosTiemposPromedioRegion(int idRegion){
		return tiempoPromedioDAO.obtenerTiemposPromedioRegion(idRegion);
	}
	
	public List<Areas> todosAreas(){
		return tiempoPromedioDAO.obtenerAreas();
	}
	
	public List<Facultades> todosFaculatdes(int idArea){
		return tiempoPromedioDAO.obtenerFacultades(idArea);
	}
	
	public String insertArea(String nombre) {
		return tiempoPromedioDAO.insertaAreas(nombre);
	}
	
	
	public String actualizarArea(int idArea, String nombre) {
		return tiempoPromedioDAO.actualizaAreas(idArea, nombre);
	}
	
	public String insertFacultad(String nombre, int idArea) {
		return tiempoPromedioDAO.insertaFacultad(nombre, idArea);
	}
	
	
	public String actualizarFacultad(int idFacultad,String nombre,int idArea) {
		return tiempoPromedioDAO.actualizaFacultad(idFacultad, nombre, idArea);
	}
	
	public String insertCarrera(int idFacultad, int idRegion,String nombre, String nivel, String modalidad, int plan, int periodoPromedio) {
		return tiempoPromedioDAO.insertaCarrera(idFacultad, idRegion, nombre, nivel, modalidad, plan, periodoPromedio);
	}
	
	
	public String actualizarCarrera(int idFacultad, int idRegion,String nombre, String nivel, String modalidad, int plan, int periodoPromedio, int idCarrera) {
		return tiempoPromedioDAO.actualizaCarrera(idFacultad, idRegion, nombre, nivel, modalidad, plan, periodoPromedio, idCarrera);
	}
}
