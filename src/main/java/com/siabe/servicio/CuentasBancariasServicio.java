package com.siabe.servicio;

import com.siabe.dao.CuentasBancariasDAO;
import com.siabe.modelo.CuentasBancarias;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class CuentasBancariasServicio {
	
	@Autowired
    private CuentasBancariasDAO cuentasBancariasDAO;

	public CuentasBancarias regresaCuentaBancaria(int idCuentaBancaria) {
		return cuentasBancariasDAO.regresarCuentaBancaria(idCuentaBancaria);
	}
	
	public String insertCuentaBancaria(String nombre ,String numeroTarjeta, String sucursal,  String clabe) {
		return cuentasBancariasDAO.insertaCuentaBancaria(nombre,numeroTarjeta, sucursal, clabe);
	}
	
	public List<CuentasBancarias> todosCuentasBancarias(){
		return cuentasBancariasDAO.obtenerCuentasBancarias();
	}
	
	public String actualizarCuentasBancarias(int idCuentaBancaria, String nombre ,String numeroTarjeta, String sucursal, String clabe) {
		return cuentasBancariasDAO.actualizaCuentaBancaria(idCuentaBancaria, nombre,numeroTarjeta, sucursal, clabe);
	}
	
	public List<CuentasBancarias> autocompleteCuentasBancarias(String termino) {
		return cuentasBancariasDAO.autocompletarCuentaBancaria(termino);
	}
}
