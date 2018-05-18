package com.siabe.modelo;




public class PermisosMenu {
	
	private int idSeccion;
	private String seccion;
	private String modulo;
	private String url;
	private int estatus;
	
	public PermisosMenu() {
		
	}
	
	public PermisosMenu(int idSeccion, String seccion, String modulo, String url, int estatus) {
		this.setIdSeccion(idSeccion);
		this.seccion = seccion;
		this.modulo = modulo;
        this.url = url;        
        this.estatus = estatus;
        
    }

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	
}
