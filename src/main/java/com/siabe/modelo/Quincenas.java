package com.siabe.modelo;

public class Quincenas {
	
	private int idQuincena;
	private int numeroQuincena;
    private String nombre;
    private int anio;
    
    public Quincenas() {
    	
    }
 
    public Quincenas(int idQuincena, int numeroQuincena,String nombre, int anio) {
        this.idQuincena = idQuincena;
        this.numeroQuincena = numeroQuincena;
        this.nombre = nombre;
        this.anio = anio;
         }


	public int getIdQuincena() {
		return idQuincena;
	}


	public void setIdQuincena(int idQuincena) {
		this.idQuincena = idQuincena;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumeroQuincena() {
		return numeroQuincena;
	}

	public void setNumeroQuincena(int numeroQuincena) {
		this.numeroQuincena = numeroQuincena;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	
 
}
