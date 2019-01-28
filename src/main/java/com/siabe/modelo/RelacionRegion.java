package com.siabe.modelo;

public class RelacionRegion {
	
	private int idRelacion;
    private int idRegionPadre;
    private int idRegionHijo;
    private int idPeriodo;
    private int estatus;
    
    
    public RelacionRegion() {
    	
    }
 
    public RelacionRegion(int idRelacion, int idRegionPadre, int idRegionHijo, int idPeriodo, int estatus) {
        this.idRelacion = idRelacion;
        this.idRegionPadre = idRegionPadre;
        this.idRegionHijo = idRegionHijo;
        this.setIdPeriodo(idPeriodo);
        this.estatus = estatus;
    }

	public int getIdRelacion() {
		return idRelacion;
	}

	public void setIdRelacion(int idRelacion) {
		this.idRelacion = idRelacion;
	}

	public int getIdRegionPadre() {
		return idRegionPadre;
	}

	public void setIdRegionPadre(int idRegionPadre) {
		this.idRegionPadre = idRegionPadre;
	}

	public int getIdRegionHijo() {
		return idRegionHijo;
	}

	public void setIdRegionHijo(int idRegionHijo) {
		this.idRegionHijo = idRegionHijo;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	
}
