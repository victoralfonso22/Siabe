package com.siabe.modelo;

import java.util.Date;

public class Periodo {
	
	private int idPeriodo;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_final;
    //private int idTipoBeca;
    private int estatus;
    //private String tBeca;
    
    public Periodo() {
    	
    }
    
    public Periodo(int idPeriodo, String nombre, Date fecha_inicio, Date fecha_final, int estatus) {
        this.idPeriodo = idPeriodo;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_final = fecha_final;      
        this.estatus = estatus;
    }


	public int getIdPeriodo() {
		return idPeriodo;
	}


	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Date getFecha_inicio() {
		return fecha_inicio;
	}


	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public Date getFecha_final() {
		return fecha_final;
	}


	public void setFecha_final(Date fecha_final) {
		this.fecha_final = fecha_final;
	}
	
	

	public int getEstatus() {
		return estatus;
	}


	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}
 
	
}
