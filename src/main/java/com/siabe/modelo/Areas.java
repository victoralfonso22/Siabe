package com.siabe.modelo;

public class Areas {
	
	private int idArea;
    private String nombre;
    
    
    public Areas() {
    	
    }
 
    public Areas(int idArea, String nombre) {
        this.idArea = idArea;
        this.nombre = nombre;
         }


	public int getIdArea() {
		return idArea;
	}


	public void setIdArea(int idTipoBeca) {
		this.idArea = idTipoBeca;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

 
}
