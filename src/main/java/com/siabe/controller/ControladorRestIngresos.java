package com.siabe.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;
import com.siabe.modelo.Usuario;


import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.DonativosServicio;


@RestController
public class ControladorRestIngresos {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private DonativosServicio donativosServicio;


	@ModelAttribute
	public void addAttributes(Model model, Principal principal) {
		if (principal != null) {
			Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());

		}
	}

	/*@PostMapping(value = "/catalogos/ajaxAgregarBeneficiario")
	public String postAjaxPeriodo(@RequestParam String nombre, @RequestParam String fecha_inicio,
			@RequestParam String fecha_final, @RequestParam int idTipoBeca) {
		System.out.println("nombre " + nombre);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String response = "";

		try {
			Date dateI = formatter.parse(fecha_inicio);
			Date dateF = formatter.parse(fecha_final);
			response = periodoServicio.insertPeriodo(nombre, dateI, dateF, idTipoBeca);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return response;

	}*/



	@PostMapping(value = "/ingresos/ajaxAgregarDonante")
	public String postAjaxAddDonante(@RequestParam int idPeriodo, @RequestParam String razonSocial, @RequestParam String titulo, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno ,@RequestParam int estatus, @RequestParam String motivoEstatus, @RequestParam String adscripcion,  @RequestParam String tipoDonante, @RequestParam int idRegion, 
			@RequestParam int sector, @RequestParam int idCampania, @RequestParam int medioAutorizacion, @RequestParam String observacionesMedioAutorizacion, @RequestParam int medioCobro, @RequestParam String numPersonal, @RequestParam String dependenciaAdscripcion, @RequestParam double donativoTotal, @RequestParam double donativoQuincenal,
			@RequestParam int numQuincenas, @RequestParam int idQuincenaInicio, @RequestParam String anioQuincena,  @RequestParam int idCuentasBancarias, @RequestParam String referencia, @RequestParam int numPagos, @RequestParam double importeNumPagos, @RequestParam String banco, @RequestParam String nombreTarjetahabiente, @RequestParam String red, @RequestParam String tipoTarjeta,
			@RequestParam String numTarjeta,@RequestParam int mesVencimiento, @RequestParam int anioVencimiento, @RequestParam String tipoDonativo, @RequestParam int mesInicioAportacion, @RequestParam String email, @RequestParam String celular, @RequestParam String telefono1, @RequestParam String telefono2,@RequestParam String calle, @RequestParam String numE,
			@RequestParam String numI, @RequestParam String col, @RequestParam String loc, @RequestParam String mun, @RequestParam String edo, @RequestParam int cp, @RequestParam String calleFiscal, @RequestParam String numEFiscal, @RequestParam String numIFiscal, @RequestParam String colFiscal, 
			@RequestParam String locFiscal, @RequestParam String munFiscal, @RequestParam String edoFiscal, @RequestParam String cpFiscal, @RequestParam String rfc, @RequestParam String observaciones, @RequestParam String idBeneficiarioAsignado, @RequestParam int idUsuario) throws ParseException {
		
		
		String response = donativosServicio.insertaDonante(idPeriodo, razonSocial, titulo, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, adscripcion, tipoDonante, idRegion, sector, idCampania,
				medioAutorizacion, observacionesMedioAutorizacion, medioCobro, numPersonal, dependenciaAdscripcion, donativoTotal, donativoQuincenal, numQuincenas, idQuincenaInicio, anioQuincena, idCuentasBancarias,
				referencia, numPagos, importeNumPagos, banco, nombreTarjetahabiente, red, tipoTarjeta, numTarjeta, mesVencimiento, anioVencimiento, tipoDonativo, mesInicioAportacion, email, celular, telefono1, 
				telefono2, calle,numE, numI, col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, idBeneficiarioAsignado, idUsuario);
				
		
		return response;

	}
	
	@PostMapping(value = "/ingresos/ajaxAgregarPatrocinador")
	public String postAjaxPatrocinador(@RequestParam int idPeriodo, @RequestParam String razonSocial, @RequestParam String titulo, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam int sector, 
			 @RequestParam String descripcionDonativo, @RequestParam String email, @RequestParam String celular, @RequestParam String telefono1, @RequestParam String telefono2, @RequestParam String calle ,@RequestParam String numE, @RequestParam String numI, @RequestParam String col, @RequestParam String loc, @RequestParam String mun, 
			 @RequestParam String edo, @RequestParam int cp, @RequestParam String calleFiscal, @RequestParam String numEFiscal, @RequestParam String numIFiscal, @RequestParam String colFiscal, @RequestParam String locFiscal, @RequestParam String munFiscal, @RequestParam String edoFiscal, @RequestParam String cpFiscal,
			 @RequestParam String rfc, @RequestParam String observaciones, @RequestParam int idUsuario) throws ParseException {
		
		 String response = donativosServicio.insertaPatrocinador(idPeriodo, razonSocial, titulo, nombre, apellidoPaterno, apellidoMaterno, adscripcion, idRegion, sector, descripcionDonativo, email, celular, telefono1, telefono2, calle,numE, numI,
				 col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, idUsuario);
		
		
		return response;

	}
	
	@RequestMapping(value = "/ingresos/autocompleteDonanteMod", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Donativos> postAjaxAutocompleteBeneMod(@RequestParam String term, @RequestParam int tipoDonativo, @RequestParam int idPeriodo) {		

		List<Donativos> donante = donativosServicio.autocompletarDonativos(term, tipoDonativo,idPeriodo); 
		
		Map<String,Donativos> response = new TreeMap<String,Donativos>();
		
		for(int b = 0; b < donante.size() ;b++) {
			if(idPeriodo == 0) {
			response.put(donante.get(b).getNombre(), donante.get(b));
			}else {
				response.put("1", donante.get(b));
			}
		}
		
		return response;

	}
	
	@PostMapping(value = "/ingresos/ajaxModificarDonante")
	public String postAjaxtDonanteModificar( @RequestParam String razonSocial, @RequestParam String titulo, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno ,@RequestParam int estatus, @RequestParam String motivoEstatus, @RequestParam String adscripcion,  @RequestParam String tipoDonante, @RequestParam int idRegion, 
			@RequestParam int sector, @RequestParam int idCampania, @RequestParam int medioAutorizacion, @RequestParam String observacionesMedioAutorizacion, @RequestParam int medioCobro, @RequestParam String numPersonal, @RequestParam String dependenciaAdscripcion, @RequestParam double donativoTotal, @RequestParam double donativoQuincenal,
			@RequestParam int numQuincenas, @RequestParam int idQuincenaInicio, @RequestParam String anioQuincena, @RequestParam int idCuentasBancarias, @RequestParam String referencia, @RequestParam int numPagos, @RequestParam double importeNumPagos, @RequestParam String banco, @RequestParam String nombreTarjetahabiente, @RequestParam String red, @RequestParam String tipoTarjeta,
			@RequestParam String numTarjeta,@RequestParam int mesVencimiento, @RequestParam int anioVencimiento, @RequestParam String tipoDonativo, @RequestParam int mesInicioAportacion, @RequestParam String email, @RequestParam String celular, @RequestParam String telefono1, @RequestParam String telefono2,@RequestParam String calle, @RequestParam String numE,
			@RequestParam String numI, @RequestParam String col, @RequestParam String loc, @RequestParam String mun, @RequestParam String edo, @RequestParam int cp, @RequestParam String calleFiscal, @RequestParam String numEFiscal, @RequestParam String numIFiscal, @RequestParam String colFiscal, 
			@RequestParam String locFiscal, @RequestParam String munFiscal, @RequestParam String edoFiscal, @RequestParam String cpFiscal, @RequestParam String rfc, @RequestParam String observaciones, @RequestParam int idUsuario, @RequestParam int idDonativo) throws ParseException {
		

		String response = donativosServicio.actualizaDatosDonantes( razonSocial, titulo,nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, adscripcion, tipoDonante, idRegion, sector,
				idCampania, medioAutorizacion, observacionesMedioAutorizacion, medioCobro, numPersonal, dependenciaAdscripcion, donativoTotal, donativoQuincenal, numQuincenas, idQuincenaInicio, anioQuincena,
				idCuentasBancarias, referencia, numPagos, importeNumPagos, banco, nombreTarjetahabiente, red, tipoTarjeta, numTarjeta, mesVencimiento, anioVencimiento, tipoDonativo,
				mesInicioAportacion, email, celular, telefono1, telefono2, calle, numE, numI, col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, 
				cpFiscal, rfc, observaciones, idUsuario, idDonativo);
				
		
		return response;

	}
	
	@PostMapping(value = "/ingresos/ajaxModificarPatrocinador")
	public String postAjaxtDeportivaModificar( @RequestParam String razonSocial, @RequestParam String titulo, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam int sector, 
			 @RequestParam String descripcionDonativo, @RequestParam String email, @RequestParam String celular, @RequestParam String telefono1, @RequestParam String telefono2, @RequestParam String calle ,@RequestParam String numE, @RequestParam String numI, @RequestParam String col, @RequestParam String loc, @RequestParam String mun, 
			 @RequestParam String edo, @RequestParam int cp, @RequestParam String calleFiscal, @RequestParam String numEFiscal, @RequestParam String numIFiscal, @RequestParam String colFiscal, @RequestParam String locFiscal, @RequestParam String munFiscal, @RequestParam String edoFiscal, @RequestParam String cpFiscal,
			 @RequestParam String rfc, @RequestParam String observaciones, @RequestParam int idUsuario,  @RequestParam int idDonativo) throws ParseException {
		
		 String response = donativosServicio.actualizaDatosPatrocinadores( razonSocial, titulo, nombre, apellidoPaterno, apellidoMaterno, adscripcion, idRegion, sector, descripcionDonativo, email, 
				 celular, telefono1, telefono2, calle, numE, numI, col, loc, mun, edo, cp, calleFiscal, numEFiscal, numIFiscal, colFiscal, locFiscal, munFiscal, edoFiscal, cpFiscal, rfc, observaciones, 
				 idUsuario, idDonativo);
		
		
		return response;

	}
	
	
	@RequestMapping(value = "/ingresos/autocompleteBenefactorNoPatrocinador", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Donativos> postAjaxAutocompleteBeneNoDepor(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Donativos> donante = donativosServicio.autocompletarBenefactorNoPatrocinador(term, idPeriodo); 
		
		Map<String,Donativos> response = new LinkedHashMap<String,Donativos>();
		
		Donativos don = new Donativos();
		don.setIdDonativo(0);
		don.setNombreCompletoDon("Sin donante");		
		response.put("Sin donante", don);
		for(int b = 0; b < donante.size() ;b++) {
			response.put(donante.get(b).getNombre(), donante.get(b));
		}
		
		return response;

	}
	
	@RequestMapping(value = "/ingresos/autocompleteBenefactorNoPatrocinadorA", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Donativos> postAjaxAutocompleteBeneNoDeporA(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Donativos> donante = donativosServicio.autocompletarBenefactorNoPatrocinador(term, idPeriodo); 
		
		Map<String,Donativos> response = new LinkedHashMap<String,Donativos>();
		/*
		Donativos don = new Donativos();
		don.setIdDonativo(0);
		don.setNombreCompletoDon("Sin donante");		
		response.put("Sin donante", don);*/
		for(int b = 0; b < donante.size() ;b++) {
			response.put(donante.get(b).getNombre(), donante.get(b));
		}
		
		return response;

	}
	
	
	
	@RequestMapping(value = "/ingresos/autocompleteDonantesTodos", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Donativos> postAjaxAutocompleDonantesTodos(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Donativos> donativos = donativosServicio.autocompletarDonativosTodos(term, idPeriodo); 
		
		Map<String,Donativos> response = new LinkedHashMap<String,Donativos>();
		
		for(int b = 0; b < donativos.size() ;b++) {
			response.put(donativos.get(b).getNombre(), donativos.get(b));
		}
		
		return response;

	}
	


}
