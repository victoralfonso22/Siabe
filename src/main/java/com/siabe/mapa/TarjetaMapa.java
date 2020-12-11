package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Tarjeta;
import org.springframework.jdbc.core.RowMapper;

public class TarjetaMapa implements RowMapper<Tarjeta> {

	public static final String BASE_SQL //
			= "Select * From tarjeta ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO tarjeta (id_donativo,num_descuento, donativo, saldo,anio, id_usuario) values ";
	
	public static final String UPDATE_SQL //
	= "update tarjeta set ";
	
	public static final String DELETE_SQL //
	= "delete from tarjeta  ";
	
	public static final String BASE_SQL_PERIODOS_DONANTE //
	= "select t.*, vn.nombre_completo_don,  vn.donativo_total, vn.num_pagos from tarjeta t\r\n" + 
			"join view_donativos vn on vn.id = t.id_donativo ";
	
	public static final String BASE_SQL_AUTOCOMPLETE_DONANTE //
	= "select t.*, vn.nombre_completo_don, vn.donativo_total, vn.num_pagos from view_donativos vn\r\n" + 
			"left join tarjeta t on t.id_donativo = vn.id "; 

	@Override
	public Tarjeta mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		int idDepositoTransferencia = rs.getInt("id");
		int idDonativo = rs.getInt("id_donativo");
		int numDescuento = rs.getInt("num_descuento");
		double donativo = rs.getDouble("donativo");
		double saldo = rs.getDouble("saldo");
		int mes = rs.getInt("mes");
		String nombreCompletoDon = rs.getString("nombre_completo_don");		
		double donativoTotal = rs.getDouble("donativo_total");
		int numPagos = rs.getInt("num_pagos");

		
		return new Tarjeta(idDepositoTransferencia, idDonativo, numDescuento,donativo, saldo, mes,  nombreCompletoDon, donativoTotal,numPagos);
	}

}
