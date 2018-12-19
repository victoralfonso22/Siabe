package com.siabe.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.siabe.excepciones.FileStorageException;
import com.siabe.excepciones.MyFileNotFoundException;
//import com.siabe.utils.PropiedadesArchivosGuardados;
import com.siabe.utils.PropiedadesArchivosGuardados;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArchivoStorageServicio {

	private final Path fileStorageLocation;
	

    @Autowired
    public ArchivoStorageServicio() {
    //public ArchivoStorageServicio(PropiedadesArchivosGuardados fileStorageProperties) {
        this.fileStorageLocation = Paths.get("C:\\Users\\victo\\Music\\comprobantes")
                .toAbsolutePath().normalize();
   //     System.out.println("1 "+this.fileStorageLocation);
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, int idPeriodo, int idDonativo) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        fileName = idPeriodo+"-"+idDonativo+"-"+fileName;

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            
          
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            System.out.println("2 "+fileStorageLocation+" filename"+fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    public List<PropiedadesArchivosGuardados> listarArchivos(int idPeriodo, int idDonativo) throws IOException {
    	
    	/*List<PropiedadesArchivosGuardados> archivos = new ArrayList<PropiedadesArchivosGuardados>();
    	Files.walk(Paths.get("C:\\Users\\victo\\Music\\comprobantes")).forEach(ruta-> {
    		
    	    if (Files.isRegularFile(ruta)) {    	    	
    	    	
    	    	if(loadFileAsResource(ruta.getFileName().toString()).exists()) {
    	    		PropiedadesArchivosGuardados propiedadesArchivosGuardados = new PropiedadesArchivosGuardados();
    	    		propiedadesArchivosGuardados.setNombreArchivo( loadFileAsResource(ruta.getFileName().toString()).toString() );
    	    	
    	    	archivos.add(propiedadesArchivosGuardados);
    	    	System.out.println("filename 78 "+propiedadesArchivosGuardados.getNombreArchivo());
    	    	}
    	    }
    	
    	});
    	*/
    	
    	List<PropiedadesArchivosGuardados> archivos = new ArrayList<PropiedadesArchivosGuardados>();
    	File directory = new File("C:\\Users\\victo\\Music\\comprobantes");
        //get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList){
        	PropiedadesArchivosGuardados propiedadesArchivosGuardados = new PropiedadesArchivosGuardados();        	
            if (file.isFile()){
            	if(file.getName().contains(idPeriodo+"-"+idDonativo+"-")) {
            	propiedadesArchivosGuardados.setNombreArchivo(file.getName().replace(idPeriodo+"-"+idDonativo+"-", ""));
            	propiedadesArchivosGuardados.setLinkDescarga("http://localhost/downloadFile/"+file.getName().replace(idPeriodo+"-"+idDonativo+"-", ""));
            	archivos.add(propiedadesArchivosGuardados);
             //   System.out.println(file.getName());
            	}
            }
        }
    	
    	return archivos;
    	
    }
	
}

