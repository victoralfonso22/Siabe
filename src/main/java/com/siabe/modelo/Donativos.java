package com.siabe.modelo;

public class Donativos {
	
	private int idDonativo;
	
	private int idDonativoTipo;
	
	private int idPeriodo;
	private String periodo;

    private String razonSocial;
    private String titulo;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    private int estatus;
    private String estatusDon;    
    private String motivoEstatus;
    
    private int adscripcion;
    private String adscripcionDon;    
    
    private int tipoDonante;
    private String tipoDon;
    
    private int idRegion;
    private String region;
    
    private int sector;
    private String sectorDon;
    
    private String descripcionDonativo;
    
    private int idCampania;
    private String campania;
    
    private int medioAutorizacionDonativo;
    private String medioAutorizacionDon;    
    private String observacionMedioAutorizacion;
    
    private int medioCobro;
    private String medioCobroDon;
    
    private String numPersonal;
    private String dependenciaAdscripcion;
    
    private double donativoTotal;
    private double donativoQuincenal;
    
    private int numQuincenas;
    private int idQuincenaInicio;
    private String quincenaInicio;
    private String anioQuincena;
    
    private int idCuentaBancaria;
    private String cuentaBancaria;
    
    private String referencia;
    
    private int numPagos;
    private double importeNumPagos;
    
    
    private String banco;
    private String nombreTarjetahabiente;
    private String red;
    private String tipoTarjeta;
    private String numTarjeta;
    private int mesVencimiento;
    private int anioVencimiento;
    
    private String tipoDonativo;
    private int mesInicioAportacion;
    
    private String email;
    private String celular;
    private String telefono1;
    private String telefono2;
    
    
    private String calle;
    private String numE;
    private String numI;
    private String col;
    private String loc;
    private String mun;
    private String edo;
    private String cp;
    
    private String razonSocialFiscal;
    private String calleFiscal;
    private String numEFiscal;
    private String numIFiscal;
    private String colFiscal;
    private String locFiscal;
    private String munFiscal;
    private String edoFiscal;
    private String cpFiscal;
    
    private String rfc;
    
    private String observaciones;
    
    private int idBeneficiario;
    private String beneficiario;
    
    private String nombreCompletoDon;
    

    public Donativos() {
    	
    }



	public Donativos(int idDonativo, int idDonativoTipo, int idPeriodo, String periodo, String razonSocial, String titulo,
			String nombre, String apellidoPaterno, String apellidoMaterno, int estatus, String estatusDon,
			String motivoEstatus, int adscripcion, String adscripcionDon, int tipoDonante, String tipoDon, int idRegion, String region,
			int sector, String sectorDon, String descripcionDonativo, int idCampania, String campania,
			int medioAutorizacionDonativo, String medioAutorizacionDon, String observacionMedioAutorizacion,
			int medioCobro, String medioCobroDon, String numPersonal, String dependenciaAdscripcion, double donativoTotal,
			double donativoQuincenal, int numQuincenas, int idQuincenaInicio, String quincenaInicio, String anioQuincena,
			int idCuentaBancaria, String cuentaBancaria, String referencia, int numPagos, double importeNumPagos,
			String banco, String nombreTarjetahabiente, String red, String tipoTarjeta, String numTarjeta,
			int mesVencimiento, int anioVencimiento, String tipoDonativo, int mesInicioAportacion, String email, String celular,
			String telefono1, String telefono2, String calle, String numE, String numI, String col, String loc,
			String mun, String edo, String cp, String razonSocialFiscal,String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal,
			String locFiscal, String munFiscal, String edoFiscal, String cpFiscal, String rfc, String observaciones,
			int idBeneficiario, String beneficiario, String nombreCompletoDon) {
		super();
		this.idDonativo = idDonativo;
		this.idDonativoTipo = idDonativoTipo;
		this.idPeriodo = idPeriodo;
		this.periodo = periodo;
		this.titulo = titulo;
		this.razonSocial = razonSocial;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.estatus = estatus;
		this.estatusDon = estatusDon;
		this.motivoEstatus = motivoEstatus;
		this.adscripcion = adscripcion;
		this.adscripcionDon = adscripcionDon;
		this.tipoDonante = tipoDonante;
		this.tipoDon = tipoDon;
		this.idRegion = idRegion;
		this.region = region;
		this.sector = sector;
		this.sectorDon = sectorDon;
		this.descripcionDonativo = descripcionDonativo;
		this.idCampania = idCampania;
		this.campania = campania;
		this.medioAutorizacionDonativo = medioAutorizacionDonativo;
		this.medioAutorizacionDon = medioAutorizacionDon;
		this.observacionMedioAutorizacion = observacionMedioAutorizacion;
		this.medioCobro = medioCobro;
		this.medioCobroDon = medioCobroDon;
		this.numPersonal = numPersonal;
		this.dependenciaAdscripcion = dependenciaAdscripcion;
		this.donativoTotal = donativoTotal;
		this.donativoQuincenal = donativoQuincenal;
		this.numQuincenas = numQuincenas;
		this.idQuincenaInicio = idQuincenaInicio;
		this.quincenaInicio = quincenaInicio;
		this.anioQuincena = anioQuincena;
		this.idCuentaBancaria = idCuentaBancaria;
		this.cuentaBancaria = cuentaBancaria;
		this.referencia = referencia;
		this.numPagos = numPagos;
		this.importeNumPagos = importeNumPagos;
		this.banco = banco;
		this.nombreTarjetahabiente = nombreTarjetahabiente;
		this.red = red;
		this.tipoTarjeta = tipoTarjeta;
		this.numTarjeta = numTarjeta;
		this.mesVencimiento = mesVencimiento;
		this.anioVencimiento = anioVencimiento;
		this.tipoDonativo = tipoDonativo;
		this.mesInicioAportacion = mesInicioAportacion;
		this.email = email;
		this.celular = celular;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.calle = calle;
		this.numE = numE;
		this.numI = numI;
		this.col = col;
		this.loc = loc;
		this.mun = mun;
		this.edo = edo;
		this.cp = cp;
		this.razonSocialFiscal = razonSocialFiscal;
		this.calleFiscal = calleFiscal;
		this.numEFiscal = numEFiscal;
		this.numIFiscal = numIFiscal;
		this.colFiscal = colFiscal;
		this.locFiscal = locFiscal;
		this.munFiscal = munFiscal;
		this.edoFiscal = edoFiscal;
		this.cpFiscal = cpFiscal;
		this.rfc = rfc;
		this.observaciones = observaciones;
		this.idBeneficiario = idBeneficiario;
		this.beneficiario = beneficiario;
		this.nombreCompletoDon = nombreCompletoDon;
	}



	public int getIdDonativo() {
		return idDonativo;
	}



	public void setIdDonativo(int idDonativo) {
		this.idDonativo = idDonativo;
	}



	public int getIdPeriodo() {
		return idPeriodo;
	}



	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}



	public String getPeriodo() {
		return periodo;
	}



	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}



	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellidoPaterno() {
		return apellidoPaterno;
	}



	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}



	public String getApellidoMaterno() {
		return apellidoMaterno;
	}



	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}



	public int getEstatus() {
		return estatus;
	}



	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}



	public String getEstatusDon() {
		return estatusDon;
	}



	public void setEstatusDon(String estatusDon) {
		this.estatusDon = estatusDon;
	}



	public String getMotivoEstatus() {
		return motivoEstatus;
	}



	public void setMotivoEstatus(String motivoEstatus) {
		this.motivoEstatus = motivoEstatus;
	}



	public int getAdscripcion() {
		return adscripcion;
	}



	public void setAdscripcion(int adscripcion) {
		this.adscripcion = adscripcion;
	}



	public int getTipoDonante() {
		return tipoDonante;
	}



	public void setTipoDonante(int tipoDonante) {
		this.tipoDonante = tipoDonante;
	}



	public String getTipoDon() {
		return tipoDon;
	}



	public void setTipoDon(String tipoDon) {
		this.tipoDon = tipoDon;
	}



	public int getIdRegion() {
		return idRegion;
	}



	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}



	public String getRegion() {
		return region;
	}



	public void setRegion(String region) {
		this.region = region;
	}



	public int getSector() {
		return sector;
	}



	public void setSector(int sector) {
		this.sector = sector;
	}



	public String getsectorDon() {
		return sectorDon;
	}



	public void setsectorDon(String sectorDon) {
		this.sectorDon = sectorDon;
	}



	public String getDescripcionDonativo() {
		return descripcionDonativo;
	}



	public void setDescripcionDonativo(String descripcionDonativo) {
		this.descripcionDonativo = descripcionDonativo;
	}



	public int getIdCampania() {
		return idCampania;
	}



	public void setIdCampania(int idCampania) {
		this.idCampania = idCampania;
	}



	public String getCampania() {
		return campania;
	}



	public void setCampania(String campania) {
		this.campania = campania;
	}



	public int getMedioAutorizacionDonativo() {
		return medioAutorizacionDonativo;
	}



	public void setMedioAutorizacionDonativo(int medioAutorizacionDonativo) {
		this.medioAutorizacionDonativo = medioAutorizacionDonativo;
	}



	public String getMedioAutorizacionDon() {
		return medioAutorizacionDon;
	}



	public void setMedioAutorizacionDon(String medioAutorizacionDon) {
		this.medioAutorizacionDon = medioAutorizacionDon;
	}



	public String getObservacionMedioAutorizacion() {
		return observacionMedioAutorizacion;
	}



	public void setObservacionMedioAutorizacion(String observacionMedioAutorizacion) {
		this.observacionMedioAutorizacion = observacionMedioAutorizacion;
	}



	public int getMedioCobro() {
		return medioCobro;
	}



	public void setMedioCobro(int medioCobro) {
		this.medioCobro = medioCobro;
	}



	public String getMedioCobroDon() {
		return medioCobroDon;
	}



	public void setMedioCobroDon(String medioCobroDon) {
		this.medioCobroDon = medioCobroDon;
	}



	public String getNumPersonal() {
		return numPersonal;
	}



	public void setNumPersonal(String numPersonal) {
		this.numPersonal = numPersonal;
	}



	public String getDependenciaAdscripcion() {
		return dependenciaAdscripcion;
	}



	public void setDependenciaAdscripcion(String dependenciaAdscripcion) {
		this.dependenciaAdscripcion = dependenciaAdscripcion;
	}



	public double getDonativoTotal() {
		return donativoTotal;
	}



	public void setDonativoTotal(double donativoTotal) {
		this.donativoTotal = donativoTotal;
	}



	public double getDonativoQuincenal() {
		return donativoQuincenal;
	}



	public void setDonativoQuincenal(double donativoQuincenal) {
		this.donativoQuincenal = donativoQuincenal;
	}



	public int getNumQuincenas() {
		return numQuincenas;
	}



	public void setNumQuincenas(int numQuincenas) {
		this.numQuincenas = numQuincenas;
	}



	public int getIdQuincenaInicio() {
		return idQuincenaInicio;
	}



	public void setIdQuincenaInicio(int idQuincenaInicio) {
		this.idQuincenaInicio = idQuincenaInicio;
	}



	public String getQuincenaInicio() {
		return quincenaInicio;
	}



	public void setQuincenaInicio(String quincenaInicio) {
		this.quincenaInicio = quincenaInicio;
	}



	public String getAnioQuincena() {
		return anioQuincena;
	}



	public void setAnioQuincena(String anioQuincena) {
		this.anioQuincena = anioQuincena;
	}



	public int getIdCuentaBancaria() {
		return idCuentaBancaria;
	}



	public void setIdCuentaBancaria(int idCuentaBancaria) {
		this.idCuentaBancaria = idCuentaBancaria;
	}



	public String getCuentaBancaria() {
		return cuentaBancaria;
	}



	public void setCuentaBancaria(String cuentaBancaria) {
		this.cuentaBancaria = cuentaBancaria;
	}



	public String getReferencia() {
		return referencia;
	}



	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}



	public int getNumPagos() {
		return numPagos;
	}



	public void setNumPagos(int numPagos) {
		this.numPagos = numPagos;
	}



	public double getImporteNumPagos() {
		return importeNumPagos;
	}



	public void setImporteNumPagos(double importeNumPagos) {
		this.importeNumPagos = importeNumPagos;
	}



	public String getBanco() {
		return banco;
	}



	public void setBanco(String banco) {
		this.banco = banco;
	}



	public String getNombreTarjetahabiente() {
		return nombreTarjetahabiente;
	}



	public void setNombreTarjetahabiente(String nombreTarjetahabiente) {
		this.nombreTarjetahabiente = nombreTarjetahabiente;
	}



	public String getRed() {
		return red;
	}



	public void setRed(String red) {
		this.red = red;
	}



	public String getTipoTarjeta() {
		return tipoTarjeta;
	}



	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}



	public String getNumTarjeta() {
		return numTarjeta;
	}



	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}



	public int getMesVencimiento() {
		return mesVencimiento;
	}



	public void setMesVencimiento(int mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}



	public int getAnioVencimiento() {
		return anioVencimiento;
	}



	public void setAnioVencimiento(int anioVencimiento) {
		this.anioVencimiento = anioVencimiento;
	}



	public int getMesInicioAportacion() {
		return mesInicioAportacion;
	}



	public void setMesInicioAportacion(int mesInicioAportacion) {
		this.mesInicioAportacion = mesInicioAportacion;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getCelular() {
		return celular;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public String getTelefono1() {
		return telefono1;
	}



	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}



	public String getTelefono2() {
		return telefono2;
	}



	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}



	public String getNumE() {
		return numE;
	}



	public void setNumE(String numE) {
		this.numE = numE;
	}



	public String getNumI() {
		return numI;
	}



	public void setNumI(String numI) {
		this.numI = numI;
	}



	public String getCol() {
		return col;
	}



	public void setCol(String col) {
		this.col = col;
	}



	public String getLoc() {
		return loc;
	}



	public void setLoc(String loc) {
		this.loc = loc;
	}



	public String getMun() {
		return mun;
	}



	public void setMun(String mun) {
		this.mun = mun;
	}



	public String getEdo() {
		return edo;
	}



	public void setEdo(String edo) {
		this.edo = edo;
	}



	public String getCp() {
		return cp;
	}



	public void setCp(String cp) {
		this.cp = cp;
	}



	public String getCalleFiscal() {
		return calleFiscal;
	}



	public void setCalleFiscal(String calleFiscal) {
		this.calleFiscal = calleFiscal;
	}



	public String getNumEFiscal() {
		return numEFiscal;
	}



	public void setNumEFiscal(String numEFiscal) {
		this.numEFiscal = numEFiscal;
	}



	public String getNumIFiscal() {
		return numIFiscal;
	}



	public void setNumIFiscal(String numIFiscal) {
		this.numIFiscal = numIFiscal;
	}



	public String getColFiscal() {
		return colFiscal;
	}



	public void setColFiscal(String colFiscal) {
		this.colFiscal = colFiscal;
	}



	public String getLocFiscal() {
		return locFiscal;
	}



	public void setLocFiscal(String locFiscal) {
		this.locFiscal = locFiscal;
	}



	public String getMunFiscal() {
		return munFiscal;
	}



	public void setMunFiscal(String munFiscal) {
		this.munFiscal = munFiscal;
	}



	public String getEdoFiscal() {
		return edoFiscal;
	}



	public void setEdoFiscal(String edoFiscal) {
		this.edoFiscal = edoFiscal;
	}



	public String getCpFiscal() {
		return cpFiscal;
	}



	public void setCpFiscal(String cpFiscal) {
		this.cpFiscal = cpFiscal;
	}



	public String getRfc() {
		return rfc;
	}



	public void setRfc(String rfc) {
		this.rfc = rfc;
	}



	public String getObservaciones() {
		return observaciones;
	}



	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}



	public int getIdBeneficiario() {
		return idBeneficiario;
	}



	public void setIdBeneficiario(int idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}



	public String getBeneficiario() {
		return beneficiario;
	}



	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}



	public int getIdDonativoTipo() {
		return idDonativoTipo;
	}



	public void setIdDonativoTipo(int idDonativoTipo) {
		this.idDonativoTipo = idDonativoTipo;
	}



	public String getTipoDonativo() {
		return tipoDonativo;
	}



	public void setTipoDonativo(String tipoDonativo) {
		this.tipoDonativo = tipoDonativo;
	}



	public String getNombreCompletoDon() {
		return nombreCompletoDon;
	}



	public void setNombreCompletoDon(String nombreCompletoDon) {
		this.nombreCompletoDon = nombreCompletoDon;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getAdscripcionDon() {
		return adscripcionDon;
	}



	public void setAdscripcionDon(String adscripcionDon) {
		this.adscripcionDon = adscripcionDon;
	}



	public String getRazonSocialFiscal() {
		return razonSocialFiscal;
	}



	public void setRazonSocialFiscal(String razonSocialFiscal) {
		this.razonSocialFiscal = razonSocialFiscal;
	}
    
    
    
		 
}
