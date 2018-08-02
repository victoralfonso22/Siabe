package com.siabe.modelo;


public class Regiones {
	
	private int idRegion;
    private String nombre;
    private String abreviatura;    
    private int idPeriodo;
    private String periodo;
    private int estatus;    
    
    public Regiones() {
    	
    }
    
    public Regiones(int idRegion, String nombre, String abreviatura, int idPeriodo, String periodo, int estatus) {
        this.idRegion = idRegion;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.idPeriodo = idPeriodo;        
        this.periodo = periodo;
        this.estatus = estatus;        
    }

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

 
}
