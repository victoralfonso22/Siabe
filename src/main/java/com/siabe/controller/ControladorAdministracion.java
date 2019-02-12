package com.siabe.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;
import com.siabe.modelo.RelacionDonantesBeneficiarios;
import com.siabe.modelo.Usuario;
import com.siabe.servicio.UsuarioServicio;

import com.siabe.utils.UtilidadesWeb;

import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.DonativosServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.PermisosMenuServicio;




@Controller
public class ControladorAdministracion {
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
	
	@Autowired
	private UtilidadesWeb utilidadesWeb;
	
	@Autowired
	private PeriodoServicio periodoServicio;
	
	@Autowired
	private BeneficiariosServicio beneficiariosServicio;
	
	@Autowired
	private DonativosServicio donativosServicio;

	@ModelAttribute
	public void addAttributes(Model model, Principal principal) {
		if (principal != null) {
			Usuario u = (Usuario) this.usuarioServicio.regresaUsuario(principal.getName());
			model.addAttribute("nombreUsuario", u.getNombre());
			model.addAttribute("idUsuario", u.getIdUsuario());
			model.addAttribute("seccionPermiso",
					permisosMenuServicio.todosPermisosMenuXSeccion(u.getIdUsuario().intValue()));
			model.addAttribute("menuPermiso", permisosMenuServicio.todosPermisosMenu(u.getIdUsuario().intValue()));
			model.addAttribute("permisoGlobal", utilidadesWeb.direccionActual(u.getIdUsuario().intValue()));
		}
	}
	
	@GetMapping(value = "/administracion/comprobanteFiscal")
	public String comprobante(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPeridosActivos());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/comprobanteFiscal";
	}
	
	
	@GetMapping(value = "/administracion/asignacion")
	public String asignacion(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPerido());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/asignacion";
	}
	
	@RequestMapping("/administracion/actualizaRegistrosAsig")
	public String actualizaRegistrosAsig(Model model, Principal principal,@RequestParam int idPeriodo, @RequestParam int tipoAsig, @RequestParam int id) {
		
		List<RelacionDonantesBeneficiarios> r = new ArrayList<RelacionDonantesBeneficiarios>();
		List<Donativos> d = donativosServicio.regresaDonantesPeriodoActivas(idPeriodo);
		List<Beneficiarios> b = beneficiariosServicio.beneficiariosActivosPeriodo(idPeriodo);
		
		if(tipoAsig == 1) {
			
			for(int i=0;i < d.size(); i++) {
				RelacionDonantesBeneficiarios relacionDonantesBeneficiarios = new RelacionDonantesBeneficiarios();
				relacionDonantesBeneficiarios.setIdRelacionDB(i);
				relacionDonantesBeneficiarios.setIdDonante(d.get(i).getIdDonativo());
				relacionDonantesBeneficiarios.setIdBeneficiario(0);
				relacionDonantesBeneficiarios.setNombreCompleto(d.get(i).getNombreCompletoDon());	
				relacionDonantesBeneficiarios.setTipoBeca("");
				relacionDonantesBeneficiarios.setSaldo(utilidadesWeb.formatoMoneda2(d.get(i).getDonativoTotal() - donativosServicio.sumaDonativoDonanteBeneficiario(d.get(i).getIdDonativo(), 0)));
				relacionDonantesBeneficiarios.setDonativoAsignado(utilidadesWeb.formatoMoneda2(donativosServicio.donativoDonanteBeneficiario(d.get(i).getIdDonativo(), id)));
				if(donativosServicio.sumaDonativoDonanteBeneficiario(d.get(i).getIdDonativo(), 0) > 0) {
				//	relacionDonantesBeneficiarios.setClase("tdSencilloPermiso");
					relacionDonantesBeneficiarios.setClase(true);
				}else {
					relacionDonantesBeneficiarios.setClase(false);
				}
				r.add(relacionDonantesBeneficiarios);
			}
			
			
		
			model.addAttribute("registrosTab", r);
			model.addAttribute("asig", 1);	
		
		}else {
			
			for(int i=0;i < b.size(); i++) {
				RelacionDonantesBeneficiarios relacionDonantesBeneficiarios = new RelacionDonantesBeneficiarios();
				relacionDonantesBeneficiarios.setIdRelacionDB(i);
				relacionDonantesBeneficiarios.setIdDonante(0);
				relacionDonantesBeneficiarios.setIdBeneficiario(b.get(i).getIdBeneficiario());
				relacionDonantesBeneficiarios.setNombreCompleto(b.get(i).getNombreCompletoBene());		
				relacionDonantesBeneficiarios.setTipoBeca(b.get(i).getTipoBeca());
				relacionDonantesBeneficiarios.setSaldo(utilidadesWeb.formatoMoneda2(b.get(i).getMontoBeca() - donativosServicio.sumaDonativoDonanteBeneficiario(0, b.get(i).getIdBeneficiario())));
				relacionDonantesBeneficiarios.setDonativoAsignado(utilidadesWeb.formatoMoneda2(donativosServicio.donativoDonanteBeneficiario(id,  b.get(i).getIdBeneficiario())));
				if(donativosServicio.sumaDonativoDonanteBeneficiario(0, b.get(i).getIdBeneficiario()) > 0) {
					relacionDonantesBeneficiarios.setClase(true);
				}else {
					relacionDonantesBeneficiarios.setClase(false);
				}
				r.add(relacionDonantesBeneficiarios);
			}
			
			model.addAttribute("registrosTab", r);
			model.addAttribute("asig", 2);				
		}
		
		
		
		if(principal == null) {
		return "/login";	
		}else {
		return "/administracion/asignacion :: #registrosAs";
		}
	}
	
	@RequestMapping("/administracion/regresaDonativoAsig")
	public String regresaDonativoAsig(Model model, Principal principal,@RequestParam int idDonante, @RequestParam int idBeneficiario) {		
		
			model.addAttribute("donativoAsignado", donativosServicio.sumaDonativoDonanteBeneficiario(idDonante, idBeneficiario));
		
		if(principal == null) {
		return "/login";	
		}else {
			if(idDonante == 0) {
				return "/administracion/asignacion :: #spMontoAsignado";
			}else if(idBeneficiario == 0) {
				return "/administracion/asignacion :: #spDonativoAsignado";
			}else {
				return null;
			}
		
		}
	}

}
