package com.siabe.modelo;

public class Campana {
	
	private int idCampana;
    private String nombre;
    private int idRegion;
    private int estatus;
    private String region;
    
    public Campana() {
    	
    }
 
    public Campana(int idCampana, String nombre, int idRegion, int estatus, String region) {
        this.idCampana = idCampana;
        this.nombre = nombre;
        this.idRegion = idRegion;
        this.setEstatus(estatus);
        this.setRegion(region);
    }

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdCampana() {
		return idCampana;
	}

	public void setIdCampana(int idCampana) {
		this.idCampana = idCampana;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	
 
}
