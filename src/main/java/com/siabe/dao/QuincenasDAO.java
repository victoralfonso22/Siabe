package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Quincenas;
import com.siabe.modelo.Regiones;
import com.siabe.mapa.QuincenasMapa;
import com.siabe.mapa.RegionesMapa;

import java.util.List;


@Repository
@Transactional
public class QuincenasDAO extends JdbcDaoSupport {

	@Autowired
	public QuincenasDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Quincenas regresarQuincena(int idQuincena) {
		String sql = QuincenasMapa.BASE_SQL + " where id = ? ; ";

		Object[] params = new Object[] { idQuincena };
		QuincenasMapa mapper = new QuincenasMapa();
		try {
			Quincenas quincenaInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return quincenaInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaQuincena(int numeroQuincena,String nombre, int anio) {
		
		String sql = QuincenasMapa.BASE_SQL_INSERT + " (?,?,?)";

		Object[] params = new Object[] { numeroQuincena,nombre , anio};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Quincenas> obtenerQuincenas() {

		String sql = QuincenasMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new QuincenasMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
		
	public String actualizaQuincena(int idQuincena, int numeroQuincena, String nombre, int anio) {

		String sql = QuincenasMapa.BASE_SQL_UPDATE + " numero_quincena = ?, nombre = ?, anio = ? where id =?;";
		Object[] params = new Object[] { numeroQuincena, nombre, anio, idQuincena};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	public List<Quincenas> autocompletarQuincena(String termino) {
		
		
		String	sql = QuincenasMapa.BASE_SQL + " where (nombre like '%"+termino+"%') or (anio like '%"+termino+"%') order by id,nombre,anio limit 10; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new QuincenasMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

}
