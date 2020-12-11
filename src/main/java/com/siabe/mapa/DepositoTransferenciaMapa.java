package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.siabe.modelo.DepositoTransferencia;
import org.springframework.jdbc.core.RowMapper;

public class DepositoTransferenciaMapa implements RowMapper<DepositoTransferencia> {

	public static final String BASE_SQL //
			= "Select * From deposito_transferencia ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO deposito_transferencia (id_donativo,num_descuento, donativo, saldo, fecha, id_usuario) values ";
	
	public static final String UPDATE_SQL //
	= "update deposito_transferencia set ";
	
	public static final String DELETE_SQL //
	= "delete from deposito_transferencia  ";
	
	public static final String BASE_SQL_PERIODOS_DONANTE //
	= "select dt.*, vn.nombre_completo_don,  vn.donativo_total, vn.num_pagos from deposito_transferencia dt\r\n" + 
			"join view_donativos vn on vn.id = dt.id_donativo ";
	
	public static final String BASE_SQL_AUTOCOMPLETE_DONANTE //
	= "select dt.*, vn.nombre_completo_don,  vn.donativo_total, vn.num_pagos from view_donativos vn\r\n" + 
			"left join deposito_transferencia dt on dt.id_donativo = vn.id "; 

	@Override
	public DepositoTransferencia mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		int idDepositoTransferencia = rs.getInt("id");
		int idDonativo = rs.getInt("id_donativo");
		int numDescuento = rs.getInt("num_descuento");
		double donativo = rs.getDouble("donativo");
		double saldo = rs.getDouble("saldo");
		String nombreCompletoDon = rs.getString("nombre_completo_don");		
		double donativoTotal = rs.getDouble("donativo_total");
		int numPagos = rs.getInt("num_pagos");
		Date fecha = rs.getDate("fecha");
		
		return new DepositoTransferencia(idDepositoTransferencia, idDonativo, numDescuento,donativo, saldo,  nombreCompletoDon, donativoTotal,numPagos, fecha);
	}

}
