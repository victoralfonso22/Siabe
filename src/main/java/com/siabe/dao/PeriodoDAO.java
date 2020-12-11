package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Periodo;
import com.siabe.mapa.PeriodoMapa;

import java.util.List;
import java.sql.PreparedStatement;
import java.util.Date;


@Repository
@Transactional
public class PeriodoDAO extends JdbcDaoSupport {

	@Autowired
	public PeriodoDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Periodo regresarPeriodo(int idPeriodo) {	
		String sql = PeriodoMapa.BASE_SQL + " where id = ? ; ";
		Object[] params = new Object[] { idPeriodo };
		PeriodoMapa mapper = new PeriodoMapa();
		try {
			Periodo periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Periodo> regresarPeriodosMayor(int idPeriodo) {

		String sql = PeriodoMapa.BASE_SQL +" where nombre > (select nombre from periodos where id = "+idPeriodo+") order by nombre";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public String insertaPeriodo(String nombre, Date fecha_inicio, Date fecha_final) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = PeriodoMapa.INSERT_SQL + "(?, ?, ?, 1)";

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
			    return ps;}, keyHolder);
			
			
			//String sqlPeriodoDB = PeriodoMapa.INSERT_SQL_PERIODO_DB + " ("+idPeriodoDonante+","+keyHolder.getKey().intValue()+")";
			
		//	this.getJdbcTemplate().update(sqlPeriodoDB);
			
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

		String sql = PeriodoMapa.BASE_SQL +" order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosMayores(int idPeriodo) {

		String sql = PeriodoMapa.BASE_SQL +" where id > "+idPeriodo+" order by nombre desc";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosMayoresNoDeportiva(int idPeriodo) {

		String sql = PeriodoMapa.BASE_SQL +" where id > "+idPeriodo+" and deportiva = 0 order by nombre desc";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosMayoresSiDeportiva(int idPeriodo) {

		String sql = PeriodoMapa.BASE_SQL +" where id > "+idPeriodo+" and deportiva = 1 order by nombre desc";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Periodo> periodoActivoDeportiva(int idTipoBeca) {
		
		int dep = 0;
		if(idTipoBeca == 4) {
			dep=1;
		}
		
		String sql = PeriodoMapa.BASE_SQL +" where deportiva = "+dep+" and estatus = 1 order by nombre desc";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
public List<Periodo> periodoTodosDeportiva(int idTipoBeca) {
		
		int dep = 0;
		if(idTipoBeca == 4) {
			dep=1;
		}
		
		String sql = PeriodoMapa.BASE_SQL +" where deportiva = "+dep+" order by nombre desc";
		System.out.println(sql);
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosActivos() {

		String sql = PeriodoMapa.BASE_SQL +" where estatus = 1 order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<Periodo> obtenerPeriodosActivosSinEscuelas() {

		String sql = PeriodoMapa.BASE_SQL +" where estatus = 1 and deportiva = 0 order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosActivosSoloEscuelas() {

		String sql = PeriodoMapa.BASE_SQL +" where estatus = 1 and deportiva = 1 order by nombre desc";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	/*public List<Periodo> obtenerPeriodosBenDona() {

		String sql = PeriodoMapa.BASE_SQL_TB_PD +" order by p.nombre, p.id_tipo_beca";
		try {
			return this.getJdbcTemplate().query(sql, new PeriodoMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}*/
	
	
	
	public List<Periodo> obtenerPeriodosXBeneficiario(String beneficiario) {

		String sql = PeriodoMapa.BASE_SQL_PERIODOS_BENEFICIARIO + " WHERE vn.nombre_completo_bene = ? GROUP BY p.id order by p.nombre desc";
		Object[] params = new Object[] { beneficiario };
		PeriodoMapa mapper = new PeriodoMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Periodo> obtenerPeriodosXDonante(String donante) {

		String sql = PeriodoMapa.BASE_SQL_PERIODOS_DONANTE + " WHERE vn.nombre_completo_don = ? GROUP BY p.id order by p.nombre desc";
		Object[] params = new Object[] { donante };
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
		String sql = PeriodoMapa.UPDATE_SQL + " estatus = ? where id =?;";
		Object[] params = new Object[] { estatus, id };
		// System.out.println(sql+ id+ estatus);
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

	public String actualizaDatos(int idPeriodo, String nombre,  Date fecha_inicio, Date fecha_final, int estatus) {

		String sql = PeriodoMapa.UPDATE_SQL + " nombre = ?, fecha_inicio = ?, fecha_final = ?, estatus = ? where id =?;";
		Object[] params = new Object[] { nombre,  fecha_inicio, fecha_final, estatus, idPeriodo};
		// System.out.println(sql+ id+ password);
		
		//String sqlPeriodoDB = PeriodoMapa.UPDATE_SQL_DB + " id_periodo_donante = "+periodoDonante+" where id_periodo_beneficiario = "+idPeriodo+"";
		
		
		try {
			this.getJdbcTemplate().update(sql, params);
		//	this.getJdbcTemplate().update(sqlPeriodoDB);
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

}
