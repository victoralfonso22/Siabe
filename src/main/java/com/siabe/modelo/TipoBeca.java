package com.siabe.modelo;

public class TipoBeca {
	
	private int idTipoBeca;
    private String nombre;
    
    
    public TipoBeca() {
    	
    }
 
    public TipoBeca(int idTipoBeca, String nombre) {
        this.idTipoBeca = idTipoBeca;
        this.nombre = nombre;
         }


	public int getIdTipoBeca() {
		return idTipoBeca;
	}


	public void setIdTipoBeca(int idTipoBeca) {
		this.idTipoBeca = idTipoBeca;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

 
}
