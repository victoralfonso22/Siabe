package com.siabe.modelo;

public class MenuSeccion {

	private int idMenu;
	private String modulo;
	private String seccion;
	private int idPermiso;
	private int estatusPermiso;
	private int idSeccion;
	
	public MenuSeccion(int idMenu, String modulo, String seccion, int idPermiso, int estatusPermiso, int idSeccion) {
		this.idMenu = idMenu;
		this.modulo = modulo;
		this.seccion = seccion;
		this.idPermiso = idPermiso;
		this.estatusPermiso = estatusPermiso;
		this.idSeccion = idSeccion;
	}
	
	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	
	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public int getEstatusPermiso() {
		return estatusPermiso;
	}

	public void setEstatusPermiso(int estatusPermiso) {
		this.estatusPermiso = estatusPermiso;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}
	
}
