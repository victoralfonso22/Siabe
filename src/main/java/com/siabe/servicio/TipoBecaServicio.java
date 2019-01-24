package com.siabe.servicio;

import com.siabe.dao.TipoBecaDAO;
import com.siabe.modelo.TipoBeca;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class TipoBecaServicio {
	
	@Autowired
    private TipoBecaDAO tipoBecaDAO;

	public TipoBeca regresaTipoBeca(int idTipoBeca) {
		return tipoBecaDAO.regresarTipoBeca(idTipoBeca);
	}
	
	public String insertTipoBeca(String nombre) {
		return tipoBecaDAO.insertaTipoBeca(nombre);
	}
	
	public List<TipoBeca> todosTipoBeca(){
		return tipoBecaDAO.obtenerTipoBeca();
	}
	
	public String actualizarTipoBeca(int idTipoBeca, String nombre) {
		return tipoBecaDAO.actualizaTipoBeca(idTipoBeca, nombre);
	}
	
	public List<TipoBeca> tipoBecaXBeneficiario(String beneficiario, int idPeriodo){
		return tipoBecaDAO.obtenerTiposBecaXBeneficiario(beneficiario, idPeriodo);
	}
}
