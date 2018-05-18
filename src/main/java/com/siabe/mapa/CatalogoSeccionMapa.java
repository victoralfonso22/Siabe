package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.CatalogoSeccion;
import org.springframework.jdbc.core.RowMapper;

public class CatalogoSeccionMapa implements RowMapper<CatalogoSeccion> {

	public static final String BASE_SQL //
			= "Select s.id, s.seccion, s.etiqueta, s.prioridad From catalogo_secciones s ";
	
	@Override
	public CatalogoSeccion mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idCatalogoSeccion = rs.getInt("id");
		String seccion = rs.getString("seccion");
		String etiqueta = rs.getString("etiqueta");		
		int prioridad = rs.getInt("prioridad");
		
		

		return new CatalogoSeccion(idCatalogoSeccion, seccion, etiqueta,prioridad);
	}

}
