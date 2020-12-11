package com.siabe.modelo;

import java.util.Date;

public class DepositoTransferencia {
	
	private int idDepositoTransferencia;
    private int idDonativo;
    private int numDescuento;
    private double donativo;    
    private double saldo;
    private String nombreCompletoDon;
    private double donativoTotal;
    private int numPagos;
    private Date fecha;
    
    
	public DepositoTransferencia(int idDepositoTransferencia, int idDonativo, int numDescuento, double donativo,
			double saldo, String nombreCompletoDon, double donativoTotal, int numPagos,
			Date fecha) {
		super();
		this.idDepositoTransferencia = idDepositoTransferencia;
		this.idDonativo = idDonativo;
		this.numDescuento = numDescuento;
		this.donativo = donativo;
		this.saldo = saldo;
		this.nombreCompletoDon = nombreCompletoDon;
		this.donativoTotal = donativoTotal;
		this.numPagos = numPagos;
		this.fecha = fecha;
	}
	public int getIdDepositoTransferencia() {
		return idDepositoTransferencia;
	}
	public void setIdDepositoTransferencia(int idDepositoTransferencia) {
		this.idDepositoTransferencia = idDepositoTransferencia;
	}
	public int getIdDonativo() {
		return idDonativo;
	}
	public void setIdDonativo(int idDonativo) {
		this.idDonativo = idDonativo;
	}
	public int getNumDescuento() {
		return numDescuento;
	}
	public void setNumDescuento(int numDescuento) {
		this.numDescuento = numDescuento;
	}
	public double getDonativo() {
		return donativo;
	}
	public void setDonativo(double donativo) {
		this.donativo = donativo;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getNombreCompletoDon() {
		return nombreCompletoDon;
	}
	public void setNombreCompletoDon(String nombreCompletoDon) {
		this.nombreCompletoDon = nombreCompletoDon;
	}

	public double getDonativoTotal() {
		return donativoTotal;
	}
	public void setDonativoTotal(double donativoTotal) {
		this.donativoTotal = donativoTotal;
	}
	public int getNumPagos() {
		return numPagos;
	}
	public void setNumPagos(int numPagos) {
		this.numPagos = numPagos;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
    
}
