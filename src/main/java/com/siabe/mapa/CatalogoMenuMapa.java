package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.CatalogoMenu;
import org.springframework.jdbc.core.RowMapper;

public class CatalogoMenuMapa implements RowMapper<CatalogoMenu> {

	public static final String BASE_SQL //
			= "Select m.id,m.modulo, m.etiqueta, m.id_catalogo_secciones, m.url, m.prioridad From catalogo_menu m";
	
	public static final String BASE_SQL_PERMISOS = "SELECT m.id, m.modulo, m.etiqueta, m.id_catalogo_secciones, m.url, m.prioridad\r\n" + 
			"FROM catalogo_menu m\r\n" + 
			"ORDER BY prioridad, id_catalogo_secciones";
	

	@Override
	public CatalogoMenu mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idCatalogoMenu = rs.getInt("id");
		String modulo = rs.getString("modulo");
		String etiqueta = rs.getString("etiqueta");
		int idCatalogoSeccion = rs.getInt("id_catalogo_secciones");
		String url = rs.getString("url");
		int prioridad = rs.getInt("prioridad");
		
		
		return new CatalogoMenu(idCatalogoMenu, modulo, etiqueta,idCatalogoSeccion, url,prioridad);
	}

}
