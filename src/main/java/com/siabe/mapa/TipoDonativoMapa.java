package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.TipoDonativo;
import org.springframework.jdbc.core.RowMapper;

public class TipoDonativoMapa implements RowMapper<TipoDonativo> {

	public static final String BASE_SQL //
			= "Select id, nombre From tipo_donativo ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO tipo_donativo (nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update tipo_donativo set ";

	@Override
	public TipoDonativo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idTipoDonativo = rs.getInt("id");
		String nombre = rs.getString("nombre");
		
		
		return new TipoDonativo(idTipoDonativo, nombre);
	}

}
