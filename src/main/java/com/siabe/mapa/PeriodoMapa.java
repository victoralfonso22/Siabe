package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.siabe.modelo.Periodo;
import org.springframework.jdbc.core.RowMapper;

public class PeriodoMapa implements RowMapper<Periodo> {

	public static final String BASE_SQL //
			= "Select p.id, p.nombre,p.fecha_inicio, p.fecha_final, p.id_tipo_beca, p.estatus, tb.nombre tBeca From periodos p\r\n" + 
					"join tipo_beca tb on tb.id = p.id_tipo_beca";
	
	public static final String INSERT_SQL //
	= "INSERT INTO periodos (nombre,fecha_inicio, fecha_final, id_tipo_beca ,estatus) values ";
	
	public static final String UPDATE_SQL //
	= "update periodos set ";

	@Override
	public Periodo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int periodoId = rs.getInt("id");
		String nombre = rs.getString("nombre");
		Date fecha_inicio = rs.getDate("fecha_inicio");
		Date fecha_final = rs.getDate("fecha_final");
		int idTipoBeca = rs.getInt("id_tipo_beca");
		int estatus = rs.getInt("estatus");
		String tBeca = rs.getString("tBeca");
		
		return new Periodo(periodoId, nombre, fecha_inicio, fecha_final, idTipoBeca, estatus, tBeca);
	}

}
