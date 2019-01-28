package com.siabe.servicio;

import com.siabe.dao.RelacionRegionDAO;
import com.siabe.modelo.RelacionRegion;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class RelacionRegionServicio {
	
	@Autowired
    private RelacionRegionDAO relacionRegionDAO;

	public RelacionRegion regresaRelacionRegion(int idRegionPadre) {
		return relacionRegionDAO.regresarRelacionRegion(idRegionPadre);
	}
	
	public String insertRelacionRegion(int idRegionPadre, int idRegionHijo, int idPeriodo) {
		return relacionRegionDAO.insertaRelacionRegion(idRegionPadre, idRegionHijo, idPeriodo);
	}
	
	public List<RelacionRegion> todosRelacionRegionPadre(int idRegionPadre){
		return relacionRegionDAO.obtenerRelacionRegionPorPadre(idRegionPadre);
	}
	
	public String actualizarRelacionRegion(int idRelacionRegion, int estatus) {
		return relacionRegionDAO.actualizaTipoBeca(idRelacionRegion, estatus);
	}
	
	public RelacionRegion verificarRelacionRegion(int idRegionPadre, int idRegionHijo, int idPeriodo) {
		return relacionRegionDAO.verificarRelacionRegion(idRegionPadre, idRegionHijo, idPeriodo);
	}
}
