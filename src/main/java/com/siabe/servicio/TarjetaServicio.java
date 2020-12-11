package com.siabe.servicio;

import com.siabe.dao.TarjetaDAO;

import com.siabe.modelo.Tarjeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class TarjetaServicio {
	
	@Autowired
    private TarjetaDAO tarjetaDAO;

	public Tarjeta regresarTarjetaPeriodo(int idPeriodo) {
		return tarjetaDAO.regresarTarjetaPeriodo(idPeriodo);
	}
	
	public String insertTarjeta(int idDonativo, int numDescuento, double descuento, double saldo, int anio, int idUsuario) {
		return tarjetaDAO.insertaTarjeta(idDonativo, numDescuento, descuento, saldo,anio, idUsuario);
	}
	
	public List<Tarjeta> obtenerTarjeta(){
		return tarjetaDAO.obtenerTarjeta();
	}
	
	public List<Tarjeta> obtenerTarjetaPeriodo(int idPeriodo, int anio, int mes){
		return tarjetaDAO.obtenerTarjetaPeriodo(idPeriodo, anio, mes);
	}
	
	public List<Tarjeta> autocompletarDonantesTarjeta(String termino, int idPeriodo){
		return tarjetaDAO.autocompletarDonantesTarjeta(termino, idPeriodo);
	}
	
	public int numDescuento(int idDonativo) {
		return tarjetaDAO.getNumDescuento(idDonativo);
	}
	
	public double saldo(int idDonativo) {
		return tarjetaDAO.getSaldo(idDonativo);
	}
	
	public Tarjeta verificaExiste(int idDonativo,int idPeriodo) {
		return tarjetaDAO.verificaExiste(idDonativo, idPeriodo);
	}
	
	public String actualizaTarjeta( double descuento, double saldo,int idUsuario, int idTarjeta) {
		return tarjetaDAO.actualizaTarjeta(descuento, saldo, idUsuario, idTarjeta);
	}
	
	public String eliminaTarjeta(  int idTarjeta) {
		return tarjetaDAO.eliminaTarjeta(idTarjeta);
	}
}
