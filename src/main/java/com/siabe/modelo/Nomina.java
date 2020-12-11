package com.siabe.modelo;

public class Nomina {
	
	private int idNomina;
    private int idDonativo;
    private int numDescuento;
    private int idQuincenActual;
    private double donativoQuincenal;    
    private double saldo;
    private String nombreCompletoDon;
    private int idQuincenInicio;
    private String quincenaInicio;
    private String numPersonal;
    private double donativoTotal;
    private int numQuincenas;
    
    
    public Nomina() {
    	
    }
    
  	public Nomina(int idNomina, int idDonativo, int numDescuento, int idQuincenActual, double donativoQuincenal,
			double saldo, String nombreCompletoDon, int idQuincenInicio, String quincenaInicio, String numPersonal,
			double donativoTotal, int numQuincenas) {
		super();
		this.idNomina = idNomina;
		this.idDonativo = idDonativo;
		this.numDescuento = numDescuento;
		this.idQuincenActual = idQuincenActual;
		this.donativoQuincenal = donativoQuincenal;
		this.saldo = saldo;
		this.nombreCompletoDon = nombreCompletoDon;
		this.idQuincenInicio = idQuincenInicio;
		this.quincenaInicio = quincenaInicio;
		this.numPersonal = numPersonal;
		this.donativoTotal = donativoTotal;
		this.numQuincenas = numQuincenas;
	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
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

	public double getDonativoQuincenal() {
		return donativoQuincenal;
	}

	public void setDonativoQuincenal(double donativoQuincenal) {
		this.donativoQuincenal = donativoQuincenal;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getIdQuincenaActual() {
		return idQuincenActual;
	}

	public void setIdQuincenaActual(int idQuincenActual) {
		this.idQuincenActual = idQuincenActual;
	}

	public int getIdQuincenActual() {
		return idQuincenActual;
	}

	public void setIdQuincenActual(int idQuincenActual) {
		this.idQuincenActual = idQuincenActual;
	}

	public String getNombreCompletoDon() {
		return nombreCompletoDon;
	}

	public void setNombreCompletoDon(String nombreCompletoDon) {
		this.nombreCompletoDon = nombreCompletoDon;
	}

	public int getIdQuincenInicio() {
		return idQuincenInicio;
	}

	public void setIdQuincenInicio(int idQuincenInicio) {
		this.idQuincenInicio = idQuincenInicio;
	}

	public String getQuincenaInicio() {
		return quincenaInicio;
	}

	public void setQuincenaInicio(String quincenaInicio) {
		this.quincenaInicio = quincenaInicio;
	}

	public String getNumPersonal() {
		return numPersonal;
	}

	public void setNumPersonal(String numPersonal) {
		this.numPersonal = numPersonal;
	}

	public double getDonativoTotal() {
		return donativoTotal;
	}

	public void setDonativoTotal(double donativoTotal) {
		this.donativoTotal = donativoTotal;
	}

	public int getNumQuincenas() {
		return numQuincenas;
	}

	public void setNumQuincenas(int numQuincenas) {
		this.numQuincenas = numQuincenas;
	}
 
	
}
