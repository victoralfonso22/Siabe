package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Campana;
import com.siabe.mapa.CampanaMapa;

import java.util.List;


@Repository
@Transactional
public class CampanaDAO extends JdbcDaoSupport {

	@Autowired
	public CampanaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public Campana regresarCampana(int idCampana) {
		String sql = CampanaMapa.BASE_SQL + " where c.id = ? ; ";

		Object[] params = new Object[] { idCampana };
		CampanaMapa mapper = new CampanaMapa();
		try {
			Campana periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaCamapana(String nombre, int idRegion) {
		
		String sql = CampanaMapa.INSERT_SQL + "(?,?,1)";

		Object[] params = new Object[] { nombre , idRegion};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<Campana> obtenerCampanas() {

		String sql = CampanaMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new CampanaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Campana> obtenerCampanasRegion( int idRegion) {

		String sql = CampanaMapa.BASE_SQL + " where c.id_region = ?";
		Object[] params = new Object[] { idRegion};
		try {
			return this.getJdbcTemplate().query(sql, params,  new CampanaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}


	public String actualizaCampana(int idCampana, String nombre, int estatus, int idRegion) {

		String sql = CampanaMapa.UPDATE_SQL + " nombre = ?, id_region = ?, estatus = ? where id =?;";
		Object[] params = new Object[] { nombre, idRegion, estatus, idCampana};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

}
