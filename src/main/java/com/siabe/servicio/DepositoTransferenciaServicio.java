package com.siabe.servicio;

import com.siabe.dao.DepositoTransferenciaDAO;

import com.siabe.modelo.DepositoTransferencia;

import java.sql.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class DepositoTransferenciaServicio {
	
	@Autowired
    private DepositoTransferenciaDAO depositoTransferenciaDAO;

	public DepositoTransferencia regresarDepositoTransferenciaPeriodo(int idPeriodo) {
		return depositoTransferenciaDAO.regresarDepositoTransferenciaPeriodo(idPeriodo);
	}
	
	public String insertDepositoTransferencia(int idDonativo, int numDescuento, double descuento, double saldo, Date fecha, int idUsuario) {
		return depositoTransferenciaDAO.insertaDepositoTransferencia(idDonativo, numDescuento, descuento, saldo, fecha, idUsuario);
	}
	
	public List<DepositoTransferencia> obtenerDepositoTransferencia(){
		return depositoTransferenciaDAO.obtenerDepositoTransferencia();
	}
	
	public List<DepositoTransferencia> obtenerDepositoTransferenciaPeriodo(int idPeriodo){
		return depositoTransferenciaDAO.obtenerDepositoTransferenciaPeriodo(idPeriodo);
	}
	
	public List<DepositoTransferencia> autocompletarDonantesDepositoTransferencia(String termino, int idPeriodo){
		return depositoTransferenciaDAO.autocompletarDonantesDepositoTransferencia(termino, idPeriodo);
	}
	
	public int numDescuento(int idDonativo) {
		return depositoTransferenciaDAO.getNumDescuento(idDonativo);
	}
	
	public double saldo(int idDonativo) {
		return depositoTransferenciaDAO.getSaldo(idDonativo);
	}
	
	public DepositoTransferencia verificaExiste(int idDonativo,int idPeriodo) {
		return depositoTransferenciaDAO.verificaExiste(idDonativo, idPeriodo);
	}
	
	public String actualizaDepositoTransferencia( double descuento, double saldo,Date fecha,int idUsuario, int idDepositoTransferencia) {
		return depositoTransferenciaDAO.actualizaDepositoTransferencia(descuento, saldo, fecha, idUsuario, idDepositoTransferencia);
	}
	
	public String eliminaDepositoTransferencia(int idDepositoTransferencia) {
		return depositoTransferenciaDAO.eliminaDepositoTransferencia(idDepositoTransferencia);
	}
}
