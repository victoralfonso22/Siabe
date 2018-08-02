package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.TipoBeca;
import org.springframework.jdbc.core.RowMapper;

public class TipoBecaMapa implements RowMapper<TipoBeca> {

	public static final String BASE_SQL //
			= "Select id, nombre From tipo_beca ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO tipo_beca (nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update tipo_beca set ";

	@Override
	public TipoBeca mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idTipoBeca = rs.getInt("id");
		String nombre = rs.getString("nombre");
		
		
		return new TipoBeca(idTipoBeca, nombre);
	}

}
