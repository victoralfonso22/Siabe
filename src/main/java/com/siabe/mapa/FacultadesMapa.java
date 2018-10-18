package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.Facultades;
import org.springframework.jdbc.core.RowMapper;

public class FacultadesMapa implements RowMapper<Facultades> {

	public static final String BASE_SQL //
			= "Select id, nombre From facultades ";
	
	
	public static final String BASE_SQL_REGION //
	= "SELECT f.* FROM facultades f\r\n" + 
			"join carreras c on c.id_facultad = f.id\r\n";
	
	/*public static final String INSERT_SQL //
	= "INSERT INTO tipo_beca (nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update tipo_beca set ";*/

	@Override
	public Facultades mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idFacultades = rs.getInt("id");
		String nombre = rs.getString("nombre");
		
		
		return new Facultades(idFacultades, nombre);
	}

}
