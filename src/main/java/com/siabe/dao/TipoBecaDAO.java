package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.TipoBeca;
import com.siabe.mapa.TipoBecaMapa;
import java.util.List;


@Repository
@Transactional
public class TipoBecaDAO extends JdbcDaoSupport {

	@Autowired
	public TipoBecaDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public TipoBeca regresarTipoBeca(int idTipoBeca) {
		String sql = TipoBecaMapa.BASE_SQL + " where id = ? ; ";

		Object[] params = new Object[] { idTipoBeca };
		TipoBecaMapa mapper = new TipoBecaMapa();
		try {
			TipoBeca periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String insertaTipoBeca(String nombre) {
		
		String sql = TipoBecaMapa.INSERT_SQL + "(?)";

		Object[] params = new Object[] { nombre};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}

	public List<TipoBeca> obtenerTipoBeca() {

		String sql = TipoBecaMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new TipoBecaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}


	public String actualizaTipoBeca(int idTipoBeca, String nombre) {

		String sql = TipoBecaMapa.UPDATE_SQL + " nombre = ? where id =?;";
		Object[] params = new Object[] { nombre, idTipoBeca};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}

	
	public List<TipoBeca> obtenerTiposBecaXBeneficiario(String beneficiario, int idPeriodo) {

		String sql = TipoBecaMapa.BASE_SQL_TB_BENEFICIARIO + " WHERE vn.nombre_completo_bene = '"+beneficiario+"' and vn.id_periodo = "+idPeriodo+" group by tb.id";
		try {
			return this.getJdbcTemplate().query(sql, new TipoBecaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
}
