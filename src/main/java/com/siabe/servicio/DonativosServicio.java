package com.siabe.servicio;

import com.siabe.dao.DonativosDAO;
import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class DonativosServicio {
	
	@Autowired
    private DonativosDAO donativosDAO;

	public Donativos donativo(int idDonativo) {
		return donativosDAO.donativo(idDonativo);
	}
	
	public List<Donativos> regresaDonantesPeriodo(int idPeriodo) {
		return donativosDAO.donativosPeriodo(idPeriodo);
	}
	
	public List<Donativos> regresaDonantesPeriodoActivas(int idPeriodo) {
		return donativosDAO.donativosActivosPeriodo(idPeriodo);
	}
	
	public String insertaDonante(int idPeriodo, String razonSocial, String titulo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String adscripcion,  String tipoDonante, int idRegion, 
			int sector, int idCampania, int medioAutorizacionDonativo, String observacionesMedioAutorizacion, int medioCobro, String numPersonal, String dependenciaAdscripcion, double donativoTotal, double donativoQuincenal,
			int numQuincenas, int idQuincenaInicio, String anioQuincena, int idCuentasBancarias, String referencia, int numPagos, double importeNumPagos, String banco, String nombreTarjetahabiente, String red, String tipoTarjeta,
			String numTarjeta,int mesVencimiento, int anioVencimiento, String tipoDonativo, int mesInicioAportacion, String email, String celular, String telefono1, String telefono2, String calle,String numE, 
			String numI, String col, String loc, String mun, String edo, int cp, String razonSocialFiscal, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, 
			String locFiscal, String munFiscal, String edoFiscal, String cpFiscal, String rfc, String observaciones, String idBeneficiarioAsignado, int idUsuario) {
		
		return donativosDAO.insertaDonante(idPeriodo, razonSocial, titulo, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, adscripcion, tipoDonante, idRegion, sector, idCampania, 
				medioAutorizacionDonativo, observacionesMedioAutorizacion, medioCobro, numPersonal, dependenciaAdscripcion, donativoTotal, donativoQuincenal, numQuincenas, idQuincenaInicio, anioQuincena,
				idCuentasBancarias, referencia, numPagos, importeNumPagos, banco, nombreTarjetahabiente, red, tipoTarjeta, numTarjeta, mesVencimiento, anioVencimiento, tipoDonativo, mesInicioAportacion, 
				email, celular, telefono1, telefono2, calle,numE, numI, col, loc, mun, edo, cp, razonSocialFiscal, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, 
				idBeneficiarioAsignado, idUsuario);
	}
	
	
	public String refrendaDonante(int idDonativo, int idPeriodo, double donativoTotal, int medioCobro,int numPagos,int quinMes, String observaciones) {
		
		return donativosDAO.refrendaDonante(idDonativo, idPeriodo, donativoTotal, medioCobro, numPagos, quinMes, observaciones);
	}
	
	public String insertaPatrocinador(int idPeriodo, String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno, String adscripcion, int idRegion, int sector, 
			 String descripcionDonativo, String email, String celular, String telefono1, String telefono2, String calle, String numE, String numI, String col, String loc, String mun, 
			 String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, String locFiscal, String munFiscal, String edoFiscal, String cpFiscal,
			 String rfc, String observaciones, int idUsuario) {
		
		return donativosDAO.insertaPatrocinador(idPeriodo, razonSocial, nombre, apellidoPaterno, apellidoMaterno, adscripcion, idRegion, sector, descripcionDonativo, email, celular, telefono1, telefono2,
				calle,numE, numI, col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, idUsuario);
	}
	
	public List<Donativos> todosBeneficiarios(){
		return donativosDAO.obtenerDonativos();
	}
	
	public String actualizaDatosDonantes( String razonSocial, String titulo, String nombre, String apellidoPaterno, String apellidoMaterno ,int estatus, String motivoEstatus, String adscripcion,  String tipoDonante, int idRegion, 
			int sector, int idCampania, int medioAutorizacionDonativo, String observacionesMedioAutorizacion, int medioCobro, String numPersonal, String dependenciaAdscripcion, double donativoTotal, double donativoQuincenal,
			int numQuincenas, int idQuincenaInicio, String anioQuincena, int idCuentasBancarias, String referencia, int numPagos, double importeNumPagos, String banco, String nombreTarjetahabiente, String red, String tipoTarjeta,
			String numTarjeta,int mesVencimiento, int anioVencimiento, String tipoDonativo, int mesInicioAportacion, String email, String celular, String telefono1, String telefono2,String calle,String numE, 
			String numI, String col, String loc, String mun, String edo, int cp, String razonSocialFiscal, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, 
			String locFiscal, String munFiscal, String edoFiscal, String cpFiscal, String rfc, String observaciones, int idUsuario, int idDonativo) {
		
		return donativosDAO.actualizaDatosDonantes( razonSocial, titulo, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, adscripcion, tipoDonante, idRegion, sector, idCampania, 
				medioAutorizacionDonativo, observacionesMedioAutorizacion, medioCobro, numPersonal, dependenciaAdscripcion, donativoTotal, donativoQuincenal, numQuincenas, idQuincenaInicio, anioQuincena,idCuentasBancarias, 
				referencia, numPagos, importeNumPagos, banco, nombreTarjetahabiente, red, tipoTarjeta, numTarjeta, mesVencimiento, anioVencimiento, tipoDonativo, mesInicioAportacion, email, celular, 
				telefono1, telefono2, calle, numE, numI, col, loc, mun, edo, cp, razonSocialFiscal, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones,  
				idUsuario, idDonativo);
	}
	
	public String actualizaDatosPatrocinadores( String razonSocial, String nombre, String apellidoPaterno, String apellidoMaterno, String adscripcion, int idRegion, int sector, 
			 String descripcionDonativo, String email, String celular, String telefono1, String telefono2,String calle,String numE, String numI, String col, String loc, String mun, 
			 String edo, int cp, String calleFiscal, String numEFiscal, String numIFiscal, String colFiscal, String locFiscal, String munFiscal, String edoFiscal, String cpFiscal,
			 String rfc, String observaciones, int idUsuario, int idDonativo) {
		
		return donativosDAO.actualizaDatosPatrocinadores(razonSocial, nombre, apellidoPaterno, apellidoMaterno, adscripcion, idRegion, sector, descripcionDonativo, email, celular, telefono1,
				telefono2, calle,numE, numI, col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, idUsuario, idDonativo);
	}
	/*
	public List<Donativos> autocompletarBeneficiariosPeriodo(int idPeriodo, String termino){
		return beneficiariosDAO.autocompletarBeneficiariosPeriodo(idPeriodo, termino);
	}
	*/
	public List<Donativos> autocompletarDonativos(String termino, int tipoDonativo, int idPeriodo){
		return donativosDAO.autocompletarDonativosTipoDonativo(tipoDonativo, termino, idPeriodo);
	}
	
	public List<Donativos> autocompletarDonantesCobranza(String termino, int idPeriodo, int medioCobro, String idss){
		return donativosDAO.autocompletarDonantesCobranza(termino, idPeriodo, medioCobro, idss);
	}
	
	public List<Donativos> autocompletarBenefactorNoPatrocinador(String termino, int idPeriodo){
		return donativosDAO.autocompletarBenefactorNoPatrocinador(termino, idPeriodo);
	}
	
	public List<Donativos> autocompletarDonativosTodos(String termino, int idPeriodo){
		return donativosDAO.autocompletarDonativosTodos(termino, idPeriodo);
	}
	
	public double sumaDonativoDonanteBeneficiario(int idDonante, int idBeneficiario) {
		return donativosDAO.sumaDonativoDonanteBeneficiario(idDonante, idBeneficiario);
	}
	
	public double donativoDonanteBeneficiario(int idDonante, int idBeneficiario) {
		return donativosDAO.donativoDonanteBeneficiario(idDonante, idBeneficiario);
	}
	
	public String insertaDonativoDonanteBeneficiario(int idDonante, int idBeneficiario, double donativo) {
		return donativosDAO.insertaDonativoDonanteBeneficiario(idDonante, idBeneficiario, donativo);
	}
	
	public List<Donativos> reporteGeneral(int idPeriodo, int idRegion){
		return donativosDAO.reporteGeneral(idPeriodo, idRegion);
	}
	
	public List<Donativos> refrendo(int idPeriodo, int idRegion, int idCampania){
		return donativosDAO.refrendo(idPeriodo, idRegion, idCampania);
	}
	

}
