package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siabe.modelo.DepositoTransferencia;
import com.siabe.mapa.DepositoTransferenciaMapa;

import java.sql.Date;
import java.util.List;
import java.sql.PreparedStatement;


@Repository
@Transactional
public class DepositoTransferenciaDAO extends JdbcDaoSupport {

	@Autowired
	public DepositoTransferenciaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public DepositoTransferencia regresarDepositoTransferenciaPeriodo(int idPeriodo) {	
		String sql = DepositoTransferenciaMapa.BASE_SQL_PERIODOS_DONANTE + " where vn.id_periodo = ?; ";
		Object[] params = new Object[] { idPeriodo };
		DepositoTransferenciaMapa mapper = new DepositoTransferenciaMapa();
		try {
			DepositoTransferencia periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaDepositoTransferencia(int idDonativo, int numDescuento, double descuento, double saldo, Date fecha, int idUsuario) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = DepositoTransferenciaMapa.INSERT_SQL + "(?, ?, ?, ?, ?, ?)";

	
		try {
			
			
			this.getJdbcTemplate().update(con -> {
			    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
			    ps.setInt(1, idDonativo);
			    ps.setInt(2, numDescuento);
			    ps.setDouble(3, descuento);		
			    ps.setDouble(4, saldo);	
			    ps.setDate(5, fecha);	
			    ps.setInt(6, idUsuario);		
			    return ps;}, keyHolder);
			
						
			System.out.println("Ud insertado :"+keyHolder.getKey().intValue());

	
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<DepositoTransferencia> obtenerDepositoTransferencia() {

		String sql = DepositoTransferenciaMapa.BASE_SQL +" order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new DepositoTransferenciaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<DepositoTransferencia> obtenerDepositoTransferenciaPeriodo(int idPeriodo) {

		String sql = DepositoTransferenciaMapa.BASE_SQL_PERIODOS_DONANTE +" where vn.id_periodo = "+idPeriodo+" ;";
		try {
			return this.getJdbcTemplate().query(sql, new DepositoTransferenciaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<DepositoTransferencia> autocompletarDonantesDepositoTransferencia(String termino, int idPeriodo) {
		


		String	sql = DepositoTransferenciaMapa.BASE_SQL_AUTOCOMPLETE_DONANTE + " where ((nombre_completo_don LIKE '%"+termino+"%') OR (rfc LIKE  '%"+termino+"%') OR (razon_social LIKE  '%"+termino+"%'))  and id_periodo = "+idPeriodo+" and medio_cobro = 1 and estatus = 1 limit 20;"; 
	
	System.out.println(sql);
	
	try {
		return this.getJdbcTemplate().query(sql, new DepositoTransferenciaMapa());

	} catch (EmptyResultDataAccessException e) {
		return null;
	}

}
	
	public int getNumDescuento(int idDonativo) {
		
		
		String	sql = "select (num_descuento) + 1 num_descuento from deposito_transferencia where id_donativo = '"+idDonativo+"'; ";
	
		
		try {
			System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
	public double getSaldo(int idDonativo) {
		
		
		String	sql = "select saldo from deposito_transferencia where id_donativo = '"+idDonativo+"' order by num_descuento desc limit 1; ";
	
		
		try {
		//	System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Double.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
public DepositoTransferencia verificaExiste(int idDonativo,int idPeriodo) {
		
		
	String sql = DepositoTransferenciaMapa.BASE_SQL_PERIODOS_DONANTE +" where dt.id_donativo = "+idDonativo+" and vn.id_periodo = "+idPeriodo+"  ;";
	
		
	Object[] params = new Object[] {  };
	DepositoTransferenciaMapa mapper = new DepositoTransferenciaMapa();
	try {
		DepositoTransferencia nom = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return nom;
	} catch (EmptyResultDataAccessException e) {
		return null;
	}

	}

public String actualizaDepositoTransferencia(double descuento, double saldo, Date fecha, int idUsuario, int idDepositoTransferencia) {

	
	String sql = DepositoTransferenciaMapa.UPDATE_SQL + " donativo = "+descuento+", saldo = "+saldo+", id_usuario = "+idUsuario+", fecha = '"+fecha+"' where id = "+idDepositoTransferencia+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


public String eliminaDepositoTransferencia(int idDepositoTransferencia) {

	
	String sql = DepositoTransferenciaMapa.DELETE_SQL + "  where id = "+idDepositoTransferencia+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


}
