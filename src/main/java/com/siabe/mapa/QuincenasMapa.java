package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.Quincenas;
import org.springframework.jdbc.core.RowMapper;

public class QuincenasMapa implements RowMapper<Quincenas> {

	public static final String BASE_SQL //
			= "Select id, numero_quincena,nombre, anio From quincenas ";
	
	public static final String BASE_SQL_INSERT //
	= "insert into quincenas (numero_quincena,nombre,anio) values ";
	
	public static final String BASE_SQL_UPDATE //
	= "update quincenas set ";


	@Override
	public Quincenas mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idQuincena = rs.getInt("id");
		int numeroQuincena = rs.getInt("numero_quincena");
		String nombre = rs.getString("nombre");
		int anio = rs.getInt("anio");
		
		
		return new Quincenas(idQuincena,numeroQuincena, nombre,anio);
	}

}
