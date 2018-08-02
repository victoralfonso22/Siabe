package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.TipoDonativo;
import com.siabe.mapa.TipoDonativoMapa;
import java.util.List;


@Repository
@Transactional
public class TipoDonativoDAO extends JdbcDaoSupport {

	@Autowired
	public TipoDonativoDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public TipoDonativo regresarTipoDonativo(int idTipoDonativo) {
		String sql = TipoDonativoMapa.BASE_SQL + " where id = ? ; ";

		Object[] params = new Object[] { idTipoDonativo };
		TipoDonativoMapa mapper = new TipoDonativoMapa();
		try {
			TipoDonativo periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaTipoDonativo(String nombre) {
		
		String sql = TipoDonativoMapa.INSERT_SQL + "(?)";

		Object[] params = new Object[] { nombre};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<TipoDonativo> obtenerTipoDonativo() {

		String sql = TipoDonativoMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new TipoDonativoMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}


	public String actualizaTipoDonativo(int idTipoDonativo, String nombre) {

		String sql = TipoDonativoMapa.UPDATE_SQL + " nombre = ? where id =?;";
		Object[] params = new Object[] { nombre, idTipoDonativo};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

}
