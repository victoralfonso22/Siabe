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
	
	public List<Periodo> todosPeridosActivos(){
		return periodoDAO.obtenerPeriodosActivos();
	}
	
	public List<Periodo> periodoActivoDeportiva(int idTipoBeca){
		return periodoDAO.periodoActivoDeportiva(idTipoBeca);
	}
	
	public List<Periodo> periodoTodosDeportiva(int idTipoBeca){
		return periodoDAO.periodoTodosDeportiva(idTipoBeca);
	}
	
	public List<Periodo> todosPeridosActivosNoDeportiva(){
		return periodoDAO.obtenerPeriodosActivosSinEscuelas();
	}
	
	
	public List<Periodo> todosPeridosActivosSiDeportiva(){
		return periodoDAO.obtenerPeriodosActivosSoloEscuelas();
	}
	
	/*public List<Periodo> todosPeridoBD(){
		return periodoDAO.obtenerPeriodosBenDona();
	}
	*/
	
	public List<Periodo> todosPeridoMayores(int idPeriodo){
		return periodoDAO.obtenerPeriodosMayores(idPeriodo);
	}
	
	public List<Periodo> todosPeridoMayoresNoDeportiva(int idPeriodo){
		return periodoDAO.obtenerPeriodosMayoresNoDeportiva(idPeriodo);
	}
	
	public List<Periodo> todosPeridoMayoresSiDeportiva(int idPeriodo){
		return periodoDAO.obtenerPeriodosMayoresSiDeportiva(idPeriodo);
	}
	
	public List<Periodo> periodosXBeneficiario(String beneficiario){
		return periodoDAO.obtenerPeriodosXBeneficiario(beneficiario);
	}
	
	public List<Periodo> periodosXDonante(String donante){
		return periodoDAO.obtenerPeriodosXDonante(donante);
	}
	
	public String periodoActualizaEstatus(int id, int estatus) {
		return periodoDAO.actualizaEstatusUsuario(id, estatus);
	}
	
	public String periodoActualizaDatos(int idPeriodo, String nombre, Date fecha_inicio, Date fecha_final, int estatus) {
		return periodoDAO.actualizaDatos(idPeriodo,nombre,fecha_inicio,fecha_final, estatus);
	}	
}
