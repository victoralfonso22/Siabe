package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siabe.modelo.Tarjeta;
import com.siabe.mapa.TarjetaMapa;


import java.util.List;
import java.sql.PreparedStatement;


@Repository
@Transactional
public class TarjetaDAO extends JdbcDaoSupport {

	@Autowired
	public TarjetaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Tarjeta regresarTarjetaPeriodo(int idPeriodo) {	
		String sql = TarjetaMapa.BASE_SQL_PERIODOS_DONANTE + " where vn.id_periodo = ?; ";
		Object[] params = new Object[] { idPeriodo };
		TarjetaMapa mapper = new TarjetaMapa();
		try {
			Tarjeta periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaTarjeta(int idDonativo, int numDescuento, double descuento, double saldo, int anio, int idUsuario) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = TarjetaMapa.INSERT_SQL + "(?, ?, ?, ?, ?, ?)";

	
		try {
			
			
			this.getJdbcTemplate().update(con -> {
			    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
			    ps.setInt(1, idDonativo);
			    ps.setInt(2, numDescuento);
			    ps.setDouble(3, descuento);		
			    ps.setDouble(4, saldo);	
			    ps.setDouble(5, anio);	
			    ps.setInt(6, idUsuario);		
			    return ps;}, keyHolder);
			
						
			System.out.println("Ud insertado :"+keyHolder.getKey().intValue());

	
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Tarjeta> obtenerTarjeta() {

		String sql = TarjetaMapa.BASE_SQL +" order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new TarjetaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Tarjeta> obtenerTarjetaPeriodo(int idPeriodo, int anio, int mes) {

		String sql = TarjetaMapa.BASE_SQL_PERIODOS_DONANTE +" where vn.id_periodo = "+idPeriodo+" and vn.mes_inicio_aportacion = "+mes+" and t.anio = "+anio+" ;";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new TarjetaMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Tarjeta> autocompletarDonantesTarjeta(String termino, int idPeriodo) {
		


		String	sql = TarjetaMapa.BASE_SQL_AUTOCOMPLETE_DONANTE + " where ((nombre_completo_don LIKE '%"+termino+"%') OR (rfc LIKE  '%"+termino+"%') OR (razon_social LIKE  '%"+termino+"%'))  and id_periodo = "+idPeriodo+" and medio_cobro = 3 and estatus = 1 limit 20;"; 
	
	System.out.println(sql);
	
	try {
		return this.getJdbcTemplate().query(sql, new TarjetaMapa());

	} catch (EmptyResultDataAccessException e) {
		return null;
	}

}
	
	public int getNumDescuento(int idDonativo) {
		
		
		String	sql = "select (num_descuento) + 1 num_descuento from tarjeta where id_donativo = '"+idDonativo+"'; ";
	
		
		try {
			System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Integer.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
	public double getSaldo(int idDonativo) {
		
		
		String	sql = "select saldo from tarjeta where id_donativo = '"+idDonativo+"' order by num_descuento desc limit 1; ";
	
		
		try {
		//	System.out.println("regreso "+ this.getJdbcTemplate().queryForObject(sql, Integer.class));
			return this.getJdbcTemplate().queryForObject(sql, Double.class);

		} catch (EmptyResultDataAccessException e) {
			return 0;
		}

	}
	
public Tarjeta verificaExiste(int idDonativo,int idPeriodo) {
		
		
	String sql = TarjetaMapa.BASE_SQL_PERIODOS_DONANTE +" where t.id_donativo = "+idDonativo+" and vn.id_periodo = "+idPeriodo+"  ;";
	
		
	Object[] params = new Object[] {  };
	TarjetaMapa mapper = new TarjetaMapa();
	try {
		Tarjeta nom = this.getJdbcTemplate().queryForObject(sql, params, mapper);
		return nom;
	} catch (EmptyResultDataAccessException e) {
		return null;
	}

	}

public String actualizaTarjeta(double descuento, double saldo, int idUsuario, int idTarjeta) {

	
	String sql = TarjetaMapa.UPDATE_SQL + " donativo = "+descuento+", saldo = "+saldo+", id_usuario = "+idUsuario+" where id = "+idTarjeta+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


public String eliminaTarjeta(int idTarjeta) {

	
	String sql = TarjetaMapa.DELETE_SQL + "  where id = "+idTarjeta+";";

	try {
		
		this.getJdbcTemplate().update(sql);

		return "Done";
	} catch (EmptyResultDataAccessException e) {
		return "Error";
	}
}


}
