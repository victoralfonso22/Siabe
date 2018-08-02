package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Regiones;
import org.springframework.jdbc.core.RowMapper;

public class RegionesMapa implements RowMapper<Regiones> {

	public static final String BASE_SQL //
			= "SELECT r.id, r.nombre, r.abreviatura, r.id_periodo, p.nombre periodo, r.estatus FROM regiones r\r\n" + 
					"join periodos p on p.id = r.id_periodo";
	
	public static final String INSERT_SQL //
	= "INSERT INTO regiones (nombre,abreviatura, id_periodo, estatus) values ";
	
	public static final String UPDATE_SQL //
	= "update regiones set ";

	@Override
	public Regiones mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idRegion = rs.getInt("id");
		String nombre = rs.getString("nombre");		
		String abreviatura = rs.getString("abreviatura");
		int idPeriodo = rs.getInt("id_periodo");
		String periodo = rs.getString("periodo");
		int estatus = rs.getInt("estatus");
		
		
		return new Regiones(idRegion, nombre, abreviatura, idPeriodo, periodo, estatus);
	}

}
