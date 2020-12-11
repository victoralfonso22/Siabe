package com.siabe.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.DepositoTransferencia;
import com.siabe.modelo.Donativos;
import com.siabe.modelo.Nomina;
import com.siabe.modelo.Tarjeta;
import com.siabe.modelo.Usuario;


import com.siabe.servicio.UsuarioServicio;
import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.DonativosServicio;
import com.siabe.servicio.NominaServicio;
import com.siabe.servicio.DepositoTransferenciaServicio;
import com.siabe.servicio.TarjetaServicio;
import com.siabe.utils.UtilidadesWeb;


@RestController
public class ControladorRestAdministracion {

	@Autowired
	private UsuarioServicio usuarioServicio;

	@Autowired
	private DonativosServicio donativosServicio;
	
	@Autowired
	private BeneficiariosServicio beneficiariosServicio;
	
	@Autowired
	private DepositoTransferenciaServicio depositoTransferenciaServicio;
	
	@Autowired
	private NominaServicio nominaServicio;
	
	@Autowired
	private TarjetaServicio tarjetaServicio;
	
	@Autowired
	private UtilidadesWeb utilidadesWeb;
	
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
	
	
	@PostMapping(value ="/administracion/refrendaDonantes")
	public String refrendaDonantes(@RequestParam String cadena, @RequestParam int idPeriodo) {
			
		String[] datos = cadena.split(",");
		int id = 0;
		double donativoTotal = 0;
		int medioCobro = 0;
		int numPagos = 0;
		int quinMes = 0;
		String observ = "";
		for(int i=0;i< datos.length;i++) {
	/*		
		Donativos d = donativosServicio.donativo(Integer.valueOf(datos[i]));
		
		
		if(d.getCp() == null) {
			cp = 0;
		}else {
			cp = Integer.valueOf(d.getCp());
		}*/
		String[] descodificado = datos[i].split("-");
		
		for(int x = 0;x<descodificado.length;x++) {
			id = Integer.valueOf(descodificado[0]);
			donativoTotal = Double.valueOf(descodificado[1]);
			medioCobro = Integer.valueOf(descodificado[2]);
			numPagos = Integer.valueOf(descodificado[3]);			
			quinMes = Integer.valueOf(descodificado[4]);
			observ = descodificado[5];

		}
		
		donativosServicio.refrendaDonante(id, idPeriodo, donativoTotal, medioCobro, numPagos, quinMes, observ);
		
		
		/*donativosServicio.insertaDonante(idPeriodo, d.getRazonSocial(), d.getTitulo(), d.getNombre(), d.getApellidoPaterno(), d.getApellidoMaterno(), d.getEstatus(), d.getMotivoEstatus(), String.valueOf(d.getAdscripcion()), 
				String.valueOf(d.getTipoDonante()), d.getIdRegion(), d.getSector(), d.getIdCampania(), d.getMedioAutorizacionDonativo(), d.getObservacionMedioAutorizacion(), d.getMedioCobro(), 
				d.getNumPersonal(), d.getDependenciaAdscripcion(), d.getDonativoTotal(),d.getDonativoQuincenal(),
				
				d.getNumQuincenas(), d.getIdQuincenaInicio(), d.getAnioQuincena(),
				d.getIdCuentaBancaria(), d.getReferencia(), d.getNumPagos(), d.getImporteNumPagos(), d.getBanco(), d.getNombreTarjetahabiente(), d.getRed(), d.getTipoTarjeta(),
				
				d.getNumTarjeta(), d.getMesVencimiento(), d.getAnioVencimiento(), d.getTipoDonativo(), d.getMesInicioAportacion(), d.getEmail(), d.getCelular(), d.getTelefono1(),
				d.getTelefono2(),
				
				d.getCalle(), d.getNumE(), d.getNumI(), d.getCol(), d.getLoc(), d.getMun(), d.getEdo(), cp, d.getRazonSocialFiscal(), d.getCalleFiscal(), d.getNumEFiscal(), 
				d.getNumIFiscal(), d.getColFiscal(), d.getLocFiscal(), d.getMunFiscal(), d.getEdoFiscal(), d.getCpFiscal(), d.getRfc(), d.getObservaciones(), 
				String.valueOf(d.getIdBeneficiario()),1);*/
			

		
		
		}
		
				
		return "Done";
	}
	
	
	@PostMapping(value ="/administracion/refrendaBeneficiarios")
	public String refrendaBeneficiarios(@RequestParam String cadena, @RequestParam int idPeriodo) {
			
		String[] datos = cadena.split(",");
	
		for(int i=0;i< datos.length;i++) {
			
			System.out.println(Integer.valueOf(datos[i])+" "+ idPeriodo);
		
		beneficiariosServicio.refrendarBeneficiario(Integer.valueOf(datos[i]), idPeriodo);
		
		}		
				
		return "Done";
	}
	
	
	@PostMapping(value ="/administracion/numDescuento")
	public int getNumeroDescuento(@RequestParam int idDonante, @RequestParam int medioCobro) {
			
		if (medioCobro == 1) {
			return nominaServicio.numDescuento(idDonante);
		}
		else if (medioCobro == 2) {
			return depositoTransferenciaServicio.numDescuento(idDonante);
		} else if (medioCobro == 3) {
			return tarjetaServicio.numDescuento(idDonante);
		}
		return 0;

	}
	
	@PostMapping(value ="/administracion/saldoNomina")
	public double getSaldo(@RequestParam int idDonante) {
			
	
		return nominaServicio.saldo(idDonante);

	}
	
/*	@RequestMapping(value = "/ingresos/autocompleteDonanteCobranza", method = RequestMethod.GET)
	public  @ResponseBody Map<String,Nomina> autocompleteDonanteCobranza(@RequestParam String term,  @RequestParam int idPeriodo,  @RequestParam int medioCobro) {		

		List<Nomina> donante = nominaServicio.autocompletarDonantesNomina(term, idPeriodo); 
		
		Map<String,Nomina> response = new LinkedHashMap<String,Nomina>();
		
		///if(donante.size() == 0) {
		Nomina don = new Nomina();
		/*don.setIdDonativo(0);
		don.setNombreCompletoDon("Sin donante");	
		don.setTitulo("");
		response.put("Sin donante", don); */
		//}else {
	/*	for(int b = 0; b < donante.size() ;b++) {
			response.put(donante.get(b).getNombreCompletoDon(), donante.get(b));
		//}
		}
		return response;

	}*/
	
	@PostMapping(value = "/administracion/tablaMedioCobro")
	public  String tablaMedioCobro( @RequestParam int idPeriodo, @RequestParam int medioCobro, @RequestParam int idQuincena, @RequestParam int anio, @RequestParam int mes) {		
		String response = "";
		String muestraGuardar = "";
		DecimalFormat formato = new DecimalFormat("#,###.00");
		int indicador = 0;
		if(medioCobro == 1) {
		
		List<Nomina> nomina = nominaServicio.obtenerNominasPeriodo(idPeriodo, idQuincena); 
		
		 response = "<table class=\"table tabla table-hover table-condensed\" id=\"registrosRB\" >\r\n" + 
				"	 <thead>\r\n" + 
				"	<tr>\r\n" + 
				"		<th>#</th>\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\">Nombre</label></th>\r\n" + 
				"		<th><label style=\"color:#fff; font-weight: normal;\">No. de personal</label></th>		\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\">Donativo autorizado</label></th>\r\n" + 
				"		<th><label style=\"color:#fff; font-weight: normal;\">Número de quincenas autorizadas</label></th>								\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\">No. de descuento</label></th>\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\">Descuento quincenal</label></th>\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\">Saldo</label></th>\r\n" + 
				"		<th><label  style=\"color:#fff; font-weight: normal;\"></label></th>\r\n" + 
				"	</tr>\r\n" + 
				"	</thead>\r\n" + 
				"	<tbody>";
		
		if(nomina.size() > 0) {
			muestraGuardar = "$(\"#btn-guardar-cobranza\").show();";
		for(int b = 0; b < nomina.size() ;b++) {
			int valor= b+1;
			String numPer = "";
			if(nomina.get(b).getNumPersonal() != null) 
			{numPer = nomina.get(b).getNumPersonal();}
			
			double descuentoSugerido = Double.valueOf(nomina.get(b).getDonativoTotal()) / nomina.get(b).getNumQuincenas();
			
			response+="<tr  id=\"trRow"+valor+"\" >							\r\n" + 
					"		<td ><label style=\"color: #333333; font-weight: normal;\" id=\"lab"+valor+"\"> "+ valor +"</label><input type=\"hidden\" id=\"numHidde"+valor+"\" value=\""+valor+"\"/></td>\r\n" + 
					"		<td   ><input type=\"text\" onblur='blurNombre("+ valor +")' value='"+nomina.get(b).getNombreCompletoDon()+"' onkeyup=\"autocompletarDonanteCobranza(this.id,"+valor+");\" id=\"auto"+valor+"\"> <input type=\"hidden\"  value='"+nomina.get(b).getIdDonativo()+"' id=\"idDon"+valor+"\"/> <input type='hidden' id='nomDon" + valor + "'   value='"+nomina.get(b).getNombreCompletoDon()+"'  /> </td>\r\n" + 
					"		<td   ><span id=\"numPer"+valor+"\" >"+numPer+"</span></td>					\r\n" + 
					"		<td   ><span id=\"donaAuto"+valor+"\" >"+"$ "+formato.format(Double.valueOf(nomina.get(b).getDonativoTotal()))+"</span></td>					\r\n" + 
					"		<td   ><span id=\"quincenaAuto"+valor+"\" >"+nomina.get(b).getNumQuincenas()+"</span></td>\r\n" + 
					"		<td   ><span id=\"numDescuento"+valor+"\" >"+nomina.get(b).getNumDescuento()+"</span></td>					\r\n" + 
					"		<td   ><input value='"+formato.format(nomina.get(b).getDonativoQuincenal())+"' type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual"+valor+"','saldo"+valor+"', "+valor+"); \" onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal"+valor+"\"> </td> <input type='hidden' id='descuentoQuincenalHidden"+valor+"' value='"+formato.format(descuentoSugerido)+"' />	\r\n" + 
					"		<td   ><span id=\"saldo"+valor+"\" >"+"$ "+formato.format(Double.valueOf(nomina.get(b).getSaldo()))+"</span><input type='hidden' id='saldoActual"+valor+"' value='"+nomina.get(b).getSaldo()+"'/></td>					\r\n" + 
					"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
					"			\r\n" + 
					"	</tr>";
			
	
		}
		}else {
			response+="<tr  id='trRow1' >							\r\n" + 
					"		<td ><label style=\"color: #333333; font-weight: normal;\" id='lab1'> 1</label><input type=\"hidden\" id=\"numHidde1\" value=\"1\"/></td>\r\n" + 
					"		<td   ><input type=\"text\" onblur='blurNombre(1)' onkeyup=\"autocompletarDonanteCobranza(this.id,1);\" id=\"auto1\"> <input type=\"hidden\" id=\"idDon1\"/>   <input type='hidden' id='nomDon1' /></td>\r\n" + 
					"		<td   ><span id=\"numPer1\" /></td>					\r\n" + 
					"		<td   ><span id=\"donaAuto1\" /></td>					\r\n" + 
					"		<td   ><span id=\"quincenaAuto1\" /></td>\r\n" + 
					"		<td   ><span id=\"numDescuento1\" /></td>					\r\n" + 
					"		<td   ><input type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual1','saldo1', 1); \"  onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal1\"></td>  <input type='hidden' id='descuentoQuincenalHidden1'/>	\r\n" + 
					"		<td   ><span id=\"saldo1\"/>  <input type='hidden' id='saldoActual1'/> </td>					\r\n" + 
					"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
					"			\r\n" + 
					"	</tr>";
		}
		
		response +="</tbody>\r\n" + 
				"	 <tfoot> \r\n" + 
				"  <tr> \r\n" + 
				"     <td colspan=\"8\">  </td>\r\n" + 
				"     <td> \r\n" + 
				"        <button type=\"button\" class=\"btn btn-success button_agregar_producto\"> Agregar </button> \r\n" + 
				"     </td> \r\n" + 
				" </tr> \r\n" + 
				"</tfoot> \r\n" + 
				"\r\n" + 
				"</table>"
				+ ""
				+ "<script>\r\n" + 
				"\r\n" + 
				"$(document).ready(function(){\r\n" + 
				"       tablaNominas(); "+muestraGuardar+"\r\n" + 
				"    }); \r\n" + 
				"</script>";
		
		if(nomina.size() > 0) {indicador = nomina.size();}
		
		}else if(medioCobro == 2) {
			
			List<DepositoTransferencia> dt = depositoTransferenciaServicio.obtenerDepositoTransferenciaPeriodo(idPeriodo); 
			
			 response = "<table class=\"table tabla table-hover table-condensed\" id=\"registrosRB\" >\r\n" + 
					"	 <thead>\r\n" + 
					"	<tr>\r\n" + 
					"		<th>#</th>\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\">Nombre</label></th>\r\n" + 

					"		<th><label  style=\"color:#fff; font-weight: normal;\">Donativo autorizado</label></th>\r\n" + 
					"		<th><label style=\"color:#fff; font-weight: normal;\">Número de meses autorizados</label></th>								\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\">No. de descuento</label></th>\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\">Donativo</label></th>\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\">Saldo</label></th>\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\">Fecha</label></th>\r\n" + 
					"		<th><label  style=\"color:#fff; font-weight: normal;\"></label></th>\r\n" + 
					"	</tr>\r\n" + 
					"	</thead>\r\n" + 
					"	<tbody>";
			
			if(dt.size() > 0) {
				muestraGuardar = "$(\"#btn-guardar-cobranza\").show();";
			for(int b = 0; b < dt.size() ;b++) {
				int valor= b+1;
	
				
				double descuentoSugerido = Double.valueOf(dt.get(b).getDonativoTotal()) / dt.get(b).getNumPagos();
				
				response+="<tr  id=\"trRow"+valor+"\" >							\r\n" + 
						"		<td ><label style=\"color: #333333; font-weight: normal;\" id=\"lab"+valor+"\"> "+ valor +"</label><input type=\"hidden\" id=\"numHidde"+valor+"\" value=\""+valor+"\"/></td>\r\n" + 
						"		<td   ><input type=\"text\" onblur='blurNombre("+ valor +")' value='"+dt.get(b).getNombreCompletoDon()+"' onkeyup=\"autocompletarDonanteCobranza(this.id,"+valor+");\" id=\"auto"+valor+"\"> <input type=\"hidden\"  value='"+dt.get(b).getIdDonativo()+"' id=\"idDon"+valor+"\"/> <input type='hidden' id='nomDon" + valor + "'   value='"+dt.get(b).getNombreCompletoDon()+"'  /> </td>\r\n" + 

						"		<td   ><span id=\"donaAuto"+valor+"\" >"+"$ "+formato.format(Double.valueOf(dt.get(b).getDonativoTotal()))+"</span></td>					\r\n" + 
						"		<td   ><span id=\"mesesAuto"+valor+"\" >"+dt.get(b).getNumPagos()+"</span></td>\r\n" + 
						"		<td   ><span id=\"numDescuento"+valor+"\" >"+dt.get(b).getNumDescuento()+"</span></td>					\r\n" + 
						"		<td   ><input value='"+formato.format(dt.get(b).getDonativo())+"' type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual"+valor+"','saldo"+valor+"', "+valor+"); \" onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal"+valor+"\"> </td> <input type='hidden' id='descuentoQuincenalHidden"+valor+"' value='"+formato.format(descuentoSugerido)+"' />	\r\n" + 
						"		<td   ><span id=\"saldo"+valor+"\" >"+"$ "+formato.format(Double.valueOf(dt.get(b).getSaldo()))+"</span><input type='hidden' id='saldoActual"+valor+"' value='"+dt.get(b).getSaldo()+"'/></td>					\r\n" +
						"		<td   ><input value='"+dt.get(b).getFecha()+"' type=\"date\" id=\"fecha"+valor+"\" onchange=\"cambiaFecha(this.value, "+valor+"); \" /> </td> \r\n" +
						"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
						"			\r\n" + 
						"	</tr>";
				
		
			}
			}else {
				response+="<tr  id='trRow1' >							\r\n" + 
						"		<td ><label style=\"color: #333333; font-weight: normal;\" id='lab1'> 1</label><input type=\"hidden\" id=\"numHidde1\" value=\"1\"/></td>\r\n" + 
						"		<td   ><input type=\"text\" onblur='blurNombre(1)' onkeyup=\"autocompletarDonanteCobranza(this.id,1);\" id=\"auto1\"> <input type=\"hidden\" id=\"idDon1\"/>   <input type='hidden' id='nomDon1' /></td>\r\n" + 

						"		<td   ><span id=\"donaAuto1\" /></td>					\r\n" + 
						"		<td   ><span id=\"mesesAuto1\" /></td>\r\n" + 
						"		<td   ><span id=\"numDescuento1\" /></td>					\r\n" + 
						"		<td   ><input type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual1','saldo1', 1); \"  onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal1\"></td>  <input type='hidden' id='descuentoQuincenalHidden1'/>	\r\n" + 
						"		<td   ><span id=\"saldo1\"/>  <input type='hidden' id='saldoActual1'/> </td>					\r\n" + 
						"		<td   ><input  type=\"date\" id=\"fecha1\" onchange=\"cambiaFecha(this.value, 1); \"  /> </td> \r\n" +
						"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
						"			\r\n" + 
						"	</tr>";
			}
			
			response +="</tbody>\r\n" + 
					"	 <tfoot> \r\n" + 
					"  <tr> \r\n" + 
					"     <td colspan=\"8\">  </td>\r\n" + 
					"     <td> \r\n" + 
					"        <button type=\"button\" class=\"btn btn-success button_agregar_producto\"> Agregar </button> \r\n" + 
					"     </td> \r\n" + 
					" </tr> \r\n" + 
					"</tfoot> \r\n" + 
					"\r\n" + 
					"</table>"
					+ ""
					+ "<script>\r\n" + 
					"\r\n" + 
					"$(document).ready(function(){\r\n" + 
					"       tablaNominas(); "+muestraGuardar+"\r\n" + 
					"    }); \r\n" + 
					"</script>";
			
			if(dt.size() > 0) {indicador = dt.size();}
			
			
		}else if(medioCobro == 3) {
			
			List<Tarjeta> dt = tarjetaServicio.obtenerTarjetaPeriodo(idPeriodo, anio, mes); 
			
			 response = "<table class=\"table tabla table-hover table-condensed\" id=\"registrosRB\" >\r\n" + 
						"	 <thead>\r\n" + 
						"	<tr>\r\n" + 
						"		<th>#</th>\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\">Nombre</label></th>\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\">Donativo autorizado</label></th>\r\n" + 
						"		<th><label style=\"color:#fff; font-weight: normal;\">Meses autorizados</label></th>								\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\">No. de pagos(cobro)</label></th>\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\">Donativo mensual</label></th>\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\">Saldo</label></th>\r\n" + 
						"		<th><label  style=\"color:#fff; font-weight: normal;\"></label></th>\r\n" + 
						"	</tr>\r\n" + 
						"	</thead>\r\n" + 
						"	<tbody>";
			
			if(dt.size() > 0) {
				muestraGuardar = "$(\"#btn-guardar-cobranza\").show();";
			for(int b = 0; b < dt.size() ;b++) {
				int valor= b+1;
	
				
				double descuentoSugerido = Double.valueOf(dt.get(b).getDonativoTotal()) / dt.get(b).getNumPagos();
				
				response+="<tr  id=\"trRow"+valor+"\" >							\r\n" + 
						"		<td ><label style=\"color: #333333; font-weight: normal;\" id=\"lab"+valor+"\"> "+ valor +"</label><input type=\"hidden\" id=\"numHidde"+valor+"\" value=\""+valor+"\"/></td>\r\n" + 
						"		<td   ><input type=\"text\" onblur='blurNombre("+ valor +")' value='"+dt.get(b).getNombreCompletoDon()+"' onkeyup=\"autocompletarDonanteCobranza(this.id,"+valor+");\" id=\"auto"+valor+"\"> <input type=\"hidden\"  value='"+dt.get(b).getIdDonativo()+"' id=\"idDon"+valor+"\"/> <input type='hidden' id='nomDon" + valor + "'   value='"+dt.get(b).getNombreCompletoDon()+"'  /> </td>\r\n" + 
						"		<td   ><span id=\"donaAuto"+valor+"\" >"+"$ "+formato.format(Double.valueOf(dt.get(b).getDonativoTotal()))+"</span></td>					\r\n" + 
						"		<td   ><span id=\"mesesAuto"+valor+"\" >"+dt.get(b).getNumPagos()+"</span></td>\r\n" + 
						"		<td   ><span id=\"numDescuento"+valor+"\" >"+dt.get(b).getNumDescuento()+"</span></td>					\r\n" + 
						"		<td   ><input value='"+formato.format(dt.get(b).getDonativo())+"' type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual"+valor+"','saldo"+valor+"', "+valor+"); \" onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal"+valor+"\"> </td> <input type='hidden' id='descuentoQuincenalHidden"+valor+"' value='"+formato.format(descuentoSugerido)+"' />	\r\n" + 
						"		<td   ><span id=\"saldo"+valor+"\" >"+"$ "+formato.format(Double.valueOf(dt.get(b).getSaldo()))+"</span><input type='hidden' id='saldoActual"+valor+"' value='"+dt.get(b).getSaldo()+"'/></td>					\r\n" + 
						"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
						"			\r\n" + 
						"	</tr>";
				
		
			}
			}else {
				response+="<tr  id='trRow1' >							\r\n" + 
						"		<td ><label style=\"color: #333333; font-weight: normal;\" id='lab1'> 1</label><input type=\"hidden\" id=\"numHidde1\" value=\"1\"/></td>\r\n" + 
						"		<td   ><input type=\"text\" onblur='blurNombre(1)' onkeyup=\"autocompletarDonanteCobranza(this.id,1);\" id=\"auto1\"> <input type=\"hidden\" id=\"idDon1\"/>   <input type='hidden' id='nomDon1' /></td>\r\n" + 

						"		<td   ><span id=\"donaAuto1\" /></td>					\r\n" + 
						"		<td   ><span id=\"mesesAuto1\" /></td>\r\n" + 
						"		<td   ><span id=\"numDescuento1\" /></td>					\r\n" + 
						"		<td   ><input type=\"text\" onkeyup=\"verificarSaldoCobranza(this.value,'saldoActual1','saldo1', 1); \"  onkeypress=\"return filterFloat(event,this)\" id=\"descuentoQuincenal1\"></td>  <input type='hidden' id='descuentoQuincenalHidden1'/>	\r\n" + 
						"		<td   ><span id=\"saldo1\"/>  <input type='hidden' id='saldoActual1'/> </td>					\r\n" + 
						"		<td   > <button type=\"button\" class=\"btn btn-danger button_eliminar_producto\"> Eliminar </button>  </td>\r\n" + 
						"			\r\n" + 
						"	</tr>";
			}
			
			response +="</tbody>\r\n" + 
					"	 <tfoot> \r\n" + 
					"  <tr> \r\n" + 
					"     <td colspan=\"7\">  </td>\r\n" + 
					"     <td> \r\n" + 
					"        <button type=\"button\" class=\"btn btn-success button_agregar_producto\"> Agregar </button> \r\n" + 
					"     </td> \r\n" + 
					" </tr> \r\n" + 
					"</tfoot> \r\n" + 
					"\r\n" + 
					"</table>"
					+ ""
					+ "<script>\r\n" + 
					"\r\n" + 
					"$(document).ready(function(){\r\n" + 
					"       tablaNominas(); "+muestraGuardar+"\r\n" + 
					"    }); \r\n" + 
					"</script>";
			
			if(dt.size() > 0) {indicador = dt.size();}
			
			
		}
		
		if(indicador > 0) {response+="<input type=\"hidden\" id=\"totalFilas\" value="+indicador+"/>";}else {response+="<input type=\"hidden\" id=\"totalFilas\" value='1'/>";}
		
		return response;

	}
	
	
	@PostMapping(value ="/administracion/guardaCobranza")
	public String guardaCobranza(@RequestParam String cadena ,@RequestParam int idPeriodo, @RequestParam int idQuincena, @RequestParam int medioCobro, @RequestParam int idUsuario, @RequestParam int anio) {
			
		boolean bandera = false;
		String[] datos = cadena.split(",");
	
		for(int i=0;i< datos.length;i++) {
			
			System.out.println(datos[i]+" "+ idQuincena);
			String[] descodificado = datos[i].split("-");
			

			if(medioCobro == 1) {			
				Nomina n = nominaServicio.verificaExiste(Integer.valueOf(descodificado[0]), idPeriodo, idQuincena); 
				if(n !=  null) {					
					if(nominaServicio.actualizaNomina(Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), idUsuario, n.getIdNomina()) == "Done")
						{bandera = true;}
					}else {
					if(nominaServicio.insertNomina(Integer.valueOf(descodificado[0]), Integer.valueOf(descodificado[1]), Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), idQuincena, idUsuario) == "Done")
						{bandera = true;}
					}
			}else if(medioCobro == 2) {
				DepositoTransferencia d = depositoTransferenciaServicio.verificaExiste(Integer.valueOf(descodificado[0]), idPeriodo); 
				if(d !=  null) {					
					if(depositoTransferenciaServicio.actualizaDepositoTransferencia(Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), Date.valueOf(descodificado[4].replace("+", "-")), idUsuario, d.getIdDepositoTransferencia()) == "Done")
						{bandera = true;}
					}else {
					if(depositoTransferenciaServicio.insertDepositoTransferencia(Integer.valueOf(descodificado[0]), Integer.valueOf(descodificado[1]), Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), Date.valueOf(descodificado[4].replace("+", "-")), idUsuario) == "Done")
						{bandera = true;}
					}
			}else if(medioCobro == 3) {
				Tarjeta n = tarjetaServicio.verificaExiste(Integer.valueOf(descodificado[0]), idPeriodo); 
				if(n !=  null) {					
					if(tarjetaServicio.actualizaTarjeta(Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), idUsuario, n.getIdTarjeta()) == "Done")

						{bandera = true;}
					}else {
					if(tarjetaServicio.insertTarjeta(Integer.valueOf(descodificado[0]), Integer.valueOf(descodificado[1]), Double.valueOf(descodificado[2]), Double.valueOf(descodificado[3]), anio, idUsuario) == "Done")
						{bandera = true;}
					}
			}
		
	
		}		
		if(bandera) {	
		return "Done";
		}else {
		return "Error";
		}
	}
	
	@PostMapping(value ="/administracion/eliminaNomina")
	public String eliminaCobranza(@RequestParam int id, @RequestParam int medioCobro, @RequestParam int idPeriodo, @RequestParam int idQuincena) {
	
			if(medioCobro == 1) {
				Nomina n = nominaServicio.verificaExiste(id, idPeriodo, idQuincena); 
				if(n !=  null) {	
				nominaServicio.eliminaNomina(n.getIdNomina());
				}
			}		
				
		return "Done";
	}
	
	
	@PostMapping(value ="/administracion/eliminaDepositoTransferencia")
	public String eliminaDepositoTransferencia(@RequestParam int id, @RequestParam int idPeriodo) {
	
		boolean bandera = false;
				DepositoTransferencia dt = depositoTransferenciaServicio.verificaExiste(id, idPeriodo); 
				if(dt !=  null) {	
					depositoTransferenciaServicio.eliminaDepositoTransferencia(dt.getIdDepositoTransferencia());
					bandera = true;
				}
			
		
				if(bandera) {	
					return "Done";
					}else {
					return "Error";
					}
	}
	
	
	@PostMapping(value ="/administracion/eliminaTarjeta")
	public String eliminaTarjeta(@RequestParam int id, @RequestParam int idPeriodo) {
	
		boolean bandera = false;
				Tarjeta t = tarjetaServicio.verificaExiste(id, idPeriodo); 
				if(t !=  null) {	
					tarjetaServicio.eliminaTarjeta(t.getIdTarjeta());
					bandera = true;
				}
			
		
				if(bandera) {	
					return "Done";
					}else {
					return "Error";
					}
	}
	
	
	

}
