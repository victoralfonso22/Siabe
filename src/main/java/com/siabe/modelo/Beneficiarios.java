package com.siabe.modelo;

import java.util.Date;

public class Beneficiarios {
	
	private int idBeneficiario;
	private int idTipoBeca;
	private String periodo;
	private String tipoBeca;
    private int idPeriodo;
    private String matricula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int estatus;
    private String motivoEstatus;
    private String tipoBecario;
    private String adscripcion;
    private int idRegion;
    private String region;
    private String carrera;
    private String nivel;
    private String modalidad;
    private String facultad;
    private int idFacultad;
    private int idArea;
    private String area;
    private int idCarrera;
    private String escuelaDeportiva;
    private Date fechaIngEscDep;
    private String nivelEduCursa;
    private String turno;
    private String tipoInstitucion;
    private int grado;
    private String nombreEdu;
    private String calleEdu;
    private String numExtEdu;
    private String numIntEdu;
    private String colEdu;
    private String locEdu;
    private String munEdu;
    private String edoEdu;
    private int cpEdu;
    private String telEdu;
    private int periodoActual;
    private double promedioGeneral;
    private int edad;
    private String genero;
    private String lenguaIndigena;
    private String discapacidad;
    private String estadoCivil;
    private String lugarNacimiento;
    private Date fechaNacimiento;
    private String breveHistoria;
    private int integrantesFamiliares;
    private double ingresosFamiliares;
    private String calleVivFam;
    private String numEVivFam;
    private String numIVivFam;
    private String colVivFam;
    private String locVivFam;
    private String munVivFam;
    private String edoVivFam;
    private int cpVivFam;
    private String enlaceMaps;
    private int mismoVivFam;
    private String calleEst;
    private String numEEst;
    private String numIEst;
    private String colEst;
    private String locEst;
    private String munEst;
    private String edoEst;
    private String cpEst;
    private String nombreTutor;
    private String parentescoTutor;
    private String celular;
    private String telDomicilio;
    private String tipoTelRef;
    private String numTelRef;
    private String parentescoRef;
    private String observacionesRef;
    private String email;
    private String facebook;
    private String facebook2;
    private String facebook3;
    private String ocupacionTutor;
    private int hermanosInscritos;
    private String escuelaHermanosInscritos;
    private int formaPago;
    private String banco;
    private String cuentaDeposito;
    private String tarjetaDeposito;
    private String claveReferenciado;
    private String vigenciaReferenciado;
    private double montoBeca;
    private String finalidadApoyo;
    private String observaciones;
    private int idBenefactor;
    private String estatusBene;
    private String tipoBene;
    private String adscripcionBene;
    private String escuelaDeportivaBene;
    private String nivelEducativoBene;
    private String turnoBene;
    private String tipoInstitucionBene;
    private String edoCivilBene;
    private String tipoTelRefBene;
    private String hermanosInscritosBene;
    private String formaPagoBene;
    private String nombreCompletoBene;
    private String generoBene;
    private int periodoPromedio;
    private int periodoRebasa;
    
    public Beneficiarios() {
    	
    }
        
	public Beneficiarios(int idBeneficiario, int idTipoBeca, String periodo, String tipoBeca,int idPeriodo, String matricula, String nombre, String apellidoPaterno,
			String apellidoMaterno, int estatus, String motivoEstatus, String tipoBecario, String adscripcion,
			int idRegion, String region, String carrera, String nivel, String modalidad, String facultad, int idFacultad,int idArea, String area,
			int idCarrera, String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa,
			String turno, String tipoInstitucion, int grado,String nombreEdu, String calleEdu, String numExtEdu, String numIntEdu,
			String colEdu, String locEdu, String munEdu, String edoEdu, int cpEdu,String telEdu, int periodoActual,
			double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad,
			String estadoCivil, String lugarNacimiento, Date fechaNacimiento, String breveHistoria,
			int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam,
			String colVivFam, String locVivFam, String munVivFam, String edoVivFam, int cpVivFam, String enlaceMaps,int mismoVivFam,
			String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst,
			String cpEst, String nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email,
			String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, int formaPago, String banco, String cuentaDeposito, String tarjetaDeposito,
			String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo,
			String observaciones, int idBenefactor,String estatusBene, String tipoBene, String adscripcionBene, String escuelaDeportivaBene, String nivelEducativoBene, String turnoBene, String tipoInstitucionBene,
			String edoCivilBene, String tipoTelRefBene, String hermanosInscritosBene,String formaPagoBene,String nombreCompletoBene, int periodoPromedio,int periodoRebasa, String generoBene) {
		super();
		this.idBeneficiario = idBeneficiario;
		this.idTipoBeca = idTipoBeca;
		this.periodo = periodo;
		this.tipoBeca = tipoBeca;
		this.idPeriodo = idPeriodo;
		this.matricula = matricula;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.estatus = estatus;
		this.motivoEstatus = motivoEstatus;
		this.tipoBecario = tipoBecario;
		this.adscripcion = adscripcion;
		this.idRegion = idRegion;
		this.region = region;
		this.carrera = carrera;
		this.nivel = nivel; 
		this.modalidad = modalidad;
		this.facultad = facultad; 
		this.idFacultad = idFacultad;
		this.idArea = idArea;
		this.area = area;
		this.idCarrera = idCarrera;
		this.escuelaDeportiva = escuelaDeportiva;
		this.fechaIngEscDep = fechaIngEscDep;
		this.nivelEduCursa = nivelEduCursa;
		this.turno = turno;
		this.tipoInstitucion = tipoInstitucion;
		this.grado = grado;
		this.setNombreEdu(nombreEdu);
		this.calleEdu = calleEdu;
		this.numExtEdu = numExtEdu;
		this.numIntEdu = numIntEdu;
		this.colEdu = colEdu;
		this.locEdu = locEdu;
		this.munEdu = munEdu;
		this.edoEdu = edoEdu;
		this.setCpEdu(cpEdu);
		this.telEdu = telEdu;
		this.periodoActual = periodoActual;
		this.promedioGeneral = promedioGeneral;
		this.edad = edad;
		this.genero = genero;
		this.lenguaIndigena = lenguaIndigena;
		this.discapacidad = discapacidad;
		this.estadoCivil = estadoCivil;
		this.lugarNacimiento = lugarNacimiento;
		this.fechaNacimiento = fechaNacimiento;
		this.breveHistoria = breveHistoria;
		this.integrantesFamiliares = integrantesFamiliares;
		this.ingresosFamiliares = ingresosFamiliares;
		this.calleVivFam = calleVivFam;
		this.numEVivFam = numEVivFam;
		this.numIVivFam = numIVivFam;
		this.colVivFam = colVivFam;
		this.locVivFam = locVivFam;
		this.munVivFam = munVivFam;
		this.edoVivFam = edoVivFam;
		this.cpVivFam = cpVivFam;
		this.enlaceMaps = enlaceMaps;
		this.setMismoVivFam(mismoVivFam);
		this.calleEst = calleEst;
		this.numEEst = numEEst;
		this.numIEst = numIEst;
		this.colEst = colEst;
		this.locEst = locEst;
		this.munEst = munEst;
		this.edoEst = edoEst;
		this.cpEst = cpEst;
		this.nombreTutor = nombreTutor;
		this.parentescoTutor = parentescoTutor;
		this.celular = celular;
		this.telDomicilio = telDomicilio;
		this.tipoTelRef = tipoTelRef;
		this.numTelRef = numTelRef;
		this.parentescoRef = parentescoRef;
		this.observacionesRef = observacionesRef;
		this.email = email;
		this.facebook = facebook;
		this.facebook2 = facebook2;
		this.facebook3 = facebook3;
		this.ocupacionTutor = ocupacionTutor;
		this.hermanosInscritos = hermanosInscritos;
		this.escuelaHermanosInscritos = escuelaHermanosInscritos;
		this.formaPago = formaPago;
		this.banco = banco;
		this.cuentaDeposito = cuentaDeposito;
		this.tarjetaDeposito = tarjetaDeposito;
		this.claveReferenciado = claveReferenciado;
		this.vigenciaReferenciado = vigenciaReferenciado;
		this.montoBeca = montoBeca;
		this.finalidadApoyo = finalidadApoyo;
		this.observaciones = observaciones;
		this.idBenefactor = idBenefactor;
		this.estatusBene = estatusBene;
		this.tipoBene = tipoBene;
		this.adscripcionBene = adscripcionBene;
		this.escuelaDeportivaBene = escuelaDeportivaBene;
		this.nivelEducativoBene = nivelEducativoBene;
		this.turnoBene = turnoBene;
		this.tipoInstitucionBene = tipoInstitucionBene;
		this.edoCivilBene = edoCivilBene;
		this.tipoTelRefBene = tipoTelRefBene;
		this.hermanosInscritosBene = hermanosInscritosBene;
		this.formaPagoBene = formaPagoBene;
		this.nombreCompletoBene = nombreCompletoBene;
		this.periodoPromedio = periodoPromedio;
		this.periodoRebasa = periodoRebasa;
		this.generoBene = generoBene;
	}

	public int getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(int idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public int getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
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

	public String getMotivoEstatus() {
		return motivoEstatus;
	}

	public void setMotivoEstatus(String motivoEstatus) {
		this.motivoEstatus = motivoEstatus;
	}

	public String getTipoBecario() {
		return tipoBecario;
	}

	public void setTipoBecario(String tipoBecario) {
		this.tipoBecario = tipoBecario;
	}

	public String getAdscripcion() {
		return adscripcion;
	}

	public void setAdscripcion(String adscripcion) {
		this.adscripcion = adscripcion;
	}

	public int getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(int idRegion) {
		this.idRegion = idRegion;
	}

	public int getIdCarrera() {
		return idCarrera;
	}

	public void setIdCarrera(int idCarrera) {
		this.idCarrera = idCarrera;
	}

	public String getEscuelaDeportiva() {
		return escuelaDeportiva;
	}

	public void setEscuelaDeportiva(String escuelaDeportiva) {
		this.escuelaDeportiva = escuelaDeportiva;
	}

	public Date getFechaIngEscDep() {
		return fechaIngEscDep;
	}

	public void setFechaIngEscDep(Date fechaIngEscDep) {
		this.fechaIngEscDep = fechaIngEscDep;
	}

	public String getNivelEduCursa() {
		return nivelEduCursa;
	}

	public void setNivelEduCursa(String nivelEduCursa) {
		this.nivelEduCursa = nivelEduCursa;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(String tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}

	public int getGrado() {
		return grado;
	}

	public void setGrado(int grado) {
		this.grado = grado;
	}

	public String getCalleEdu() {
		return calleEdu;
	}

	public void setCalleEdu(String calleEdu) {
		this.calleEdu = calleEdu;
	}

	public String getNumExtEdu() {
		return numExtEdu;
	}

	public void setNumExtEdu(String numExtEdu) {
		this.numExtEdu = numExtEdu;
	}

	public String getNumIntEdu() {
		return numIntEdu;
	}

	public void setNumIntEdu(String numIntEdu) {
		this.numIntEdu = numIntEdu;
	}

	public String getColEdu() {
		return colEdu;
	}

	public void setColEdu(String colEdu) {
		this.colEdu = colEdu;
	}

	public String getLocEdu() {
		return locEdu;
	}

	public void setLocEdu(String locEdu) {
		this.locEdu = locEdu;
	}

	public String getMunEdu() {
		return munEdu;
	}

	public void setMunEdu(String munEdu) {
		this.munEdu = munEdu;
	}

	public String getEdoEdu() {
		return edoEdu;
	}

	public void setEdoEdu(String edoEdu) {
		this.edoEdu = edoEdu;
	}

	public String getTelEdu() {
		return telEdu;
	}

	public void setTelEdu(String telEdu) {
		this.telEdu = telEdu;
	}

	public int getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(int periodoActual) {
		this.periodoActual = periodoActual;
	}

	public double getPromedioGeneral() {
		return promedioGeneral;
	}

	public void setPromedioGeneral(double promedioGeneral) {
		this.promedioGeneral = promedioGeneral;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getLenguaIndigena() {
		return lenguaIndigena;
	}

	public void setLenguaIndigena(String lenguaIndigena) {
		this.lenguaIndigena = lenguaIndigena;
	}

	public String getDiscapacidad() {
		return discapacidad;
	}

	public void setDiscapacidad(String discapacidad) {
		this.discapacidad = discapacidad;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getLugarNacimiento() {
		return lugarNacimiento;
	}

	public void setLugarNacimiento(String lugarNacimiento) {
		this.lugarNacimiento = lugarNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getBreveHistoria() {
		return breveHistoria;
	}

	public void setBreveHistoria(String breveHistoria) {
		this.breveHistoria = breveHistoria;
	}

	public int getIntegrantesFamiliares() {
		return integrantesFamiliares;
	}

	public void setIntegrantesFamiliares(int integrantesFamiliares) {
		this.integrantesFamiliares = integrantesFamiliares;
	}

	public double getIngresosFamiliares() {
		return ingresosFamiliares;
	}

	public void setIngresosFamiliares(double ingresosFamiliares) {
		this.ingresosFamiliares = ingresosFamiliares;
	}

	public String getCalleVivFam() {
		return calleVivFam;
	}

	public void setCalleVivFam(String calleVivFam) {
		this.calleVivFam = calleVivFam;
	}

	public String getNumEVivFam() {
		return numEVivFam;
	}

	public void setNumEVivFam(String numEVivFam) {
		this.numEVivFam = numEVivFam;
	}

	public String getNumIVivFam() {
		return numIVivFam;
	}

	public void setNumIVivFam(String numIVivFam) {
		this.numIVivFam = numIVivFam;
	}

	public String getColVivFam() {
		return colVivFam;
	}

	public void setColVivFam(String colVivFam) {
		this.colVivFam = colVivFam;
	}

	public String getLocVivFam() {
		return locVivFam;
	}

	public void setLocVivFam(String locVivFam) {
		this.locVivFam = locVivFam;
	}

	public String getMunVivFam() {
		return munVivFam;
	}

	public void setMunVivFam(String munVivFam) {
		this.munVivFam = munVivFam;
	}

	public String getEdoVivFam() {
		return edoVivFam;
	}

	public void setEdoVivFam(String edoVivFam) {
		this.edoVivFam = edoVivFam;
	}

	public int getCpVivFam() {
		return cpVivFam;
	}

	public void setCpVivFam(int cpVivFam) {
		this.cpVivFam = cpVivFam;
	}

	public String getEnlaceMaps() {
		return enlaceMaps;
	}

	public void setEnlaceMaps(String enlaceMaps) {
		this.enlaceMaps = enlaceMaps;
	}

	public String getCalleEst() {
		return calleEst;
	}

	public void setCalleEst(String calleEst) {
		this.calleEst = calleEst;
	}

	public String getNumEEst() {
		return numEEst;
	}

	public void setNumEEst(String numEEst) {
		this.numEEst = numEEst;
	}

	public String getNumIEst() {
		return numIEst;
	}

	public void setNumIEst(String numIEst) {
		this.numIEst = numIEst;
	}

	public String getColEst() {
		return colEst;
	}

	public void setColEst(String colEst) {
		this.colEst = colEst;
	}

	public String getLocEst() {
		return locEst;
	}

	public void setLocEst(String locEst) {
		this.locEst = locEst;
	}

	public String getMunEst() {
		return munEst;
	}

	public void setMunEst(String munEst) {
		this.munEst = munEst;
	}

	public String getEdoEst() {
		return edoEst;
	}

	public void setEdoEst(String edoEst) {
		this.edoEst = edoEst;
	}

	public String getCpEst() {
		return cpEst;
	}

	public void setCpEst(String cpEst) {
		this.cpEst = cpEst;
	}

	public String getNombreTutor() {
		return nombreTutor;
	}

	public void setNombreTutor(String nombreTutor) {
		this.nombreTutor = nombreTutor;
	}

	public String getParentescoTutor() {
		return parentescoTutor;
	}

	public void setParentescoTutor(String parentescoTutor) {
		this.parentescoTutor = parentescoTutor;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelDomicilio() {
		return telDomicilio;
	}

	public void setTelDomicilio(String telDomicilio) {
		this.telDomicilio = telDomicilio;
	}

	public String getTipoTelRef() {
		return tipoTelRef;
	}

	public void setTipoTelRef(String tipoTelRef) {
		this.tipoTelRef = tipoTelRef;
	}

	public String getNumTelRef() {
		return numTelRef;
	}

	public void setNumTelRef(String numTelRef) {
		this.numTelRef = numTelRef;
	}

	public String getParentescoRef() {
		return parentescoRef;
	}

	public void setParentescoRef(String parentescoRef) {
		this.parentescoRef = parentescoRef;
	}

	public String getObservacionesRef() {
		return observacionesRef;
	}

	public void setObservacionesRef(String observacionesRef) {
		this.observacionesRef = observacionesRef;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getFacebook2() {
		return facebook2;
	}

	public void setFacebook2(String facebook2) {
		this.facebook2 = facebook2;
	}

	public String getFacebook3() {
		return facebook3;
	}

	public void setFacebook3(String facebook3) {
		this.facebook3 = facebook3;
	}

	public String getOcupacionTutor() {
		return ocupacionTutor;
	}

	public void setOcupacionTutor(String ocupacionTutor) {
		this.ocupacionTutor = ocupacionTutor;
	}

	public int getHermanosInscritos() {
		return hermanosInscritos;
	}

	public void setHermanosInscritos(int hermanosInscritos) {
		this.hermanosInscritos = hermanosInscritos;
	}

	public String getEscuelaHermanosInscritos() {
		return escuelaHermanosInscritos;
	}

	public void setEscuelaHermanosInscritos(String escuelaHermanosInscritos) {
		this.escuelaHermanosInscritos = escuelaHermanosInscritos;
	}

	public int getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(int formaPago) {
		this.formaPago = formaPago;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCuentaDeposito() {
		return cuentaDeposito;
	}

	public void setCuentaDeposito(String cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}

	public String getTarjetaDeposito() {
		return tarjetaDeposito;
	}

	public void setTarjetaDeposito(String tarjetaDeposito) {
		this.tarjetaDeposito = tarjetaDeposito;
	}

	public String getClaveReferenciado() {
		return claveReferenciado;
	}

	public void setClaveReferenciado(String claveReferenciado) {
		this.claveReferenciado = claveReferenciado;
	}

	public String getVigenciaReferenciado() {
		return vigenciaReferenciado;
	}

	public void setVigenciaReferenciado(String vigenciaReferenciado) {
		this.vigenciaReferenciado = vigenciaReferenciado;
	}

	public double getMontoBeca() {
		return montoBeca;
	}

	public void setMontoBeca(double montoBeca) {
		this.montoBeca = montoBeca;
	}

	public String getFinalidadApoyo() {
		return finalidadApoyo;
	}

	public void setFinalidadApoyo(String finalidadApoyo) {
		this.finalidadApoyo = finalidadApoyo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getIdBenefactor() {
		return idBenefactor;
	}

	public void setIdBenefactor(int idBenefactor) {
		this.idBenefactor = idBenefactor;
	}

	public int getMismoVivFam() {
		return mismoVivFam;
	}

	public void setMismoVivFam(int mismoVivFam) {
		this.mismoVivFam = mismoVivFam;
	}

	public int getCpEdu() {
		return cpEdu;
	}

	public void setCpEdu(int cpEdu) {
		this.cpEdu = cpEdu;
	}

	public String getNombreEdu() {
		return nombreEdu;
	}

	public void setNombreEdu(String nombreEdu) {
		this.nombreEdu = nombreEdu;
	}

	public int getIdTipoBeca() {
		return idTipoBeca;
	}

	public void setIdTipoBeca(int idTipoBeca) {
		this.idTipoBeca = idTipoBeca;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public int getIdFacultad() {
		return idFacultad;
	}

	public void setIdFacultad(int idFacultad) {
		this.idFacultad = idFacultad;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTipoBeca() {
		return tipoBeca;
	}

	public void setTipoBeca(String tipoBeca) {
		this.tipoBeca = tipoBeca;
	}

	public String getEstatusBene() {
		return estatusBene;
	}

	public void setEstatusBene(String estatusBene) {
		this.estatusBene = estatusBene;
	}

	public String getTipoBene() {
		return tipoBene;
	}

	public void setTipoBene(String tipoBene) {
		this.tipoBene = tipoBene;
	}

	public String getAdscripcionBene() {
		return adscripcionBene;
	}

	public void setAdscripcionBene(String adscripcionBene) {
		this.adscripcionBene = adscripcionBene;
	}

	public String getEscuelaDeportivaBene() {
		return escuelaDeportivaBene;
	}

	public void setEscuelaDeportivaBene(String escuelaDeportivaBene) {
		this.escuelaDeportivaBene = escuelaDeportivaBene;
	}

	public String getNivelEducativoBene() {
		return nivelEducativoBene;
	}

	public void setNivelEducativoBene(String nivelEducativoBene) {
		this.nivelEducativoBene = nivelEducativoBene;
	}

	public String getTurnoBene() {
		return turnoBene;
	}

	public void setTurnoBene(String turnoBene) {
		this.turnoBene = turnoBene;
	}

	public String getTipoInstitucionBene() {
		return tipoInstitucionBene;
	}

	public void setTipoInstitucionBene(String tipoInstitucionBene) {
		this.tipoInstitucionBene = tipoInstitucionBene;
	}

	public String getEdoCivilBene() {
		return edoCivilBene;
	}

	public void setEdoCivilBene(String edoCivilBene) {
		this.edoCivilBene = edoCivilBene;
	}

	public String getTipoTelRefBene() {
		return tipoTelRefBene;
	}

	public void setTipoTelRefBene(String tipoTelRefBene) {
		this.tipoTelRefBene = tipoTelRefBene;
	}

	public String getHermanosInscritosBene() {
		return hermanosInscritosBene;
	}

	public void setHermanosInscritosBene(String hermanosInscritosBene) {
		this.hermanosInscritosBene = hermanosInscritosBene;
	}

	public String getFormaPagoBene() {
		return formaPagoBene;
	}

	public void setFormaPagoBene(String formaPagoBene) {
		this.formaPagoBene = formaPagoBene;
	}

	public String getNombreCompletoBene() {
		return nombreCompletoBene;
	}

	public void setNombreCompletoBene(String nombreCompletoBene) {
		this.nombreCompletoBene = nombreCompletoBene;
	}

	public int getPeriodoPromedio() {
		return periodoPromedio;
	}

	public void setPeriodoPromedio(int periodoPromedio) {
		this.periodoPromedio = periodoPromedio;
	}

	public int getPeriodoRebasa() {
		return periodoRebasa;
	}

	public void setPeriodoRebasa(int periodoRebasa) {
		this.periodoRebasa = periodoRebasa;
	}

	

	public String getGeneroBene() {
		return generoBene;
	}

	public void setGeneroBene(String generoBene) {
		this.generoBene = generoBene;
	}

	
 
}
