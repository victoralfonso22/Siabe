package com.siabe.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.siabe.modelo.Beneficiarios;
import com.siabe.modelo.Nomina;
import com.siabe.modelo.Periodo;
import com.siabe.modelo.TiempoPromedio;
import com.siabe.reportesDinamicos.ReporteDinamicoBeneficiariosGeneral;
import com.siabe.reportesDinamicos.ReporteDinamicoDonantesGeneral;
import com.siabe.servicio.TiempoPromedioServicio;
import com.siabe.utils.UtilidadesWeb;
import com.siabe.servicio.BeneficiariosServicio;
import com.siabe.servicio.PeriodoServicio;
import com.siabe.servicio.NominaServicio;
import com.siabe.servicio.DepositoTransferenciaServicio;
import com.siabe.servicio.TarjetaServicio;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;

import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

@Controller
public class ControladorJasperReportes {

	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;
	
	@Autowired
	private PeriodoServicio periodoServicio;
	
	@Autowired
	private BeneficiariosServicio beneficiariosServicio;
	
	@Autowired
	private UtilidadesWeb utilidadesWeb;
	
	@Autowired
	private ReporteDinamicoBeneficiariosGeneral reporteDinamicoBeneficiariosGeneral;
	
	@Autowired
	private ReporteDinamicoDonantesGeneral reporteDinamicoDonantesGeneral;
	
	@Autowired
	private NominaServicio nominaServicio;
	
	@Autowired
	private DepositoTransferenciaServicio depositoTransferenciaServicio;
	
	@Autowired
	private TarjetaServicio tarjetaServicio;

	/*********************TIEMPOS PROMEDIO**************************************/
	@RequestMapping(value = "/catalogos/reporteTiempoPromedio")
	public void reportesTiemposPromedio(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type, @RequestParam("idRegion") int idRegion,@RequestParam("inputBusca") String inputBusca, @RequestParam("carrera") int carrera,
			@RequestParam("facultad") int facultad, @RequestParam("area") int area)
					throws Exception {

		System.out.println("Escribe type " + type);
		
		//Periodo p = periodoServicio.regresaPeriodo();
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}
		
//data source
		JRDataSource dataSource = new JRBeanCollectionDataSource(tiempoPromedioServicio.todosTiemposPromedioRegionInput( idRegion, inputBusca,carrera,facultad,area));
		
//compile jrxml template and get report
		JasperReport report;
		InputStream stream = getClass().getResourceAsStream("/reportes/tiempoPromedio/tiempoPromedio.jrxml");
		report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		ServletContext context = request.getServletContext();
	
		//parameters.put("periodo", p.getNombre());
		
		if (!type.equals("pdf")) {
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		}

//fill the report with data source objects
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

		if (type.equals("pdf")) {
			
			reportePDF(jasperPrint, response);
			

		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(jasperPrint, response,String.valueOf(idRegion));
		}else if (type.equals("html")) {
			reporteHTML(jasperPrint, response);
		}

	}
	
	/*********************************************************************************BENEFICIARIOS*************************************************************************/
	
	@RequestMapping(value = "/reportes/reporteBeneficiariosTodos")
	public void reporteBeneficiariosTodos(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("valores") String valores ,@RequestParam("type") String type) throws Exception {

		reporteDinamicoBeneficiariosGeneral.cadena = valores;
		
		reporteDinamicoBeneficiariosGeneral.testReport();

		System.out.println("Escribe type " + type);
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}

				
		if (type.equals("pdf")) {
			reportePDF(reporteDinamicoBeneficiariosGeneral.jp, response);
		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(reporteDinamicoBeneficiariosGeneral.jp, response,"Beneficiarios todos");
		}else if (type.equals("html")) {
			reporteHTML(reporteDinamicoBeneficiariosGeneral.jp, response);
		}

	}
	
	
	
	@RequestMapping(value = "/reportes/reporteBeneficiarioNombre")
	public void reporteBeneficiarioNombre(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("type") String type, @RequestParam("idBeneficiario") int idBeneficiario) throws Exception {

		System.out.println("Escribe type " + type);
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}
		
//data source
		//JRDataSource dataSource = new JRBeanCollectionDataSource(beneficiariosServicio.beneficiario(idBeneficiario));
		
//compile jrxml template and get report
		Beneficiarios beneficiario = beneficiariosServicio.beneficiario(idBeneficiario);
		
		int tipoBeca=beneficiario.getIdTipoBeca();
		
		JasperReport report;
		
		InputStream stream;
		
		if(tipoBeca == 4) {
			 stream = getClass().getResourceAsStream("/reportes/beneficiarios/beneficiarioNombreDeportivas.jrxml");	
		}else {
		     stream = getClass().getResourceAsStream("/reportes/beneficiarios/beneficiarioNombreBecas.jrxml");
		}
		
		report = JasperCompileManager.compileReport(stream);
		
		
		ObjectMapper oMapper = new ObjectMapper();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		//Date fechaNacimientoFormat = formatter.parse(beneficiario.getFechaNacimiento().toString());
		
		Map<String, Object> map = oMapper.convertValue(beneficiario, Map.class);
		
		Map<String, Object> parameters = new HashMap<>();
		
		ServletContext context = request.getServletContext();
		
		TiempoPromedio tiempoPromedio = tiempoPromedioServicio.regresaTiempoPromedio(beneficiario.getIdCarrera());
		
		
		
		parameters.put("montoBecaFormat",utilidadesWeb.formatoMoneda(beneficiario.getMontoBeca()));
		
		if(beneficiario.getFechaNacimiento() != null) {
		parameters.put("fechaNacimientoFormat",utilidadesWeb.MillisToDate(beneficiario.getFechaNacimiento().getTime()));
		}else {
			parameters.put("fechaNacimientoFormat","");
		}
		
		parameters.put("ingresosFamiliaresFormat",utilidadesWeb.formatoMoneda(beneficiario.getIngresosFamiliares()));
		
		if(tipoBeca != 4) {
		parameters.put("periodoPromedio",tiempoPromedio.getPeriodoPromedio());
		
		int periodoRebasa = tiempoPromedio.getPeriodoPromedio() - beneficiario.getPeriodoActual();
		
		parameters.put("periodoRebasa",periodoRebasa);	
		
		}else {
		parameters.put("fechaIngresoFormat",utilidadesWeb.MillisToDate(beneficiario.getFechaIngEscDep().getTime()));
		}
		
		map.forEach(
				(k,v) -> parameters.put(k, v)				
				);
		 parameters.put("beneficiario", beneficiariosServicio.beneficiario(idBeneficiario));
		//if (!type.equals("pdf")) {
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	//	}

//fill the report with data source objects
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

		if (type.equals("pdf")) {
			reportePDF(jasperPrint, response);
		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(jasperPrint, response,"Beneficiarios");
		}else if (type.equals("html")) {
			reporteHTML(jasperPrint, response);
		}

		

	}
	
	@RequestMapping(value = "/reportes/reporteBeneficiariosGeneral")
	public void reporteBeneficiariosGeneral(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("valores") String valores , @RequestParam("idTipoBeca") int idTipoBeca ,@RequestParam("idPeriodo") int idPeriodo ,@RequestParam("idRegion") int idRegion ,
			@RequestParam("type") String type, @RequestParam("tipoB") String tipoB,@RequestParam("contadores") ArrayList<Integer> contadores) throws Exception {
		reporteDinamicoBeneficiariosGeneral.cadena = valores;
		
		reporteDinamicoBeneficiariosGeneral.idTipoBeca = idTipoBeca;
		reporteDinamicoBeneficiariosGeneral.idPeriodo = idPeriodo;
		reporteDinamicoBeneficiariosGeneral.idRegion = idRegion;
		reporteDinamicoBeneficiariosGeneral.tipoB = tipoB;
		reporteDinamicoBeneficiariosGeneral.type = type;
		reporteDinamicoBeneficiariosGeneral.contadores = contadores;
		
		reporteDinamicoBeneficiariosGeneral.testReport();

		//System.out.println("Escribe type " + type);
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}

				
		if (type.equals("pdf")) {
			reportePDF(reporteDinamicoBeneficiariosGeneral.jp, response);
		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(reporteDinamicoBeneficiariosGeneral.jp, response,"Beneficiarios");
		}else if (type.equals("html")) {
			reporteHTML(reporteDinamicoBeneficiariosGeneral.jp, response);
		}

	}
	
	
	/*********************************************************************************DONANTES*************************************************************************/
	
	@RequestMapping(value = "/reportes/reporteDonantesGeneral")
	public void reporteDonantesGeneral(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("valores") String valores ,@RequestParam("idPeriodo") int idPeriodo ,@RequestParam("idRegion") int idRegion ,
			@RequestParam("type") String type,@RequestParam("contadores") ArrayList<Integer> contadores) throws Exception {
		reporteDinamicoDonantesGeneral.cadena = valores;		
	
		reporteDinamicoDonantesGeneral.idPeriodo = idPeriodo;
		reporteDinamicoDonantesGeneral.idRegion = idRegion;
		reporteDinamicoDonantesGeneral.type = type;
		reporteDinamicoDonantesGeneral.contadores = contadores;
		
		
		reporteDinamicoDonantesGeneral.testReport();

		System.out.println("Escribe type " + type);
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}

				
		if (type.equals("pdf")) {
			reportePDF(reporteDinamicoDonantesGeneral.jp, response);
		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(reporteDinamicoDonantesGeneral.jp, response,"Donantes");
		}else if (type.equals("html")) {
			reporteHTML(reporteDinamicoDonantesGeneral.jp, response);
		}

	}
	
	
	/***********************************************GENERACION DEL REPORTE EN PDF*************************************************************************************/
	public void reportePDF(JasperPrint jasperPrint, HttpServletResponse response)  throws Exception{
		System.err.println("PDF entre");
		JRPdfExporter exporter = new JRPdfExporter();
		long start = System.currentTimeMillis();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		SimplePdfReportConfiguration reportConfig = new SimplePdfReportConfiguration();
		reportConfig.setSizePageToContent(true);
		reportConfig.setForceLineBreakPolicy(false);

		SimplePdfExporterConfiguration exportConfig = new SimplePdfExporterConfiguration();
//exportConfig.setMetadataAuthor("baeldung");

//exportConfig.setEncrypted(true);
//exportConfig.setAllowedPermissionsHint("PRINTING");
		exportConfig.setCreatingBatchModeBookmarks(true);

//exporter.setConfiguration(reportConfig);
		exporter.setConfiguration(exportConfig);

		response.setContentType("application/pdf");
//response.setHeader("Content-Disposition", "attachment; filename=\"Archivo.pdf\""); 
		exporter.exportReport();

		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}
	
	/*********************************************GENERACION DEL REPORTE EN EXCEL************************************************************************************************/
	public void reporteExcel(JasperPrint jasperPrint, HttpServletResponse response, String nombreHoja)  throws Exception{
		JRXlsxExporter exporter = new JRXlsxExporter();		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
		SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
		reportConfig.setSheetNames(new String[] { nombreHoja });
		reportConfig.isRemoveEmptySpaceBetweenColumns();
		reportConfig.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);		
		
		exporter.setConfiguration(reportConfig);

		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		exporter.exportReport();
	}
	
	/*********************************************GENERACION DEL REPORTE EN HTML************************************************************************************************/
	public void reporteHTML(JasperPrint jasperPrint, HttpServletResponse response)  throws Exception{
	//	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HtmlExporter exporter = new HtmlExporter();
		
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getOutputStream()));
		SimpleHtmlReportConfiguration reportConfig = new SimpleHtmlReportConfiguration();
		
		reportConfig.setSizeUnit(HtmlSizeUnitEnum.POINT);		
		reportConfig.setRemoveEmptySpaceBetweenRows(true);
		reportConfig.isAccessibleHtml();
		reportConfig.getProgressMonitor();
		reportConfig.isRemoveEmptySpaceBetweenRows();
		
		
		
		exporter.setConfiguration(reportConfig);

		response.setContentType("text/html");
		response.setHeader("Content-disposition", "inline");

		//response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		exporter.exportReport();
		//return baos.toByteArray();
	}
	
	/*********************NOMINA**************************************/
	@RequestMapping(value = "/reportes/reporteNominaGeneral")
	public void reportesCobranzaNomina(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("idPeriodo") int idPeriodo, @RequestParam("idQuincena") int idQuincena, @RequestParam("type") String type)
					throws Exception {

		System.out.println("Escribe type " + type);
		
		//Periodo p = periodoServicio.regresaPeriodo();
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}
		
//data source
		JRDataSource dataSource = new JRBeanCollectionDataSource(nominaServicio.obtenerNominasPeriodo(idPeriodo, idQuincena) );
		
//compile jrxml template and get report
		JasperReport report;
		InputStream stream = getClass().getResourceAsStream("/reportes/donantes/nominaGeneral.jrxml");
		report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		ServletContext context = request.getServletContext();
	
		//parameters.put("periodo", p.getNombre());
		
		if (!type.equals("pdf")) {
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		}

//fill the report with data source objects
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

		if (type.equals("pdf")) {
			
			reportePDF(jasperPrint, response);
			

		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(jasperPrint, response,"Nómina");
		}else if (type.equals("html")) {
			reporteHTML(jasperPrint, response);
		}

	}
	
	

	
	/*********************DEPOSITO O TRANSFERENCIA**************************************/
	@RequestMapping(value = "/reportes/reporteDepTraGeneral")
	public void reportesCobranzaDepositoTransferencia(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("idPeriodo") int idPeriodo, @RequestParam("type") String type)
					throws Exception {

		System.out.println("Escribe type " + type);
		
		//Periodo p = periodoServicio.regresaPeriodo();
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}
		
//data source
		JRDataSource dataSource = new JRBeanCollectionDataSource(depositoTransferenciaServicio.obtenerDepositoTransferenciaPeriodo(idPeriodo));
		
//compile jrxml template and get report
		JasperReport report;
		InputStream stream = getClass().getResourceAsStream("/reportes/donantes/depositoTransferenciaGeneral.jrxml");
		report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		ServletContext context = request.getServletContext();
	
		//parameters.put("periodo", p.getNombre());
		
		if (!type.equals("pdf")) {
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		}

//fill the report with data source objects
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

		if (type.equals("pdf")) {
			
			reportePDF(jasperPrint, response);
			

		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(jasperPrint, response,"Depósito o transferencia");
		}else if (type.equals("html")) {
			reporteHTML(jasperPrint, response);
		}

	}
	
	
	/*********************TARJETA**************************************/
	@RequestMapping(value = "/reportes/reporteTarjetaGeneral")
	public void reportesCobranzaTarjeta(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("idPeriodo") int idPeriodo, @RequestParam("anio") int anio, @RequestParam("mes") int mes, @RequestParam("type") String type)
					throws Exception {

		System.out.println("Escribe type " + type);
		
		//Periodo p = periodoServicio.regresaPeriodo();
		
		
		if(!type.equals("html")) {
		response.setContentType("application/" + type);
		}
		
//data source
		JRDataSource dataSource = new JRBeanCollectionDataSource(tarjetaServicio.obtenerTarjetaPeriodo(idPeriodo, anio, mes));
		
//compile jrxml template and get report
		JasperReport report;
		InputStream stream = getClass().getResourceAsStream("/reportes/donantes/tarjetaGeneral.jrxml");
		report = JasperCompileManager.compileReport(stream);

		Map<String, Object> parameters = new HashMap<>();
		ServletContext context = request.getServletContext();
	
		//parameters.put("periodo", p.getNombre());
		
		if (!type.equals("pdf")) {
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		}

//fill the report with data source objects
		JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

		if (type.equals("pdf")) {
			
			reportePDF(jasperPrint, response);
			

		} else if (type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			reporteExcel(jasperPrint, response,"Tarjeta");
		}else if (type.equals("html")) {
			reporteHTML(jasperPrint, response);
		}

	}
	
	
}
