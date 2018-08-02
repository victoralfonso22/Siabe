package com.siabe.modelo;

public class TipoDonativo {
	
	private int idTipoDonativo;
    private String nombre;
    
    
    public TipoDonativo() {
    	
    }
 
    public TipoDonativo(int idTipoDonativo, String nombre) {
        this.setIdTipoDonativo(idTipoDonativo);
        this.nombre = nombre;
         }




	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdTipoDonativo() {
		return idTipoDonativo;
	}

	public void setIdTipoDonativo(int idTipoDonativo) {
		this.idTipoDonativo = idTipoDonativo;
	}

 
}
