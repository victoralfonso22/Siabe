package com.siabe.modelo;

public class Usuario {
	
	private Long id;
    private String usuario;
    private String password;
    public String nombre;
 
    public Usuario() {
 
    }
 
    public Usuario(Long userId, String userName, String encrytedPassword, String nombre) {
        this.id = userId;
        this.usuario = userName;
        this.password = encrytedPassword;
        this.setNombre(nombre);
    }
 
    public Long getid() {
        return id;
    }
 
    public void setUserId(Long id) {
        this.id = id;
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

}
