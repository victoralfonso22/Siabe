package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.RelacionRegion;
import com.siabe.mapa.RelacionRegionMapa;
import java.util.List;


@Repository
@Transactional
public class RelacionRegionDAO extends JdbcDaoSupport {

	@Autowired
	public RelacionRegionDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public RelacionRegion regresarRelacionRegion(int idRegionPadre) {
		String sql = RelacionRegionMapa.BASE_SQL + " where id = ? and estatus = 1; ";

		Object[] params = new Object[] { idRegionPadre };
		RelacionRegionMapa mapper = new RelacionRegionMapa();
		try {
			RelacionRegion periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	public List<RelacionRegion> obtenerRelacionRegionPorPadre(int idRegionPadre) {

		String sql = RelacionRegionMapa.BASE_SQL + " where id_region_padre = ? and estatus = 1; ";
		
		Object[] params = new Object[] { idRegionPadre };
		RelacionRegionMapa mapper = new RelacionRegionMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	

	public String insertaRelacionRegion(int idRegionPadre, int idRegionHijo) {
		
		String sql = RelacionRegionMapa.INSERT_SQL + "(?,?,1)";

		Object[] params = new Object[] { idRegionPadre , idRegionHijo};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	public RelacionRegion verificarRelacionRegion(int idRegionPadre, int idRegionHijo) {
		String sql = RelacionRegionMapa.BASE_SQL + " where id_region_padre = ? and id_region_hijo = ? ; ";
		
	//	System.out.println(RelacionRegionMapa.BASE_SQL + " where id_region_padre = "+idRegionPadre+" and id_region_hijo = "+idRegionHijo+" ; ");
		
		Object[] params = new Object[] { idRegionPadre , idRegionHijo };
		RelacionRegionMapa mapper = new RelacionRegionMapa();
		try {
			RelacionRegion periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	


	public String actualizaTipoBeca(int idRelacionRegion, int estatus) {

		String sql = RelacionRegionMapa.UPDATE_SQL + " estatus = ?  where id =?;";
		Object[] params = new Object[] { estatus, idRelacionRegion};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

}
