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
	
	
	/*****************************************************TIEMPO PROMEDIO*****************************************************************************************/
	public TiempoPromedio regresaTiempoPromedio(int idTiempoPromedio) {
		return tiempoPromedioDAO.regresarTiempoPromedio(idTiempoPromedio);
	}
	
	public List<TiempoPromedio> todosTiemposPromedio(){
		return tiempoPromedioDAO.obtenerTiemposPromedio();
	}
	
	public List<TiempoPromedio> todosTiemposPromedioTermino(String termino){
		return tiempoPromedioDAO.regresarTiempoPromedioTermino(termino);
	}
	
	
	public List<TiempoPromedio> todosTiemposPromedioPeriodo(){
		return tiempoPromedioDAO.obtenerTiemposPromedio();
	}
	
	public List<TiempoPromedio> todosTiemposPromedioRegion(int idRegion){
		return tiempoPromedioDAO.obtenerTiemposPromedioRegion(idRegion);
	}
	
	/*public List<TiempoPromedio> todosTiemposPromedioRegion(int idPeriodo,int idRegion){
		return tiempoPromedioDAO.tiemposPromedioIdRegion(idPeriodo, idRegion);
	}*/
	
	public List<TiempoPromedio> todosTiemposPromedioRegionInput(int idRegion, String termino, int carrera, int facultad, int area){
		return tiempoPromedioDAO.tiemposPromedioIdRegionInput( idRegion, termino, carrera, facultad, area);
	}
	
	public String insertCarrera(int idFacultad, int idArea, int idRegion,String nombre, String nivel, String modalidad, int plan, int periodoPromedio) {
		return tiempoPromedioDAO.insertaCarrera(idFacultad, idArea, idRegion, nombre, nivel, modalidad, plan, periodoPromedio);
	}
	
	public String actualizarCarrera(int idFacultad, int idArea, int idRegion,String nombre, String nivel, String modalidad, int plan, int periodoPromedio, int estatus, int idCarrera) {
		return tiempoPromedioDAO.actualizaCarrera(idFacultad, idArea, idRegion, nombre, nivel, modalidad, plan, periodoPromedio, estatus, idCarrera);
	}
	
	public List<TiempoPromedio> autocompleteNivel(int idRegion, int idArea, int idFac, String term){		
		return tiempoPromedioDAO.autocompleteCarrera("nivel like '%"+term+"%' and id_region = "+idRegion+" and id_area = "+idArea+" and id_facultad = "+idFac+" and estatus= 1;");
	}
	
	public List<TiempoPromedio> autocompleteModalidad(int idRegion, int idArea, int idFac,String nivel, String term){		
		return tiempoPromedioDAO.autocompleteCarrera("nivel like '%"+term+"%' and id_region = "+idRegion+" and id_area = "+idArea+" and id_facultad = "+idFac+" and nivel = '"+nivel+"' and estatus= 1;");
	}
	
	public List<TiempoPromedio> autocompleteCarrera(int idRegion, int idArea, int idFac,String nivel,String modalidad, String term){		
		return tiempoPromedioDAO.autocompleteCarrera("nivel like '%"+term+"%' and id_region = "+idRegion+" and id_area = "+idArea+" and id_facultad = "+idFac+" and nivel = '"+nivel+"' and modalidad = '"+modalidad+"' and estatus= 1;");
	}
	
	/*********************************************************AREAS*************************************************************************/
	
	public List<Areas> todosAreas(){
		return tiempoPromedioDAO.obtenerAreas();
	}
	
	public List<Areas> autocompletarAreas(int idRegion, String termino){
		return tiempoPromedioDAO.autocompleteAreas(idRegion, termino);
	}
	
	public String insertArea(String nombre) {
		return tiempoPromedioDAO.insertaAreas(nombre);
	}
	
	
	public String actualizarArea(int idArea, String nombre) {
		return tiempoPromedioDAO.actualizaAreas(idArea, nombre);
	}
	

	public List<Areas> todosAreasRegion(int idRegion){
		return tiempoPromedioDAO.obtenerAreasRegiones(idRegion);
	}
	
	/**************************************************************FACULTADES*****************************************************************************/
	
	public List<Facultades> todosFacultades(int idArea, int idRegion){
		return tiempoPromedioDAO.obtenerFacultades(idArea,idRegion);
	}
	
	public List<Facultades> todosFacultades(){
		return tiempoPromedioDAO.obtenerFacultades();
	}
	
	public List<Facultades> autocompleteFacultad(int idRegion, int idArea, String termino){
		return tiempoPromedioDAO.autocompleteFacultad(idRegion, idArea, termino);
	}
	

	public String insertFacultad(String nombre) {
		return tiempoPromedioDAO.insertaFacultad(nombre);
	}
	
	
	public String actualizarFacultad(int idFacultad,String nombre) {
		return tiempoPromedioDAO.actualizaFacultad(idFacultad, nombre);
	}
	


}
