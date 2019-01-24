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
import com.siabe.modelo.Usuario;


import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.BeneficiariosServicio;


@RestController
public class ControladorRestEgresos {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private BeneficiariosServicio beneficiariosServicio;


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



	@PostMapping(value = "/egresos/ajaxAgregarBeneficiarioBeca")
	public String postAjaxtBeca(@RequestParam int idPeriodo, @RequestParam int idTipoBeca, @RequestParam String matricula, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno , 
			@RequestParam int estatus, @RequestParam String motivoEstatus, @RequestParam String tipoBecario, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam 
			int idCarrera, @RequestParam int periodoActual, @RequestParam double promedioGeneral, @RequestParam int edad, @RequestParam String genero, @RequestParam String lenguaIndigena, 
			@RequestParam String discapacidad, @RequestParam String estadoCivil, @RequestParam String lugarNacimiento, @RequestParam String fechaNacimiento, @RequestParam
			String breveHistoria, @RequestParam int integrantesFamiliares, @RequestParam double ingresosFamiliares, @RequestParam String calleVivFam, @RequestParam String numEVivFam, @RequestParam String numIVivFam,
			@RequestParam String colVivFam, @RequestParam String locVivFam, @RequestParam String munVivFam, @RequestParam String edoVivFam, @RequestParam int cpVivFam, @RequestParam String enlaceMaps, 
			@RequestParam int mismoVivFam, @RequestParam String calleEst, @RequestParam String numEEst, @RequestParam String numIEst, 
			@RequestParam String colEst, @RequestParam String locEst, @RequestParam String munEst, @RequestParam String edoEst, @RequestParam String cpEst, @RequestParam String celular, 
			@RequestParam String telDomicilio, @RequestParam
			String tipoTelRef, @RequestParam String numTelRef, @RequestParam String parentescoRef, @RequestParam String observacionesRef, @RequestParam String email, @RequestParam String facebook, 
			@RequestParam String facebook2, @RequestParam String facebook3, @RequestParam int formaPago, @RequestParam String banco, @RequestParam String cuentaDeposito, @RequestParam 
			String tarjetaDeposito, @RequestParam String claveReferenciado, @RequestParam String vigenciaReferenciado, @RequestParam double montoBeca, @RequestParam String finalidadApoyo, 
			@RequestParam String observaciones, @RequestParam String idBenefactor, @RequestParam int idUsuario) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateBirth = formatter.parse(fechaNacimiento);
		
		String response = beneficiariosServicio.insertaBeneficiarioBeca(idPeriodo,idTipoBeca, matricula, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, 
				idCarrera, periodoActual, promedioGeneral, edad, genero, lenguaIndigena, discapacidad, estadoCivil, lugarNacimiento, dateBirth, breveHistoria, integrantesFamiliares, 
				ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, mismoVivFam, calleEst, numEEst, numIEst, colEst, locEst, 
				munEst, edoEst, cpEst, celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, formaPago, banco, cuentaDeposito, 
				tarjetaDeposito, claveReferenciado, vigenciaReferenciado, montoBeca, finalidadApoyo, observaciones, idBenefactor, idUsuario);
				
		
		return response;

	}
	
	@PostMapping(value = "/egresos/ajaxAgregarBeneficiarioDeportiva")
	public String postAjaxtBeca(@RequestParam int idPeriodo, @RequestParam int idTipoBeca,  @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno , @RequestParam int estatus, 
			@RequestParam String motivoEstatus, @RequestParam String tipoBecario, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam 
			String escuelaDeportiva, @RequestParam String fechaIngEscDep, @RequestParam String nivelEduCursa, @RequestParam String turno, @RequestParam String tipoInstitucion, @RequestParam int grado, 
			@RequestParam String nombreEdu, @RequestParam String calleEdu, @RequestParam String numExtEdu, @RequestParam String numIntEdu, @RequestParam String colEdu, @RequestParam String locEdu, @RequestParam 
			String munEdu, @RequestParam String edoEdu, @RequestParam int cpEdu, @RequestParam String telEdu, @RequestParam double promedioGeneral, @RequestParam int edad, @RequestParam String genero, 
			@RequestParam String lugarNacimiento, @RequestParam String fechaNacimiento, @RequestParam
			String breveHistoria, @RequestParam int integrantesFamiliares, @RequestParam double ingresosFamiliares, @RequestParam String calleVivFam, @RequestParam String numEVivFam, @RequestParam String numIVivFam, 
			@RequestParam String colVivFam, @RequestParam String locVivFam, @RequestParam String munVivFam, @RequestParam String edoVivFam, @RequestParam
			int cpVivFam, @RequestParam String enlaceMaps, @RequestParam String  nombreTutor, @RequestParam String parentescoTutor, @RequestParam String celular, @RequestParam String telDomicilio, @RequestParam
			String tipoTelRef, @RequestParam String numTelRef, @RequestParam String parentescoRef, @RequestParam String observacionesRef, @RequestParam String email, @RequestParam String facebook, 
			@RequestParam String facebook2, @RequestParam String facebook3, @RequestParam String ocupacionTutor, @RequestParam int hermanosInscritos, @RequestParam
			String escuelaHermanosInscritos, @RequestParam double montoBeca, @RequestParam String observaciones, @RequestParam int idUsuario) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateIn = formatter.parse(fechaIngEscDep);
		
		Date dateBirth = formatter.parse(fechaNacimiento);
		 
		 String response = beneficiariosServicio.insertaBeneficiarioDeportiva(idPeriodo,idTipoBeca, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, escuelaDeportiva, 
				 dateIn, nivelEduCursa, turno, tipoInstitucion, grado, nombreEdu, calleEdu, numExtEdu, numIntEdu, colEdu, locEdu, munEdu, edoEdu, cpEdu, telEdu, promedioGeneral, edad, genero,
				lugarNacimiento, dateBirth, breveHistoria, integrantesFamiliares, ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam,
				enlaceMaps, nombreTutor, parentescoTutor, celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, ocupacionTutor,
				hermanosInscritos, escuelaHermanosInscritos, montoBeca, observaciones, idUsuario);
		
		
		return response;

	}
	
	@RequestMapping(value = "/egresos/autocompleteBeneficiarioMod", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Beneficiarios> postAjaxAutocompleteBeneMod(@RequestParam String term, @RequestParam int idPeriodo, @RequestParam int idTipoBeca) {		

		List<Beneficiarios> beneficiario = beneficiariosServicio.autocompletarBeneficiarios(term,idPeriodo,idTipoBeca); 
		
		Map<String,Beneficiarios> response = new TreeMap<String,Beneficiarios>();
		
		for(int b = 0; b < beneficiario.size() ;b++) {
			if(idPeriodo == 0 && idTipoBeca == 0) {
			response.put(beneficiario.get(b).getNombre(), beneficiario.get(b));
			}else {
			response.put("1", beneficiario.get(b));			
			}
		}
		
		return response;

	}
	
	@PostMapping(value = "/egresos/ajaxModificarBeneficiarioBeca")
	//public String postAjaxtBecaModificar(@RequestParam int idPeriodo, @RequestParam int idTipoBeca, @RequestParam String matricula, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno , 
	public String postAjaxtBecaModificar( @RequestParam String matricula, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno ,
	@RequestParam int estatus, @RequestParam String motivoEstatus, @RequestParam String tipoBecario, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam 
			int idCarrera, @RequestParam int periodoActual, @RequestParam double promedioGeneral, @RequestParam int edad, @RequestParam String genero, @RequestParam String lenguaIndigena, 
			@RequestParam String discapacidad, @RequestParam String estadoCivil, @RequestParam String lugarNacimiento, @RequestParam String fechaNacimiento, @RequestParam
			String breveHistoria, @RequestParam int integrantesFamiliares, @RequestParam double ingresosFamiliares, @RequestParam String calleVivFam, @RequestParam String numEVivFam, @RequestParam String numIVivFam,
			@RequestParam String colVivFam, @RequestParam String locVivFam, @RequestParam String munVivFam, @RequestParam String edoVivFam, @RequestParam int cpVivFam, @RequestParam String enlaceMaps, 
			@RequestParam int mismoVivFam, @RequestParam String calleEst, @RequestParam String numEEst, @RequestParam String numIEst, 
			@RequestParam String colEst, @RequestParam String locEst, @RequestParam String munEst, @RequestParam String edoEst, @RequestParam String cpEst, @RequestParam String celular, 
			@RequestParam String telDomicilio, @RequestParam
			String tipoTelRef, @RequestParam String numTelRef, @RequestParam String parentescoRef, @RequestParam String observacionesRef, @RequestParam String email, @RequestParam String facebook, 
			@RequestParam String facebook2, @RequestParam String facebook3, @RequestParam int formaPago, @RequestParam String banco, @RequestParam String cuentaDeposito, @RequestParam 
			String tarjetaDeposito, @RequestParam String claveReferenciado, @RequestParam String vigenciaReferenciado, @RequestParam double montoBeca, @RequestParam String finalidadApoyo, 
			@RequestParam String observaciones, @RequestParam int idUsuario, @RequestParam int idBeneficiario) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateBirth = formatter.parse(fechaNacimiento);
		
		String response = beneficiariosServicio.actualizaDatosBecas( matricula, nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, 
				idCarrera, periodoActual, promedioGeneral, edad, genero, lenguaIndigena, discapacidad, estadoCivil, lugarNacimiento, dateBirth, breveHistoria, integrantesFamiliares, 
				ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam, enlaceMaps, mismoVivFam, calleEst, numEEst, numIEst, colEst, locEst, 
				munEst, edoEst, cpEst, celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, formaPago, banco, cuentaDeposito, 
				tarjetaDeposito, claveReferenciado, vigenciaReferenciado, montoBeca, finalidadApoyo, observaciones, idBeneficiario, idUsuario);
				
		
		return response;

	}
	
	@PostMapping(value = "/egresos/ajaxModificarBeneficiarioDeportiva")
	//public String postAjaxtDeportivaModificar(@RequestParam int idPeriodo, @RequestParam int idTipoBeca, @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno , @RequestParam int estatus, 
			public String postAjaxtDeportivaModificar( @RequestParam String nombre, @RequestParam String apellidoPaterno, @RequestParam String apellidoMaterno , @RequestParam int estatus,
			@RequestParam String motivoEstatus, @RequestParam String tipoBecario, @RequestParam String adscripcion, @RequestParam int idRegion, @RequestParam 
			String escuelaDeportiva, @RequestParam String fechaIngEscDep, @RequestParam String nivelEduCursa, @RequestParam String turno, @RequestParam String tipoInstitucion, @RequestParam int grado, 
			@RequestParam String nombreEdu, @RequestParam String calleEdu, @RequestParam String numExtEdu, @RequestParam String numIntEdu, @RequestParam String colEdu, @RequestParam String locEdu, @RequestParam 
			String munEdu, @RequestParam String edoEdu, @RequestParam int cpEdu, @RequestParam String telEdu, @RequestParam double promedioGeneral, @RequestParam int edad, @RequestParam String genero, 
			@RequestParam String lugarNacimiento, @RequestParam String fechaNacimiento, @RequestParam
			String breveHistoria, @RequestParam int integrantesFamiliares, @RequestParam double ingresosFamiliares, @RequestParam String calleVivFam, @RequestParam String numEVivFam, @RequestParam String numIVivFam, 
			@RequestParam String colVivFam, @RequestParam String locVivFam, @RequestParam String munVivFam, @RequestParam String edoVivFam, @RequestParam
			int cpVivFam, @RequestParam String enlaceMaps, @RequestParam String  nombreTutor, @RequestParam String parentescoTutor, @RequestParam String celular, @RequestParam String telDomicilio, @RequestParam
			String tipoTelRef, @RequestParam String numTelRef, @RequestParam String parentescoRef, @RequestParam String observacionesRef, @RequestParam String email, @RequestParam String facebook, 
			@RequestParam String facebook2, @RequestParam String facebook3, @RequestParam String ocupacionTutor, @RequestParam int hermanosInscritos, @RequestParam
			String escuelaHermanosInscritos, @RequestParam double montoBeca, @RequestParam String observaciones, @RequestParam int idUsuario, @RequestParam int idBeneficiario) throws ParseException {
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date dateIn = formatter.parse(fechaIngEscDep);
		
		Date dateBirth = formatter.parse(fechaNacimiento);
		 
		 String response = beneficiariosServicio.actualizaDatosDeportivas(nombre, apellidoPaterno, apellidoMaterno, estatus, motivoEstatus, tipoBecario, adscripcion, idRegion, escuelaDeportiva, 
				 dateIn, nivelEduCursa, turno, tipoInstitucion, grado, nombreEdu, calleEdu, numExtEdu, numIntEdu, colEdu, locEdu, munEdu, edoEdu, cpEdu, telEdu, promedioGeneral, edad, genero,
				lugarNacimiento, dateBirth, breveHistoria, integrantesFamiliares, ingresosFamiliares, calleVivFam, numEVivFam, numIVivFam, colVivFam, locVivFam, munVivFam, edoVivFam, cpVivFam,
				enlaceMaps, nombreTutor, parentescoTutor, celular, telDomicilio, tipoTelRef, numTelRef, parentescoRef, observacionesRef, email, facebook, facebook2, facebook3, ocupacionTutor,
				hermanosInscritos, escuelaHermanosInscritos, montoBeca, observaciones, idBeneficiario,idUsuario);
		
		
		return response;

	}
	
	@RequestMapping(value = "/egresos/autocompleteBeneficiario", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Beneficiarios> postAjaxAutocompleteBene(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Beneficiarios> beneficiario = beneficiariosServicio.autocompletarBeneficiarios(term,idPeriodo); 
		
		Map<String,Beneficiarios> response = new TreeMap<String,Beneficiarios>();
		
		for(int b = 0; b < beneficiario.size() ;b++) {
			response.put(beneficiario.get(b).getNombre(), beneficiario.get(b));
		}
		
		return response;

	}

	@RequestMapping(value = "/egresos/autocompleteBeneficiarioNoDepor", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Beneficiarios> postAjaxAutocompleteBeneNoDepor(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Beneficiarios> beneficiario = beneficiariosServicio.autocompletarBeneficiariosNoDepor(term,idPeriodo); 
		
		Map<String,Beneficiarios> response = new LinkedHashMap<String,Beneficiarios>();
		Beneficiarios ben = new Beneficiarios();
		ben.setIdBeneficiario(0);
		ben.setNombreCompletoBene("Sin beneficiario");
		
		response.put("Sin beneficiario", ben);
		for(int b = 0; b < beneficiario.size() ;b++) {
			response.put(beneficiario.get(b).getNombre(), beneficiario.get(b));
		}
		
		return response;

	}
	
	
	@RequestMapping(value = "/egresos/autocompleteBeneficiarioNoDeporA", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Beneficiarios> postAjaxAutocompleteBeneNoDeporA(@RequestParam String term, @RequestParam int idPeriodo) {		

		List<Beneficiarios> beneficiario = beneficiariosServicio.autocompletarBeneficiariosNoDepor(term,idPeriodo); 
		
		Map<String,Beneficiarios> response = new LinkedHashMap<String,Beneficiarios>();
		/*Beneficiarios ben = new Beneficiarios();
		ben.setIdBeneficiario(0);
		ben.setNombreCompletoBene("Sin beneficiario");
		
		response.put("Sin beneficiario", ben);*/
		for(int b = 0; b < beneficiario.size() ;b++) {
			response.put(beneficiario.get(b).getNombre(), beneficiario.get(b));
		}
		
		return response;

	}
	
	


}
