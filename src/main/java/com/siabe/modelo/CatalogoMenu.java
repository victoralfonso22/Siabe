package com.siabe.modelo;

public class CatalogoMenu {
	
	private int idCatalogoMenu;
	private String modulo;
	private String etiqueta;
	private int idCatalogoSeccion;
	private String url;
	private int prioridad;
	
	public CatalogoMenu() {
		
	}
	
	public CatalogoMenu(int idCatalogoMenu, String modulo, String etiqueta, int idCatalogoSeccion, String url, int prioridad) {
		this.idCatalogoMenu = idCatalogoMenu;
		this.modulo = modulo;
		this.etiqueta = etiqueta;
		this.idCatalogoSeccion = idCatalogoSeccion;
		this.url = url;
		this.prioridad = prioridad;
	}


	public int getIdCatalogoMenu() {
		return idCatalogoMenu;
	}


	public void setIdCatalogoMenu(int idCatalogoMenu) {
		this.idCatalogoMenu = idCatalogoMenu;
	}


	public String getModulo() {
		return modulo;
	}


	public void setModulo(String modulo) {
		this.modulo = modulo;
	}


	public String getEtiqueta() {
		return etiqueta;
	}


	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}


	public int getIdCatalogoSeccion() {
		return idCatalogoSeccion;
	}


	public void setIdCatalogoSeccion(int idCatalogoSeccion) {
		this.idCatalogoSeccion = idCatalogoSeccion;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getPrioridad() {
		return prioridad;
	}


	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
}
