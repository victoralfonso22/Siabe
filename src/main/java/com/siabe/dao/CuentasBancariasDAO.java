package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.CuentasBancarias;
import com.siabe.modelo.Quincenas;
import com.siabe.mapa.CuentasBancariasMapa;
import com.siabe.mapa.QuincenasMapa;

import java.util.List;


@Repository
@Transactional
public class CuentasBancariasDAO extends JdbcDaoSupport {

	@Autowired
	public CuentasBancariasDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public CuentasBancarias regresarCuentaBancaria(int idCuentaBancaria) {
		String sql = CuentasBancariasMapa.BASE_SQL + " where id = ? ; ";

		Object[] params = new Object[] { idCuentaBancaria };
		CuentasBancariasMapa mapper = new CuentasBancariasMapa();
		try {
			CuentasBancarias periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaCuentaBancaria(String nombre ,String numeroTarjeta, String sucursal, String clabe) {
		
		String sql = CuentasBancariasMapa.INSERT_SQL + "(?,?,?,?)";

		Object[] params = new Object[] { nombre, numeroTarjeta, sucursal, clabe};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<CuentasBancarias> obtenerCuentasBancarias() {

		String sql = CuentasBancariasMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new CuentasBancariasMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}


	public String actualizaCuentaBancaria(int idCuentaBancaria, String nombre ,String numeroTarjeta, String sucursal, String clabe) {

		String sql = CuentasBancariasMapa.UPDATE_SQL + " nombre= ?, numero_cuenta = ?, sucursal = ?, clabe = ? where id =?;";
		Object[] params = new Object[] { nombre,numeroTarjeta, sucursal, clabe, idCuentaBancaria};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	public List<CuentasBancarias> autocompletarCuentaBancaria(String termino) {
		
		
		String	sql = CuentasBancariasMapa.BASE_SQL + " where (nombre like '%"+termino+"%') or (numero_cuenta like '%"+termino+"%') or (sucursal like '%"+termino+"%') or (clabe like '%"+termino+"%') "
				+ "order by nombre,numero_cuenta,sucursal,clabe  limit 10; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new CuentasBancariasMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
