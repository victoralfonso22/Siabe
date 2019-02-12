package com.siabe.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.siabe.modelo.PermisosMenu;
import com.siabe.servicio.PermisosMenuServicio;
import com.siabe.servicio.UsuarioServicio;



@Service
public class UtilidadesWeb {
	
	@Autowired
	private PermisosMenuServicio permisosMenuServicio;
	
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@Autowired 
	private JavaMailSender sender;
	
	
	
	private static final String CHAR_LIST = 
	        "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	    private static final int RANDOM_STRING_LENGTH = 10;
	     

	    public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	
	    private int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	
 
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
   
    /*
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
    }*/
    
    public String hostname() {
    	InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
            return hostname;
        } catch (UnknownHostException e) {        	
            e.printStackTrace();
            return "";
        }
    }
    
    
    public String MillisToDate(long mili) {
    	          
           //creating Date from millisecond
           Date currentDate = new Date(mili);
          
           //printing value of Date
           System.out.println("current Date: " + currentDate);
          
           DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
           System.out.println("Milliseconds to Date: " + df.format(currentDate));
           //formatted value of current Date
           return df.format(currentDate);
        //   
          

          
    }
    
    public String formatoMoneda(double dato) {
    	DecimalFormat formatea = new DecimalFormat("###,###.##");
    	return formatea.format(dato);
    	
    }
    
    public String sendEmailRecuperacionUP(String destino,String nombre, String usuario, String pass, int id) throws Exception{
    	
    	        MimeMessage message = sender.createMimeMessage();
    	
    	        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
    	        
    	        String passw = "";
    	      //  Random rnd = new Random();

    	       /* for (int i = 0; i < 7; i++)
    	        {
    	            if(i < 4)
    	            {
    	                passw += rnd.nextInt(10);
    	            }
    	            else
    	            {
    	                passw += (char)(rnd.nextInt(100));
    	            }
    	        }*/
    	        
    	        passw = generateRandomString();
    	        
    	        System.out.println("pass : " + passw.trim());
    	        
    	        PasswordEncryptado passwordEncryptado = new PasswordEncryptado();
    	        
    	        usuarioServicio.usuarioActualizaPassword(passwordEncryptado.encrytePassword(passw.trim()), id);
    	      
    	        String mensaje = "<div style='font-family: \"calibri\", Garamond, 'Comic Sans MS';'><p>Hola, <b>"+nombre+"</b></p>";
    	        
    	        mensaje+="<p>Usuario : <b style='color:#337AB7;'>"+usuario+"</b></p>";
    	        mensaje+="<p>Nueva contraseña : <b style='color:#337AB7;'>"+passw.trim()+"</b></p>";
    	        
    	        mensaje+="<br/>Te sugerimos que cambies esta nueva contraseña por una que recuerdes, para realizarlo debes ir a SIABE->Menú->Usuarios->Cambio de password";
    	        
    	        mensaje+="<p>Si no solicitaste esta información, por favor envía un correo a vquitano@fundacionuv.org</p><br/></div>";
    	        
    	        helper.setFrom("vquitano@fundacionuv.org");
    	        
    	        helper.setTo(destino);
    	
    	        helper.setText(mensaje,true);
    	
    	        helper.setSubject("Usuario y nueva contraseña SIABE");
    	
    	         
    	
    	        sender.send(message);
    	        
    	        return "Done";
    	    }
    
    
    public String formatoMoneda2(Double num) {
    	NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        String currency = format.format(num);
        return currency;
    }


   
}
