package com.siabe.controller;




import java.io.InputStream;
import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.siabe.servicio.TiempoPromedioServicio;




import net.sf.jasperreports.engine.JRDataSource;

import net.sf.jasperreports.engine.JasperCompileManager;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Controller
public class ControladorReportes {
	
	@Autowired
	private TiempoPromedioServicio tiempoPromedioServicio;

	@RequestMapping(value = "/catalogos/reporteTiempoPromedio")
	public void generatePDFJasperChart(HttpServletRequest request, HttpServletResponse response, @RequestParam("type") String type) throws Exception {
		
		System.out.println("Escribe type "+type);
		
		response.setContentType("application/"+type);
		long start = System.currentTimeMillis();
//data source
JRDataSource dataSource = new JRBeanCollectionDataSource(tiempoPromedioServicio.todosTiemposPromedio());
//compile jrxml template and get report
JasperReport report;
InputStream stream = getClass().getResourceAsStream("/reportes/tiempoPromedio.jrxml");
report = JasperCompileManager.compileReport(stream);

Map<String, Object> parameters = new HashMap<>();
parameters.put("periodo", "Employee Report");

//fill the report with data source objects
JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

if(type.equals("pdf")) {
	
	System.err.println("PDF entre");
JRPdfExporter exporter = new JRPdfExporter();

exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
 
SimplePdfReportConfiguration reportConfig  = new SimplePdfReportConfiguration();
reportConfig.setSizePageToContent(true);
reportConfig.setForceLineBreakPolicy(false);

 
SimplePdfExporterConfiguration exportConfig  = new SimplePdfExporterConfiguration();
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

}else if(type.equals("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
	JRXlsxExporter exporter = new JRXlsxExporter();
	  
	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
	SimpleXlsxReportConfiguration reportConfig  = new SimpleXlsxReportConfiguration();
	reportConfig.setSheetNames(new String[] { "Employee Data" });
	 
	exporter.setConfiguration(reportConfig);
	
	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	exporter.exportReport();
}

	 
	/*	String sourceFileName = "/reportes/tiempoPromedio.jrxml";
		System.out.println(sourceFileName);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ReportTitle", "Jasper Demo");
		parameters.put("Author", "Prepared By jCombat");
		try {
			System.out.println("Start compiling!!! ...");
			JasperCompileManager.compileReportToFile(sourceFileName);
			System.out.println("Done compiling!!! ...");
			sourceFileName = "D://Documents/jasper_report_template.jasper";
			
			List<TiempoPromedio> dataList = tiempoPromedioServicio.todosTiemposPromedio();
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					dataList);
			JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(sourceFileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(report,
					parameters, beanColDataSource);
			if (jasperPrint != null) {
				byte[] pdfReport = JasperExportManager
						.exportReportToPdf(jasperPrint);
				response.reset();
				response.setContentType("application/pdf");
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Cache-Control", "private");
				response.setHeader("Pragma", "no-store");
				response.setContentLength(pdfReport.length);
				response.getOutputStream().write(pdfReport);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch (JRException e) {
			e.printStackTrace();
		}*/
	}
	
}
