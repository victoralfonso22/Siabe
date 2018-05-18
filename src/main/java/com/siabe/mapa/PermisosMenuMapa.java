package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.PermisosMenu;
import org.springframework.jdbc.core.RowMapper;

public class PermisosMenuMapa implements RowMapper<PermisosMenu> {


	public static final String MENU_SQL
	= "SELECT cs.id idSeccion, cs.seccion, cm.modulo, cm.url, p.estatus\r\n" + 
			"FROM permisos p\r\n" + 
			"JOIN catalogo_menu cm ON cm.id = p.id_catalogo_menu\r\n" + 
			"JOIN catalogo_secciones cs ON cs.id = cm.id_catalogo_secciones\r\n" + 
			"WHERE p.estatus =1\r\n";
	
	public static final String MENUTODO_SQL
	= "SELECT cs.id idSeccion, cs.seccion, cm.modulo, cm.url, p.estatus\r\n" + 
			"FROM permisos p\r\n" + 
			"JOIN catalogo_menu cm ON cm.id = p.id_catalogo_menu\r\n" + 
			"JOIN catalogo_secciones cs ON cs.id = cm.id_catalogo_secciones\r\n" + 
			"\r\n";
		
	@Override
	public PermisosMenu mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idSeccion = rs.getInt("idSeccion");
		String seccion = rs.getString("seccion");
		String modulo = rs.getString("modulo");
		String url = rs.getString("url");
		int estatus = rs.getInt("estatus");
		
		return new PermisosMenu(idSeccion,seccion, modulo, url,estatus);
	}

}
