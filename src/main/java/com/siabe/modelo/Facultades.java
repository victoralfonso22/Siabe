package com.siabe.modelo;

public class Facultades {
	
	private int idFacultad;
    private String nombre;
  
    
    public Facultades() {
    	
    }
 
    public Facultades(int idFacultad, String nombre) {
        this.idFacultad = idFacultad;
        this.nombre = nombre;
        
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

}
