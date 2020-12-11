package com.siabe.modelo;



public class Tarjeta {
	
	private int idTarjeta;
    private int idDonativo;
    private int numDescuento;
    private double donativo;    
    private double saldo;
    private int anio;
    private String nombreCompletoDon;
    private double donativoTotal;
    private int numPagos;

    
    
	public Tarjeta(int idTarjeta, int idDonativo, int numDescuento, double donativo,
			double saldo, int anio, String nombreCompletoDon, double donativoTotal, int numPagos
			) {
		super();
		this.idTarjeta = idTarjeta;
		this.idDonativo = idDonativo;
		this.numDescuento = numDescuento;
		this.donativo = donativo;
		this.saldo = saldo;
		this.anio = anio;
		this.nombreCompletoDon = nombreCompletoDon;
		this.donativoTotal = donativoTotal;
		this.numPagos = numPagos;

	}
	public int getIdTarjeta() {
		return idTarjeta;
	}
	public void setIdDepositoTransferencia(int idTarjeta) {
		this.idTarjeta = idTarjeta;
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
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
   
    
}
