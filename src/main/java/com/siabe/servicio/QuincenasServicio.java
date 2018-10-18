package com.siabe.servicio;

import com.siabe.dao.QuincenasDAO;
import com.siabe.modelo.Quincenas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class QuincenasServicio {
	
	@Autowired
    private QuincenasDAO quincenasDAO;

	public Quincenas regresaQuincena(int idQuincena) {
		return quincenasDAO.regresarQuincena(idQuincena);
	}
	
	public List<Quincenas> todasQuincenas(){
		return quincenasDAO.obtenerQuincenas();
	}


	public String insertQuincena(int numeroQuincena , String nombre, int anio) {
		return quincenasDAO.insertaQuincena(numeroQuincena, nombre, anio);
	}
	
	
	
	public String actualizarQuincenas(int idQuincena,  int numeroQuincena, String nombre, int anio) {
		return quincenasDAO.actualizaQuincena(idQuincena, numeroQuincena, nombre, anio);
	}
}
