package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Regiones;
import com.siabe.modelo.TiempoPromedio;
import com.siabe.mapa.RegionesMapa;
import com.siabe.mapa.TiempoPromedioMapa;

import java.util.List;


@Repository
@Transactional
public class RegionesDAO extends JdbcDaoSupport {

	@Autowired
	public RegionesDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Regiones regresarRegion(int idRegion) {	
		String sql = RegionesMapa.BASE_SQL + " where id = ? ; ";
		Object[] params = new Object[] { idRegion };
		RegionesMapa mapper = new RegionesMapa();
		try {
			Regiones periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Regiones> regresarRegionesPeriodo(int idPeriodo) {	
		String sql = RegionesMapa.BASE_SQL + " where id_periodo = ? ; ";
		Object[] params = new Object[] { idPeriodo };
		RegionesMapa mapper = new RegionesMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Regiones> regresarRegionesPeriodoActiva(int idPeriodo) {	
		String sql = RegionesMapa.BASE_SQL + " where r.id_periodo = ? and r.estatus = 1; ";
		Object[] params = new Object[] { idPeriodo };
		RegionesMapa mapper = new RegionesMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Regiones> regresarRegionesPeriodoActivaNoId(int idPeriodo,int idRegion) {	
		String sql = RegionesMapa.BASE_SQL + " where r.id_periodo = ? and r.estatus = 1 and r.id != ?; ";
		Object[] params = new Object[] { idPeriodo , idRegion };
		RegionesMapa mapper = new RegionesMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaRegion(String nombre, String abreviatura,  int idPeriodo) {
		
		String sql = RegionesMapa.INSERT_SQL + "(?, ?, ?, 1)";

		Object[] params = new Object[] { nombre, abreviatura, idPeriodo };
		
		try {
			
		this.getJdbcTemplate().update(sql, params);
			
		return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Regiones> obtenerRegiones() {

		String sql = RegionesMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new RegionesMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public String actualizaDatos(int idRegion, String nombre,  String abreviatura, int idPeriodo, int estatus) {

		String sql = RegionesMapa.UPDATE_SQL + " nombre = ?, abreviatura = ?, id_periodo = ?, estatus = ? where id =?;";
		Object[] params = new Object[] { nombre,  abreviatura, idPeriodo, estatus, idRegion};
		// System.out.println(sql+ id+ password);
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	public List<Regiones> autocompletarRegion(int idPeriodo, String termino) {
		
	
		String	sql = RegionesMapa.BASE_SQL + " where r.nombre like '%"+termino+"%' and r.id_periodo = "+idPeriodo+" and r.estatus= 1; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new RegionesMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
