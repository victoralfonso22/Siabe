package com.siabe.servicio;

import com.siabe.dao.TipoDonativoDAO;
import com.siabe.modelo.TipoDonativo;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class TipoDonativoServicio {
	
	@Autowired
    private TipoDonativoDAO tipoDonativoDAO;

	public TipoDonativo regresaTipoDonativo(int idTipoDonativo) {
		return tipoDonativoDAO.regresarTipoDonativo(idTipoDonativo);
	}
	
	public String insertTipoDonativo(String nombre) {
		return tipoDonativoDAO.insertaTipoDonativo(nombre);
	}
	
	public List<TipoDonativo> todosTipoDonativo(){
		return tipoDonativoDAO.obtenerTipoDonativo();
	}
	
	public String actualizarTipoDonativo(int idTipoDonativo, String nombre) {
		return tipoDonativoDAO.actualizaTipoDonativo(idTipoDonativo, nombre);
	}
}
