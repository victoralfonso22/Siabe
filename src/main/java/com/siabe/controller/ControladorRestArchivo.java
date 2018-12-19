package com.siabe.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.siabe.servicio.ArchivoStorageServicio;
import com.siabe.utils.PropiedadesArchivosGuardados;
import com.siabe.utils.UploadArchivoResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControladorRestArchivo {

	private static final Logger logger = LoggerFactory.getLogger(ControladorRestArchivo.class);

    @Autowired
    private ArchivoStorageServicio fileStorageService;
    
 
    
    @PostMapping("/uploadFile")
    public UploadArchivoResponse uploadFile(@RequestParam("file") MultipartFile file, @RequestParam int idPeriodo, @RequestParam int idDonativo) {
        String fileName = fileStorageService.storeFile(file,idPeriodo,idDonativo);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadArchivoResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadArchivoResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, @RequestParam int idPeriodo, @RequestParam int idDonativo) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,idPeriodo,idDonativo))
                .collect(Collectors.toList());
    }
    
    @PostMapping("/listFiles")
    public String listFiles(@RequestParam int idPeriodo, @RequestParam int idDonativo) throws IOException {
    	String response = "";
    	List<PropiedadesArchivosGuardados> listaFiles = new ArrayList<PropiedadesArchivosGuardados>();
    	listaFiles = fileStorageService.listarArchivos( idPeriodo, idDonativo);  
    	int count = 1;
    	
    	response += ""
				+ "<br/><table class=\"table tablaArchivos\"  ";

    	
    	for(int b = 0; b < listaFiles.size() ;b++) {
    		if(count == 1) {
    			response += "<tr>";
    			response += "<td><span>	<a title='Click para descargar' href="+listaFiles.get(b).getLinkDescarga()+">"+listaFiles.get(b).getNombreArchivo()+"</a></span></td>";
    			count++;
    		}else if(count > 1 && count < 3) {
    		response += "<td><span>	<a title='Click para descargar' href="+listaFiles.get(b).getLinkDescarga()+">"+listaFiles.get(b).getNombreArchivo()+"</a></span></td>"; 
    		count++;
    		}else {
    			response += "<td><span>	<a title='Click para descargar' href="+listaFiles.get(b).getLinkDescarga()+">"+listaFiles.get(b).getNombreArchivo()+"</a></span></td>";
    			response += "</tr>";
    		count = 1;
    		}
    	}
    	response += "</table>";

    	
    	  
    	return response;
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
