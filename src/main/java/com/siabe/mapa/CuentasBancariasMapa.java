package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.siabe.modelo.CuentasBancarias;
import org.springframework.jdbc.core.RowMapper;

public class CuentasBancariasMapa implements RowMapper<CuentasBancarias> {

	public static final String BASE_SQL //
			= "Select id, nombre, numero_cuenta, sucursal, clabe From cuentas_bancarias ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO cuentas_bancarias (nombre,numero_cuenta, sucursal, clabe) values ";
	
	public static final String UPDATE_SQL //
	= "update cuentas_bancarias set ";

	@Override
	public CuentasBancarias mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idCuentaBancaria = rs.getInt("id");
		String nombre = rs.getString("nombre");
		String numCuenta = rs.getString("numero_cuenta");
		String sucursal = rs.getString("sucursal");
		String clabe = rs.getString("clabe");
		
		
		return new CuentasBancarias(idCuentaBancaria, nombre, numCuenta, sucursal,clabe);
	}

}
