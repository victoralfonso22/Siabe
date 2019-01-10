package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Periodo;
import com.siabe.mapa.PeriodoMapa;
import java.util.List;
import java.util.Map;
import java.sql.PreparedStatement;
import java.util.Date;

import java.util.HashMap;

@Repository
@Transactional
public class PeriodoDAO extends JdbcDaoSupport {

	@Autowired
	public PeriodoDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Periodo regresarPeriodo(int idPeriodo) {	
		String sql = PeriodoMapa.BASE_SQL + " where p.id = ? ; ";
		Object[] params = new Object[] { idPeriodo };
		PeriodoMapa mapper = new PeriodoMapa();
		try {
			Periodo periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaPeriodo(String nombre, Date fecha_inicio, Date fecha_final, int idTipoBeca) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = PeriodoMapa.INSERT_SQL + "(?, ?, ?, ?, 1)";

		//Object[] params = new Object[] { nombre, fecha_inicio, fecha_final };
		
		/*Map<String,Object> bind = new HashMap<>(3);
        bind.put("nombre", nombre);
        bind.put("fecha_inicio", fecha_inicio);
        bind.put("fecha_final", fecha_final);
        bind.put("id_tipo_beca", idTipoBeca);
        SqlParameterSource paramSource = new MapSqlParameterSource(bind);*/
		try {
			
			java.sql.Date fechaI = new java.sql.Date(fecha_inicio.getTime()); 
			java.sql.Date fechaF = new java.sql.Date(fecha_final.getTime()); 
			//this.getJdbcTemplate().update(sql, paramSource, keyHolder);
			
			this.getJdbcTemplate().update(con -> {
			    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
			    ps.setString(1, nombre);
			    ps.setDate(2, fechaI);
			    ps.setDate(3, fechaF);
			    ps.setInt(4, idTipoBeca);
			    return ps;}, keyHolder);
			
			System.out.println("Ud insertado :"+keyHolder.getKey().intValue());

			/*
			 * Periodo userInfo = regresarPeriodo(userName);
			 * 
			 * String sqlRoles =
			 * "insert into usuarios_roles (id_usuario, id_rol) values ("+userInfo.
			 * getIdUsuario()+",1)"; String sqlRoles2 =
			 * "insert into usuarios_roles (id_usuario, id_rol) values ("+userInfo.
			 * getIdUsuario()+",2)";
			 * 
			 * this.getJdbcTemplate().update(sqlRoles);
			 * this.getJdbcTemplate().update(sqlRoles2);
			 */
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Periodo> obtenerPeriodos() {

		String sql = PeriodoMapa.BASE_SQL +" order by p.nombre";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Periodo> obtenerPeriodosIdBeca(int idTipoBeca) {

		String sql = PeriodoMapa.BASE_SQL + " where tb.id = ?";
		Object[] params = new Object[] { idTipoBeca };
		PeriodoMapa mapper = new PeriodoMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}


	public String actualizaEstatusUsuario(int id, int estatus) {

		if (estatus == 1) {
			estatus = 0;
		} else {
			estatus = 1;
		}
		String sql = PeriodoMapa.UPDATE_SQL + "estatus = ? where id =?;";
		Object[] params = new Object[] { estatus, id };
		// System.out.println(sql+ id+ estatus);
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

	public String actualizaDatos(int idPeriodo, String nombre,  Date fecha_inicio, Date fecha_final, int idTipoBeca, int estatus) {

		String sql = PeriodoMapa.UPDATE_SQL + " nombre = ?, fecha_inicio = ?, fecha_final = ?, id_tipo_beca = ?, estatus = ? where id =?;";
		Object[] params = new Object[] { nombre,  fecha_inicio, fecha_final, idTipoBeca, estatus, idPeriodo};
		// System.out.println(sql+ id+ password);
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

}
