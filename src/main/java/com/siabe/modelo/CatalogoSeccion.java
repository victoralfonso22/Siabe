package com.siabe.modelo;

public class CatalogoSeccion {

	private int idCatalogoSeccion;
	private String seccion;
	private String etiqueta;
	private int prioridad;
	
	public CatalogoSeccion() {
		
	}
	
	public CatalogoSeccion(int idCatalogoSeccion, String seccion, String etiqueta, int prioridad) {
		this.idCatalogoSeccion = idCatalogoSeccion;
		this.seccion = seccion;
		this.etiqueta = etiqueta;
		this.prioridad = prioridad;
	}

	public int getIdCatalogoSeccion() {
		return idCatalogoSeccion;
	}

	public void setIdCatalogoSeccion(int idCatalogoSeccion) {
		this.idCatalogoSeccion = idCatalogoSeccion;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
}
