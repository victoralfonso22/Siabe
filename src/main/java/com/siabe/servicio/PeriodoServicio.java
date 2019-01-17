package com.siabe.servicio;

import com.siabe.dao.PeriodoDAO;
import com.siabe.modelo.Periodo;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PeriodoServicio {
	
	@Autowired
    private PeriodoDAO periodoDAO;

	public Periodo regresaPeriodo(int idPeriodo) {
		return periodoDAO.regresarPeriodo(idPeriodo);
	}
	
	public String insertPeriodo(String nombre, Date fecha_inicio, Date fecha_final) {
		return periodoDAO.insertaPeriodo(nombre, fecha_inicio, fecha_final);
	}
	
	public List<Periodo> todosPerido(){
		return periodoDAO.obtenerPeriodos();
	}
	
	/*public List<Periodo> todosPeridoBD(){
		return periodoDAO.obtenerPeriodosBenDona();
	}
	
	public List<Periodo> todosPeridoIdBeca(int idTipoBeca){
		return periodoDAO.obtenerPeriodosIdBeca(idTipoBeca);
	}*/
	
	public String periodoActualizaEstatus(int id, int estatus) {
		return periodoDAO.actualizaEstatusUsuario(id, estatus);
	}
	
	public String periodoActualizaDatos(int idPeriodo, String nombre, Date fecha_inicio, Date fecha_final, int estatus) {
		return periodoDAO.actualizaDatos(idPeriodo,nombre,fecha_inicio,fecha_final, estatus);
	}	
}
