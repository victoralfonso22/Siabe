package com.siabe.modelo;

public class CuentasBancarias {
	
	private int idCuentasBancarias;
	private String nombre;
    private String numeroCuenta;
    private String sucursal;
    private String clabe;
    
    
    public CuentasBancarias() {
    	
    }
 
    public CuentasBancarias(int idCuentasBancarias, String nombre, String numeroCuenta, String sucursal, String clabe) {
        this.idCuentasBancarias = idCuentasBancarias;
        this.setNombre(nombre);
        this.numeroCuenta = numeroCuenta;
        this.sucursal = sucursal;
        this.clabe = clabe;
         }


	public int getIdCuentasBancarias() {
		return idCuentasBancarias;
	}


	public void setIdCuentasBancarias(int idCuentasBancarias) {
		this.idCuentasBancarias = idCuentasBancarias;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
 
}
