package com.siabe.modelo;




public class Permisos {

	private int idPermiso;
	private int idUsuario;
	private int idCatalogoMenu;
	private int estatus;
	
	public Permisos() {
		
	}
	
	public Permisos(int idPermiso, int idUsuario, int idCatalogoMenu, int estatus) {
		this.idPermiso = idPermiso;
		this.idUsuario = idUsuario;
        this.idCatalogoMenu = idCatalogoMenu;        
        this.estatus = estatus;
        
    }

	public int getIdPermiso() {
		return idPermiso;
	}

	public void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdCatalogoMenu() {
		return idCatalogoMenu;
	}

	public void setIdCatalogoMenu(int idCatalogoMenu) {
		this.idCatalogoMenu = idCatalogoMenu;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

}
