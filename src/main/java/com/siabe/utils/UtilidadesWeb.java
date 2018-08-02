package com.siabe.utils;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.siabe.modelo.PermisosMenu;
import com.siabe.servicio.PermisosMenuServicio;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class UtilidadesWeb {
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
    
    
    public boolean direccionActual(int idUsuario) {
    	boolean registrado = false;
    	
    	UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest(); 
	    List<PermisosMenu> pm = permisosMenuServicio.todosPermisosMenu(idUsuario);
	        
	        for(int i =0;i < pm.size(); i++) {
	    //    	System.out.println("link "+builder.buildAndExpand().getPath());
	        //	System.out.println("recorrido "+pm.get(i).getUrl().equals(builder.buildAndExpand().getPath()));
	        	if(builder.buildAndExpand().getPath().equals("/inicio") || pm.get(i).getUrl().equals(builder.buildAndExpand().getPath())) {
	        		
	        		registrado = true;
	        	}
	        }
	   //     System.out.println("recorrido "+registrado);
	  return registrado;
    }
    
    public void exportarPDF(JasperPrint jasperPrint, HttpServletResponse response, ServletOutputStream nombre) throws IOException, JRException {
    	JRPdfExporter exporter = new JRPdfExporter();

    	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(nombre));
    	 
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

    }
    
    
    public void exportarExcel(JasperPrint jasperPrint, HttpServletResponse response, ServletOutputStream nombre) throws IOException, JRException {
    	JRXlsxExporter exporter = new JRXlsxExporter();
    	  
    	exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
    	exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(nombre));
    	SimpleXlsxReportConfiguration reportConfig  = new SimpleXlsxReportConfiguration();
    	reportConfig.setSheetNames(new String[] { "Employee Data" });
    	 
    	exporter.setConfiguration(reportConfig);
    	
    	response.setContentType("application/x-ms-excel");
    	exporter.exportReport();
    }
}
