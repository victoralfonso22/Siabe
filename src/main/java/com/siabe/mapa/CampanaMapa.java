package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.Campana;
import org.springframework.jdbc.core.RowMapper;

public class CampanaMapa implements RowMapper<Campana> {

	public static final String BASE_SQL //
			= "Select c.id, c.nombre, c.id_region,c. estatus, r.nombre region From campanas c\r\n" + 
					"join regiones r on r.id = c.id_region ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO campanas (nombre, id_region, estatus) values ";
	
	public static final String UPDATE_SQL //
	= "update campanas set ";

	@Override
	public Campana mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idCampana = rs.getInt("id");
		String nombre = rs.getString("nombre");
		int idRegion = rs.getInt("id_region");
		int estatus = rs.getInt("estatus");
		String region = rs.getString("region");
		
		return new Campana(idCampana, nombre, idRegion, estatus, region);
	}

}
