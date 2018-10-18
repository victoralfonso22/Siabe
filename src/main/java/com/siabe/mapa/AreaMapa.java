package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.Areas;
import org.springframework.jdbc.core.RowMapper;

public class AreaMapa implements RowMapper<Areas> {

	public static final String BASE_SQL //
			= "Select id, nombre From areas ";
	
	public static final String BASE_SQL_REGION //
	= "SELECT a . * \r\n" + 
			"FROM areas a\r\n" + 
			"JOIN carreras c ON c.id_area = a.id";
	
	/*public static final String INSERT_SQL //
	= "INSERT INTO tipo_beca (nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update tipo_beca set ";*/

	@Override
	public Areas mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idArea = rs.getInt("id");
		String nombre = rs.getString("nombre");
		
		
		return new Areas(idArea, nombre);
	}

}
