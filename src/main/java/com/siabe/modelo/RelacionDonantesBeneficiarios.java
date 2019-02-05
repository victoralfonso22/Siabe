package com.siabe.modelo;

public class RelacionDonantesBeneficiarios {
	
	private int idRelacionDB;
    private int idDonante;
    private int idBeneficiario;
    private String nombreCompleto;
    private String tipoBeca;
    private String saldo;
    private String donativoAsignado;
    private boolean clase;
    
    public RelacionDonantesBeneficiarios() {
    	
    };
    
    public RelacionDonantesBeneficiarios(int idRelacionDB, int idDonante, int idBeneficiario, String nombreCompleto, String tipoBeca,
			String saldo, String donativoAsignado, boolean clase) {
		super();
		this.idRelacionDB = idRelacionDB;
		this.idDonante = idDonante;
		this.idBeneficiario = idBeneficiario;
		this.nombreCompleto = nombreCompleto;
		this.tipoBeca = tipoBeca;
		this.saldo = saldo;
		this.donativoAsignado = donativoAsignado;
		this.clase = clase;
	}


	public int getIdRelacionDB() {
		return idRelacionDB;
	}


	public void setIdRelacionDB(int idRelacionDB) {
		this.idRelacionDB = idRelacionDB;
	}


	public int getIdDonante() {
		return idDonante;
	}


	public void setIdDonante(int idDonante) {
		this.idDonante = idDonante;
	}


	public int getIdBeneficiario() {
		return idBeneficiario;
	}


	public void setIdBeneficiario(int idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}


	public String getNombreCompleto() {
		return nombreCompleto;
	}


	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}


	public String getSaldo() {
		return saldo;
	}


	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}


	public String getDonativoAsignado() {
		return donativoAsignado;
	}


	public void setDonativoAsignado(String donativoAsignado) {
		this.donativoAsignado = donativoAsignado;
	}


	public boolean getClase() {
		return clase;
	}


	public void setClase(boolean clase) {
		this.clase = clase;
	}

	public String getTipoBeca() {
		return tipoBeca;
	}

	public void setTipoBeca(String tipoBeca) {
		this.tipoBeca = tipoBeca;
	}


 
}
