package com.siabe.modelo;

public class Facultades {
	
	private int idFacultad;
    private String nombre;
    private int idArea;
    
    public Facultades() {
    	
    }
 
    public Facultades(int idFacultad, String nombre, int idArea) {
        this.idFacultad = idFacultad;
        this.nombre = nombre;
        this.idArea = idArea;
    }

	public int getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(int idFacultad) {
		this.idFacultad = idFacultad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}


}
