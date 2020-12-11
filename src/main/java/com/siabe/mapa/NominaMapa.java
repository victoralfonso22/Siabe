package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Nomina;
import org.springframework.jdbc.core.RowMapper;

public class NominaMapa implements RowMapper<Nomina> {

	public static final String BASE_SQL //
			= "Select * From nomina ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO nomina (id_donativo,num_descuento, donativo_quincenal, saldo, id_quincena, id_usuario) values ";
	
	public static final String UPDATE_SQL //
	= "update nomina set ";
	
	public static final String DELETE_SQL //
	= "delete from nomina  ";
	
	public static final String BASE_SQL_PERIODOS_DONANTE //
	= "select n.*, vn.nombre_completo_don, vn.id_quincena_inicio, vn.quincena_inicio, vn.num_personal, vn.donativo_total, vn.num_quincenas from nomina n\r\n" + 
			"join view_donativos vn on vn.id = n.id_donativo ";
	
	public static final String BASE_SQL_AUTOCOMPLETE_DONANTE //
	= "select n.*, vn.nombre_completo_don, vn.id_quincena_inicio, vn.quincena_inicio, vn.num_personal, vn.donativo_total, vn.num_quincenas from view_donativos vn\r\n" + 
			"left join nomina n on n.id_donativo = vn.id "; 

	@Override
	public Nomina mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idNomina = rs.getInt("id");
		int idDonativo = rs.getInt("id_donativo");
		int numDescuento = rs.getInt("num_descuento");
		double donativoQuincenal = rs.getDouble("donativo_quincenal");
		double saldo = rs.getDouble("saldo");
		int idQuincenActual = rs.getInt("id_quincena");	  
		String nombreCompletoDon = rs.getString("nombre_completo_don");
		int idQuincenaInicio = rs.getInt("id_quincena_inicio");
		String quincenaInicio = rs.getString("quincena_inicio");
		String numPersonal = rs.getString("num_personal");
		double donativoTotal = rs.getDouble("donativo_total");
		int numQuincenas = rs.getInt("num_quincenas");
		
		return new Nomina(idNomina, idDonativo, numDescuento, idQuincenActual,donativoQuincenal, saldo,  nombreCompletoDon, idQuincenaInicio,quincenaInicio, numPersonal, donativoTotal,numQuincenas);
	}

}
