package com.siabe.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.siabe.modelo.RelacionDonantesBeneficiarios;
import com.siabe.modelo.Usuario;


import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.DonativosServicio;


@RestController
public class ControladorRestAdministracion {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private DonativosServicio donativosServicio;
	
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
	
	@PostMapping(value ="/administracion/registraDonativoAsig")
	public String actualizaRegistraDonativo(@RequestParam int idDonante, @RequestParam int idBeneficiario, @RequestParam double donativo) {
	//	model.addAttribute("periodos", periodoServicio.todosPeridoIdBeca(idTipoBeca));
		
		Donativos d = donativosServicio.donativo(idDonante);
		
		Beneficiarios b = beneficiariosServicio.beneficiario(idBeneficiario);
				
		double sumaDonante = donativo + donativosServicio.sumaDonativoDonanteBeneficiario(idDonante, 0);
		double sumaBeneficiario = donativo + donativosServicio.sumaDonativoDonanteBeneficiario(0, idBeneficiario);
		
		if(sumaDonante > d.getDonativoTotal()) {
			return "DonativoExeD";
		}else if(sumaBeneficiario > b.getMontoBeca()) {
			return "DonativoExeB";
		}else {		
		return donativosServicio.insertaDonativoDonanteBeneficiario(idDonante, idBeneficiario, donativo);
		}
	}
	
	
	@PostMapping(value ="/administracion/registraRefrendoDonativo")
	public String registraRefrendoDonativo(@RequestParam(value="idDonante[]") Integer[] idDonante, @RequestParam int idPeriodo, @RequestParam int idUsuario) {
	//	model.addAttribute("periodos", periodoServicio.todosPeridoIdBeca(idTipoBeca));
		
		int flag = 0;
		for (int x=0;x<idDonante.length;x++) {
			if(donativosServicio.refrendaDonativo(Integer.valueOf(idDonante[x]), idPeriodo, idUsuario)) {
				flag+=1;
			}
				
			
			  System.out.println(idDonante[x]);
		}
		
		//System.out.println("longitud "+idDonante.length+" | flag"+flag);
		if(idDonante.length == flag) {
		return "Done";
		}else {
			return "Not";
		}
		
		
	
	}
	
	@PostMapping(value ="/administracion/donantivosPeriodo")
	public String donantivosPeriodo(@RequestParam int idPeriodo) {
		List<Donativos> lista = new ArrayList<Donativos>();
		
		String ids = "";
		
		lista = donativosServicio.regresaDonantesPeriodoRFC(idPeriodo);
		
		for (int x=0;x<lista.size();x++) {
			ids+=lista.get(x).getIdDonativo()+"-";			
			
		}
		
		return ids;
		
		
	
	}

}
