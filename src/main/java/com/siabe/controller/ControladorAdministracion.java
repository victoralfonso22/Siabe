package com.siabe.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Donativos;
import com.siabe.modelo.RelacionDonantesBeneficiarios;
import com.siabe.modelo.Usuario;
import com.siabe.servicio.UsuarioServicio;

import com.siabe.utils.UtilidadesWeb;

import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.DonativosServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.CampanaServicio;
import com.siabe.servicio.RegionesServicio;
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.TipoBecaServicio;
import com.siabe.servicio.QuincenasServicio;




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
	
	@Autowired
	private CampanaServicio campaniaServicio;
	
	@Autowired
	private RegionesServicio regionesServicio;
	
	@Autowired
	private TipoBecaServicio tipoBecaServicio;
	
	@Autowired
	private QuincenasServicio quincenasServicio;
	
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

		model.addAttribute("periodos", periodoServicio.todosPeridosActivosNoDeportiva());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/comprobanteFiscal";
	}
	
	
	@GetMapping(value = "/administracion/asignacion")
	public String asignacion(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPeridosActivos());
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/asignacion";
	}
	
	@GetMapping(value = "/administracion/refrendoDonantes")
	public String refrendoDonantes(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPeridosActivosNoDeportiva());
		model.addAttribute("tipoBecas",tipoBecaServicio.todosTipoBeca());
		model.addAttribute("regiones", regionesServicio.todosRegiones());
		
		
		return "/administracion/refrendoDonantes";
	}
	
	@GetMapping(value = "/administracion/refrendoBeneficiarios")
	public String refrendoBeneficiarios(Model model, Principal principal) throws IOException {

		model.addAttribute("periodos", periodoServicio.todosPeridosActivos());
		model.addAttribute("tipoBecas", tipoBecaServicio.todosTipoBeca());
		model.addAttribute("regiones", regionesServicio.todosRegiones());
		
		return "/administracion/refrendoBeneficiarios";
	}
	
	@GetMapping(value = "/administracion/cobranza")
	public String cobranza(Model model, Principal principal) throws IOException {

		model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
		model.addAttribute("periodos",  periodoServicio.todosPeridosActivosNoDeportiva());
		
		return "/administracion/cobranza";
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
				if(donativosServicio.donativoDonanteBeneficiario(d.get(i).getIdDonativo(), id) > 0) {
				//	relacionDonantesBeneficiarios.setClase("tdSencilloPermiso");
					relacionDonantesBeneficiarios.setClase(true);
				}else {
					relacionDonantesBeneficiarios.setClase(false);
				}
				r.add(relacionDonantesBeneficiarios);
			}
			
			
		
			model.addAttribute("registrosTab", r);
			model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
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
				if(donativosServicio.donativoDonanteBeneficiario(id, b.get(i).getIdBeneficiario()) > 0) {
					relacionDonantesBeneficiarios.setClase(true);
				}else {
					relacionDonantesBeneficiarios.setClase(false);
				}
				r.add(relacionDonantesBeneficiarios);
			}
			
			model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
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
	
	/***********************refrendo de donantes*********************/
	
	@RequestMapping("/administracion/actualizaRegionRD")
	public String actualizaRegionRD(Model model, Principal principal,@RequestParam int idPeriodo) {		
		
		model.addAttribute("regiones", regionesServicio.regresaRegionesPeriodo(idPeriodo));
		
		if(principal == null) {
		return "/login";	
		}else {			
				return "/administracion/refrendoDonantes :: #idRegionRD";	
		
		}
	}
	
	
	@RequestMapping("/administracion/actualizaTablaRD")
	public String actualizaTablaRD(Model model, Principal principal,@RequestParam int idPeriodo,@RequestParam int idRegion,@RequestParam int idCampania) {		
		
		
		model.addAttribute("registrosTab", donativosServicio.refrendo(idPeriodo, idRegion, idCampania));
		model.addAttribute("quincenas", quincenasServicio.todasQuincenas());
		
		if(donativosServicio.refrendo(idPeriodo, idRegion, idCampania).isEmpty()) {
		model.addAttribute("muestraRegistro", 0);

		}else {

			model.addAttribute("muestraRegistro", 1);	
		}

		if(principal == null) {
		return "/login";	
		}else {			
				return "/administracion/refrendoDonantes :: #registrosRD";	
		
		}
	}
	
	@RequestMapping("/administracion/actualizaCampaniasRD")
	public String actualizaCampaniasRD(Model model, Principal principal,@RequestParam int idRegion) {		
		
		model.addAttribute("campanas", campaniaServicio.todosCampanaRegion(idRegion));
		
		if(principal == null) {
		return "/login";	
		}else {			
				return "/administracion/refrendoDonantes :: #idCampaniaRD";	
		
		}
	}
	
	
/***********************refrendo de beneficiarios*********************/
	
	@RequestMapping("/administracion/actualizaRegionRB")
	public String actualizaRegionRB(Model model, Principal principal,@RequestParam int idPeriodo) {		
		
		model.addAttribute("regiones", regionesServicio.regresaRegionesPeriodo(idPeriodo));
		
		if(principal == null) {
		return "/login";	
		}else {			
				return "/administracion/refrendoDonantes :: #idRegionRD";	
		
		}
	}
	
	
	@RequestMapping("/administracion/actualizaTablaRB")
	public String actualizaTablaRB(Model model, Principal principal,@RequestParam int idPeriodo,@RequestParam int idRegion,@RequestParam int idTipoBeca) {		
		
		
		model.addAttribute("registrosTab", beneficiariosServicio.refrendo(idPeriodo, idRegion, idTipoBeca));
		
		if(beneficiariosServicio.refrendo(idPeriodo, idRegion, idTipoBeca).isEmpty()) {
		model.addAttribute("muestraRegistro", 0);

		}else {

			model.addAttribute("muestraRegistro", 1);	
		}

		if(principal == null) {
		return "/login";	
		}else {			
				return "/administracion/refrendoBeneficiarios :: #registrosRB";	
		
		}
	}
	

	
	@RequestMapping("/administracion/periodosMayores")
	public String periodosMayores(Model model, Principal principal,@RequestParam int idPeriodo) {

		model.addAttribute("periodosM", periodoServicio.todosPeridoMayoresNoDeportiva(idPeriodo));
		
	//	System.out.println(periodoServicio.todosPeridoMayores(idPeriodo).get(0).getNombre());
		if(principal == null) {
			return "/login";	
			}else {			
				return "/administracion/refrendoDonantes :: #idPeriodoDestinoRD";
			
			}

		
	}
	
	
	@GetMapping(value = "/administracion/excelBeneficiarios")
	public String excelBeneficiarios(Model model, Principal principal) throws IOException {

		//model.addAttribute("registrosTab", "" );
	//	model.addAttribute("lista", archivoStorageServicio.listarArchivos().size());
		
		return "/administracion/excelBeneficiarios";
	}
	
	@RequestMapping("/administracion/import")
		public String mapReapExcelDatatoDB(Model model, Principal principal,@RequestParam("file") MultipartFile reapExcelDataFile, @RequestParam int idUsuario) throws IOException {

		   List<Beneficiarios> tempListFUVUV = new ArrayList<Beneficiarios>();
		  
		   System.out.println(reapExcelDataFile.getInputStream().available());
		    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		    XSSFSheet worksheet = workbook.getSheetAt(0);
		    
		    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
		    	Beneficiarios tempStudent = new Beneficiarios();

		        XSSFRow row = worksheet.getRow(i);

		        tempStudent.setIdBeneficiario((int) row.getCell(0).getNumericCellValue());
		        tempStudent.setIdTipoBeca((int)row.getCell(1).getNumericCellValue());
		        tempStudent.setMatricula(row.getCell(2).getStringCellValue());
		        tempStudent.setNombre(row.getCell(3).getStringCellValue());
		        tempStudent.setApellidoPaterno(row.getCell(4).getStringCellValue());
		        tempStudent.setApellidoMaterno(row.getCell(5).getStringCellValue());
		        tempStudent.setTipoBecario(row.getCell(6).getStringCellValue());
		        tempStudent.setAdscripcion(row.getCell(7).getStringCellValue());
		        tempStudent.setIdRegion((int) row.getCell(8).getNumericCellValue());
		        tempStudent.setIdCarrera((int) row.getCell(9).getNumericCellValue());
		        tempStudent.setPeriodoActual((int) row.getCell(10).getNumericCellValue());
		        tempStudent.setPromedioGeneral((double) row.getCell(11).getNumericCellValue());
		        tempStudent.setEdad((int) row.getCell(12).getNumericCellValue());
		        tempStudent.setGenero( row.getCell(13).getStringCellValue());
		        tempStudent.setLenguaIndigena(row.getCell(14).getStringCellValue());
		        tempStudent.setDiscapacidad(row.getCell(15).getStringCellValue());
		        tempStudent.setEstadoCivil(row.getCell(16).getStringCellValue());
		        tempStudent.setLugarNacimiento(row.getCell(17).getStringCellValue());
		        tempStudent.setFechaNacimiento((Date) row.getCell(18).getDateCellValue());
		        tempStudent.setBreveHistoria(row.getCell(19).getStringCellValue());
		        tempStudent.setIntegrantesFamiliares((int) row.getCell(20).getNumericCellValue());
		        tempStudent.setIngresosFamiliares((double) row.getCell(21).getNumericCellValue());
		        tempStudent.setCalleVivFam(row.getCell(22).getStringCellValue());
		        tempStudent.setNumEVivFam(row.getCell(23).getStringCellValue());
		        tempStudent.setNumIVivFam(row.getCell(24).getStringCellValue());
		        tempStudent.setColVivFam(row.getCell(25).getStringCellValue());
		        tempStudent.setLocVivFam(row.getCell(26).getStringCellValue());
		        tempStudent.setMunVivFam(row.getCell(27).getStringCellValue());
		        tempStudent.setEdoVivFam(row.getCell(28).getStringCellValue());
		        tempStudent.setCpVivFam((int) row.getCell(29).getNumericCellValue());
		        tempStudent.setEnlaceMaps(row.getCell(30).getStringCellValue());
		        tempStudent.setMismoVivFam((int) row.getCell(31).getNumericCellValue());
		        tempStudent.setCalleEst(row.getCell(32).getStringCellValue());
		        tempStudent.setNumEEst(row.getCell(33).getStringCellValue());
		        tempStudent.setNumIEst(row.getCell(34).getStringCellValue());
		        tempStudent.setColEst(row.getCell(35).getStringCellValue());
		        tempStudent.setLocEst(row.getCell(36).getStringCellValue());
		        tempStudent.setMunEst(row.getCell(37).getStringCellValue());
		        tempStudent.setEdoEst(row.getCell(38).getStringCellValue());
		        tempStudent.setCpEst(row.getCell(39).getStringCellValue());
		        tempStudent.setCelular(row.getCell(40).getStringCellValue());
		        tempStudent.setTelDomicilio(row.getCell(41).getStringCellValue());
		        tempStudent.setTipoTelRef(row.getCell(42).getStringCellValue());
		        tempStudent.setNumTelRef(row.getCell(43).getStringCellValue());
		        tempStudent.setParentescoRef(row.getCell(44).getStringCellValue());
		        tempStudent.setObservacionesRef(row.getCell(45).getStringCellValue());
		        tempStudent.setEmail(row.getCell(46).getStringCellValue());
		        tempStudent.setFacebook(row.getCell(47).getStringCellValue());
		        tempStudent.setFacebook2(row.getCell(48).getStringCellValue());
		        tempStudent.setFacebook3(row.getCell(49).getStringCellValue());
		        tempStudent.setFormaPago((int)row.getCell(50).getNumericCellValue());
		        tempStudent.setBanco(row.getCell(51).getStringCellValue());
		        tempStudent.setCuentaDeposito(row.getCell(52).getStringCellValue());
		        tempStudent.setTarjetaDeposito(row.getCell(53).getStringCellValue());
		        tempStudent.setClaveReferenciado(row.getCell(54).getStringCellValue());
		        tempStudent.setVigenciaReferenciado(row.getCell(55).getStringCellValue());
		        tempStudent.setMontoBeca((double) row.getCell(56).getNumericCellValue());
		        tempStudent.setObservaciones(row.getCell(57).getStringCellValue());
		        
		            tempListFUVUV.add(tempStudent);   
		            
		        System.out.println(tempListFUVUV.get(0).getIdBenefactor());
		        System.out.println(tempListFUVUV.get(0).getNombre());
		    	
		    }
		    
		    for(int x=0; x <= tempListFUVUV.size();x++) {
		    
		    beneficiariosServicio.insertaBeneficiarioBeca(tempListFUVUV.get(x).getIdPeriodo(), tempListFUVUV.get(x).getIdTipoBeca(), tempListFUVUV.get(x).getMatricula(), 
		    		tempListFUVUV.get(x).getNombre(), tempListFUVUV.get(x).getApellidoPaterno(), tempListFUVUV.get(x).getApellidoMaterno(), 1, "", tempListFUVUV.get(x).getTipoBecario(), 
		    		tempListFUVUV.get(x).getAdscripcion(), tempListFUVUV.get(x).getIdRegion(), tempListFUVUV.get(x).getIdCarrera(), tempListFUVUV.get(x).getPeriodoActual(), 
		    		tempListFUVUV.get(x).getPromedioGeneral(), tempListFUVUV.get(x).getEdad(), tempListFUVUV.get(x).getGenero(), tempListFUVUV.get(x).getLenguaIndigena(),
		    		tempListFUVUV.get(x).getDiscapacidad(), tempListFUVUV.get(x).getEstadoCivil(), tempListFUVUV.get(x).getLugarNacimiento(), tempListFUVUV.get(x).getFechaNacimiento(), 
		    		tempListFUVUV.get(x).getBreveHistoria(), tempListFUVUV.get(x).getIntegrantesFamiliares(), tempListFUVUV.get(x).getIngresosFamiliares(), tempListFUVUV.get(x).getCalleVivFam(),
		    		tempListFUVUV.get(x).getNumEVivFam(), tempListFUVUV.get(x).getNumIVivFam(), tempListFUVUV.get(x).getColVivFam(), tempListFUVUV.get(x).getLocVivFam(), 
		    		tempListFUVUV.get(x).getMunVivFam(), tempListFUVUV.get(x).getEdoVivFam(), tempListFUVUV.get(x).getCpVivFam(), tempListFUVUV.get(x).getEnlaceMaps(),
		    		tempListFUVUV.get(x).getMismoVivFam(), tempListFUVUV.get(x).getCalleEst(), tempListFUVUV.get(x).getNumEEst(), tempListFUVUV.get(x).getNumIEst(), 
		    		tempListFUVUV.get(x).getColEst(), tempListFUVUV.get(x).getLocEst(),tempListFUVUV.get(x).getMunEst(), tempListFUVUV.get(x).getEdoEst(),tempListFUVUV.get(x).getCpEst(), 
		    		tempListFUVUV.get(x).getCelular(), tempListFUVUV.get(x).getTelDomicilio(), tempListFUVUV.get(x).getTipoTelRef(), tempListFUVUV.get(x).getNumTelRef(),tempListFUVUV.get(x).getParentescoRef(), 
		    		tempListFUVUV.get(x).getObservacionesRef(), tempListFUVUV.get(x).getEmail(), tempListFUVUV.get(x).getFacebook(), tempListFUVUV.get(x).getFacebook2(), tempListFUVUV.get(x).getFacebook3(), 
		    		tempListFUVUV.get(x).getFormaPago(), tempListFUVUV.get(x).getBanco(), tempListFUVUV.get(x).getCuentaDeposito(), tempListFUVUV.get(x).getTarjetaDeposito(), 
		    		tempListFUVUV.get(x).getClaveReferenciado(), tempListFUVUV.get(x).getVigenciaReferenciado(), tempListFUVUV.get(x).getMontoBeca(), "", tempListFUVUV.get(x).getObservaciones(), "0", idUsuario);
		    }
		    
		   
		    
		    model.addAttribute("registrosTab", tempListFUVUV);
	
		    return "/administracion/excelBeneficiarios :: #registros";	
		    
		}

}
