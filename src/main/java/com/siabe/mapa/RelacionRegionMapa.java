package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.RelacionRegion;
import org.springframework.jdbc.core.RowMapper;

public class RelacionRegionMapa implements RowMapper<RelacionRegion> {

	public static final String BASE_SQL //
			= "Select id, id_region_padre, id_region_hijo, estatus From relacion_dependencia_region ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO relacion_dependencia_region (id_region_padre, id_region_hijo, estatus) values ";
	
	public static final String UPDATE_SQL //
	= "update relacion_dependencia_region set ";

	@Override
	public RelacionRegion mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idRelacionRegion = rs.getInt("id");
		int idRegionPadre = rs.getInt("id_region_padre");
		int idRegionHijo = rs.getInt("id_region_hijo");
		int estatus = rs.getInt("estatus");
		
		return new RelacionRegion(idRelacionRegion, idRegionPadre, idRegionHijo, estatus);
	}

}
