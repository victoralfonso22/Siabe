package com.siabe.servicio;

import com.siabe.dao.BeneficiariosDAO;
import com.siabe.modelo.Beneficiarios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class BeneficiariosServicio {
	
	@Autowired
    private BeneficiariosDAO beneficiariosDAO;

	public Beneficiarios beneficiario(int idBeneficiario) {
		return beneficiariosDAO.beneficiario(idBeneficiario);
	}
	
	public List<Beneficiarios> beneficiariosPeriodo(int idPeriodo) {
		return beneficiariosDAO.beneficiariosPeriodo(idPeriodo);
	}
	
	public List<Beneficiarios> beneficiariosActivosPeriodo(int idPeriodo) {
		return beneficiariosDAO.beneficiariosActivosPeriodo(idPeriodo);
	}
	
	public String insertaBeneficiarioBeca(int idPeriodo, String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, String idBenefactor, int idUsuario) {
		
		return beneficiariosDAO.insertaBeneficiarioBecas(idPeriodo, matricula, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, idCarrera, periodoActual, 
				promedioGeneral, edad, genero, lenguaIndigena, discapacidad, estadoCivil, lugarNacimiento, fechaNacimiento, breveHistoria, integrantesFamiliares, ingresosFamiliares, calleVivFam, numEVivFam, 
				numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, mismoVivFam, calleEst, numEEst, numIEst, colEst, locEst, munEst, edoEst, cpEst, celular, telDomicilio, tipoTelRef, 
				numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, formaPago, banco, cuentaDeposito, tarjetaDeposito, claveReferenciado, vigenciaReferenciado, montoBeca, 
				finalidadApoyo, observaciones, idBenefactor, idUsuario);
	}
	
	public String insertaBeneficiarioDeportiva(int idPeriodo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu,String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idUsuario) {
		
		return beneficiariosDAO.insertaBeneficiarioDeportivas(idPeriodo, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, escuelaDeportiva, fechaIngEscDep, 
				nivelEduCursa, turno, tipoInstitucion, grado,nombreEdu, calleEdu, numExtEdu, numIntEdu, colEdu, locEdu, munEdu, edoEdu, cpEdu, telEdu, promedioGeneral, edad, genero, lugarNacimiento, fechaNacimiento, 
				breveHistoria, integrantesFamiliares, ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, nombreTutor, parentescoTutor, 
				celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, ocupacionTutor, hermanosInscritos, escuelaHermanosInscritos, montoBeca, 
				observaciones, idUsuario);
	}
	
	public List<Beneficiarios> todosBeneficiarios(){
		return beneficiariosDAO.obtenerBeneficiarios();
	}
	
	public String actualizaDatosBecas(int idPeriodo, String matricula, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			int idCarrera, int periodoActual, double promedioGeneral, int edad, String genero, String lenguaIndigena, String discapacidad, String estadoCivil, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, int mismoVivFam, String calleEst, String numEEst, String numIEst, String colEst, String locEst, String munEst, String edoEst, String cpEst, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, int formaPago, String banco, String cuentaDeposito, 
			String tarjetaDeposito, String claveReferenciado, String vigenciaReferenciado, double montoBeca, String finalidadApoyo, String observaciones, int idBeneficiario, int idUsuario) {
		
		return beneficiariosDAO.actualizaDatosBecas(idPeriodo, matricula, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, idCarrera,
				periodoActual, promedioGeneral, edad, genero, lenguaIndigena, discapacidad, estadoCivil, lugarNacimiento, fechaNacimiento, breveHistoria, integrantesFamiliares, ingresosFamiliares, 
				calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, mismoVivFam, calleEst, numEEst, numIEst, colEst, locEst, munEst, edoEst, cpEst, 
				celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, formaPago, banco, cuentaDeposito, tarjetaDeposito, claveReferenciado, 
				vigenciaReferenciado, montoBeca, finalidadApoyo, observaciones, idBeneficiario, idUsuario);
	}
	
	public String actualizaDatosDeportivas(int idPeriodo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String tipoBecario, String adscripcion, int idRegion, 
			String escuelaDeportiva, Date fechaIngEscDep, String nivelEduCursa, String turno, String tipoInstitucion, int grado, String nombreEdu,String calleEdu, String numExtEdu, String numIntEdu, String colEdu, String locEdu, 
			String munEdu, String edoEdu, int cpEdu, String telEdu, double promedioGeneral, int edad, String genero, String lugarNacimiento, Date fechaNacimiento,
			String breveHistoria, int integrantesFamiliares, double ingresosFamiliares, String calleVivFam, String numEVivFam, String numIVivFam, String colVivFam, String locVivFam, String munVivFam, String edoVivFam,
			int cpVivFam, String enlaceMaps, String  nombreTutor, String parentescoTutor, String celular, String telDomicilio,
			String tipoTelRef, String numTelRef, String parentescoRef, String observacionesRef, String email, String facebook, String facebook2, String facebook3, String ocupacionTutor, int hermanosInscritos,
			String escuelaHermanosInscritos, double montoBeca, String observaciones, int idBeneficiario, int idUsuario) {
		
		return beneficiariosDAO.actualizaDatosDeportivas(idPeriodo, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, escuelaDeportiva, fechaIngEscDep, 
				nivelEduCursa, turno, tipoInstitucion, grado,nombreEdu, calleEdu, numExtEdu, numIntEdu, colEdu, locEdu, munEdu, edoEdu, cpEdu, telEdu, promedioGeneral, edad, genero, lugarNacimiento, fechaNacimiento, 
				breveHistoria, integrantesFamiliares, ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, nombreTutor, parentescoTutor, 
				celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, ocupacionTutor, hermanosInscritos, escuelaHermanosInscritos, montoBeca, 
				observaciones, idBeneficiario, idUsuario);
	}
	
	public List<Beneficiarios> autocompletarBeneficiariosPeriodo(int idPeriodo, String termino){
		return beneficiariosDAO.autocompletarBeneficiariosPeriodo(idPeriodo, termino);
	}
	
	public List<Beneficiarios> autocompletarBeneficiarios(String termino){
		return beneficiariosDAO.autocompletarBeneficiarios(termino);
	}
	
	public List<Beneficiarios> autocompletarBeneficiarios(String termino, int idPeriodo){
		return beneficiariosDAO.autocompletarBeneficiarios(termino,idPeriodo);
	}
	
	public List<Beneficiarios> autocompletarBeneficiariosNoDepor(String termino, int idPeriodo){
		return beneficiariosDAO.autocompletarBeneficiariosNoDepor(termino,idPeriodo);
	}
	
	public List<Beneficiarios> reporteGeneral(int idTipoBeca, int idPeriodo, int idRegion){
		return beneficiariosDAO.reporteGeneral(idTipoBeca, idPeriodo, idRegion);
	}
}
