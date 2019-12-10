package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.TiempoPromedio;
import org.springframework.jdbc.core.RowMapper;

public class TiempoPromedioMapa implements RowMapper<TiempoPromedio> {

	public static final String BASE_SQL //
			= "SELECT * FROM tiempos_promedio ";
	
	public static final String INSERT_SQL_CARRERA //
	
	= "INSERT INTO carreras (id_facultad,id_area, id_region, nombre, nivel, modalidad, plan, periodo_promedio) values ";
	
	public static final String UPDATE_SQL_CARRERA //
	= "update carreras set ";
	
	public static final String INSERT_SQL_FACULTAD //
	= "INSERT INTO facultades (nombre) values ";
	
	public static final String UPDATE_SQL_FACULTAD //
	= "update facultades set ";
	
	public static final String INSERT_SQL_AREAS //
	= "INSERT INTO areas (nombre) values ";
	
	public static final String UPDATE_SQL_AREAS //
	= "update areas set ";
	
	@Override
	public TiempoPromedio mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idCarrera = rs.getInt("id_carrera");
		String carrera = rs.getString("carrera");
		String nivel = rs.getString("nivel");
		String modalidad = rs.getString("modalidad");
		int plan = rs.getInt("plan");
		int periodoPromedio = rs.getInt("periodo_promedio");
		int idFacultad = rs.getInt("id_facultad");
		String facultad = rs.getString("facultad");
		int idArea = rs.getInt("id_area");
		String area = rs.getString("area");
		int idRegion = rs.getInt("id_region");
		String region = rs.getString("region");
		//int idPeriodo = rs.getInt("id_periodo");
		//String periodo = rs.getString("periodo");		
		int estatus = rs.getInt("estatus");
		String estatusDefinicion = rs.getString("estatusDef");
		
		return new TiempoPromedio(idCarrera, carrera,nivel,modalidad,plan,periodoPromedio,idFacultad, facultad, idArea,area,idRegion,region,estatus,estatusDefinicion);
	}

}
