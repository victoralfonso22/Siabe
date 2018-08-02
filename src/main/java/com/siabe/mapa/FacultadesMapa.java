package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.Facultades;
import org.springframework.jdbc.core.RowMapper;

public class FacultadesMapa implements RowMapper<Facultades> {

	public static final String BASE_SQL //
			= "Select id, nombre, id_area From facultades ";
	
	/*public static final String INSERT_SQL //
	= "INSERT INTO tipo_beca (nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update tipo_beca set ";*/

	@Override
	public Facultades mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idFacultades = rs.getInt("id");
		String nombre = rs.getString("nombre");
		int idArea = rs.getInt("id_area");
		
		return new Facultades(idFacultades, nombre, idArea);
	}

}
