package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import com.siabe.modelo.Periodo;
import org.springframework.jdbc.core.RowMapper;

public class PeriodoMapa implements RowMapper<Periodo> {

	public static final String BASE_SQL //
			= "Select id, nombre,fecha_inicio, fecha_final, estatus, deportiva From periodos ";
	
	/*public static final String BASE_SQL_TB_PD //
	= "Select p.id, p.nombre,p.fecha_inicio, p.fecha_final, p.id_tipo_beca, pd.nombre periodo_donante, pd.id id_periodo_donante,  p.estatus, tb.nombre tBeca \r\n" + 
			"From periodos p\r\n" + 
			"join tipo_beca tb on tb.id = p.id_tipo_beca\r\n" + 
			"left join trPeriodoDonPeriodoBen pdb on pdb.id_periodo_beneficiario = p.id\r\n" + 
			"left join periodos_donante pd on pd.id = pdb.id_periodo_donante ";*/
	
	public static final String INSERT_SQL //
	= "INSERT INTO periodos (nombre,fecha_inicio, fecha_final, estatus) values ";
	
	/*public static final String INSERT_SQL_PERIODO_DB //
	= "INSERT INTO  trPeriodoDonPeriodoBen (id_periodo_donante,id_periodo_beneficiario) values ";*/
	
	public static final String UPDATE_SQL //
	= "update periodos set ";
	
	/*public static final String UPDATE_SQL_DB //
	= "update trPeriodoDonPeriodoBen set ";*/
	
	public static final String BASE_SQL_PERIODOS_BENEFICIARIO //
	= "SELECT p.* FROM view_beneficiarios vn JOIN periodos p ON p.id = vn.id_periodo ";
	
	public static final String BASE_SQL_PERIODOS_DONANTE //
	= "SELECT p.* FROM view_donativos vn JOIN periodos p ON p.id = vn.id_periodo ";

	@Override
	public Periodo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int periodoId = rs.getInt("id");
		String nombre = rs.getString("nombre");
		Date fecha_inicio = rs.getDate("fecha_inicio");
		Date fecha_final = rs.getDate("fecha_final");
		//int idTipoBeca = rs.getInt("id_tipo_beca");
		int estatus = rs.getInt("estatus");
		int deportiva = rs.getInt("deportiva");
		//String tBeca = rs.getString("tBeca");
		
		
        String[] arrOfStr = nombre.split("/"); 
        //for (int i = 0; i < arrOfStr.length;i++){ 
        /*	nombre = arrOfStr[0];
        	nombre = nombre+"	/n	/		"+arrOfStr[1];
        	System.out.println(nombre);*/
        //}
		
		return new Periodo(periodoId, nombre, fecha_inicio, fecha_final, estatus, deportiva);
	}

}
