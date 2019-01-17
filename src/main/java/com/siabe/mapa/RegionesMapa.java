package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Regiones;
import org.springframework.jdbc.core.RowMapper;

public class RegionesMapa implements RowMapper<Regiones> {

	public static final String BASE_SQL //
		/*	= "SELECT r.id, r.nombre, r.abreviatura, r.id_periodo, p.nombre periodo, r.estatus FROM regiones r\r\n" + 
					"join periodos p on p.id = r.id_periodo"; */
	= "SELECT r.id, r.nombre, r.abreviatura, p.id id_periodo, p.nombre periodo, tr.estatus FROM regiones r\r\n" + 
			"					 join trPeridoRegion tr on tr.id_region = r.id\r\n" + 
			"          join periodos p on p.id = tr.id_periodo ";
	
	public static final String BASE_SQL_REG //
	
	= "select *, null id_periodo, null periodo, null estatus  from regiones ";
	
	public static final String BASE_SQL_REGION_PRINCIPAL //
	
		= "SELECT *, null id_periodo, null periodo, null estatus FROM regiones";
	
	/*public static final String BASE_SQL_DONANTE
		= "SELECT r.id, r.nombre, r.abreviatura, r.id_periodo, p.nombre periodo, r.estatus FROM regiones r " + 
				"join trPeriodoDonPeriodoBen tr on tr.id_periodo_beneficiario = r.id_periodo " + 
				"join periodos_donante pd on pd.id = tr.id_periodo_donante "; */
	
	public static final String INSERT_SQL //
	= "INSERT INTO regiones (nombre,abreviatura) values ";
	
	public static final String INSERT_SQL_TR //
	= "INSERT INTO trPeridoRegion (id_region,id_periodo) values ";
	
	public static final String UPDATE_SQL //
	= "update regiones set ";
	
	public static final String UPDATE_SQL_TR //
	= "update trPeridoRegion set ";

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
