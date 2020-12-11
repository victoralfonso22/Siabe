package com.siabe.servicio;

import com.siabe.dao.NominaDAO;

import com.siabe.modelo.Nomina;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class NominaServicio {
	
	@Autowired
    private NominaDAO nominaDAO;

	public Nomina regresarNominaPeriodo(int idPeriodo) {
		return nominaDAO.regresarNominaPeriodo(idPeriodo);
	}
	
	public String insertNomina(int idDonativo, int numDescuento, double descuentoQuincenal, double saldo, int idQuincenActual, int idUsuario) {
		return nominaDAO.insertaNomina(idDonativo, numDescuento, descuentoQuincenal, saldo, idQuincenActual, idUsuario);
	}
	
	public List<Nomina> obtenerNominas(){
		return nominaDAO.obtenerNominas();
	}
	
	public List<Nomina> obtenerNominasPeriodo(int idPeriodo, int idQuincena){
		return nominaDAO.obtenerNominasPeriodo(idPeriodo, idQuincena);
	}
	
	public List<Nomina> autocompletarDonantesNomina(String termino, int idPeriodo){
		return nominaDAO.autocompletarDonantesNomina(termino, idPeriodo);
	}
	
	public int numDescuento(int idDonativo) {
		return nominaDAO.getNumDescuento(idDonativo);
	}
	
	public double saldo(int idDonativo) {
		return nominaDAO.getSaldo(idDonativo);
	}
	
	public Nomina verificaExiste(int idDonativo,int idPeriodo, int idQuincena) {
		return nominaDAO.verificaExiste(idDonativo, idPeriodo, idQuincena);
	}
	
	public String actualizaNomina( double descuentoQuincenal, double saldo,int idUsuario, int idNomina) {
		return nominaDAO.actualizaNomina(descuentoQuincenal, saldo, idUsuario, idNomina);
	}
	
	public String eliminaNomina(  int idNomina) {
		return nominaDAO.eliminaNomina( idNomina);
	}
}
