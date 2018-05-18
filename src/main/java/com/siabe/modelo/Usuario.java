package com.siabe.modelo;

public class Usuario {
	
	private Long idUsuario;
    private String usuario;
    private String password;
    private int estatus;
    private String nombre;
    private int superU;
    
    public Usuario() {
 
    }
 
    public Usuario(Long userId, String userName, String encrytedPassword, int estatus, String nombre, int superU) {
        this.idUsuario = userId;
        this.usuario = userName;
        this.password = encrytedPassword;
        this.estatus = estatus;
        this.setNombre(nombre);
        this.superU = superU;
    }
 
    public Long getIdUsuario() {
        return idUsuario;
    }
 
    public void setIdUsuario(Long id) {
        this.idUsuario = id;
    }
 
    public String getUsuario() {
        return usuario;
    }
 
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    @Override
    public String toString() {
        return this.usuario + "/" + this.password;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public int getSuperU() {
		return superU;
	}

	public void setSuperU(int superU) {
		this.superU = superU;
	}

}
