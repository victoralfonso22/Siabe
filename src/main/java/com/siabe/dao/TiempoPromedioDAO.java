package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.TiempoPromedio;
import com.siabe.modelo.Facultades;
import com.siabe.modelo.Areas;
import com.siabe.mapa.TiempoPromedioMapa;
import com.siabe.mapa.FacultadesMapa;
import com.siabe.mapa.AreaMapa;
import java.util.List;


@Repository
@Transactional
public class TiempoPromedioDAO extends JdbcDaoSupport {

	@Autowired
	public TiempoPromedioDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public TiempoPromedio regresarTiempoPromedio(int idTiempoPromedio) {
		String sql = TiempoPromedioMapa.BASE_SQL + " where id = ? ; ";

		Object[] params = new Object[] { idTiempoPromedio };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			TiempoPromedio periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<TiempoPromedio> obtenerTiemposPromedio() {

		String sql = TiempoPromedioMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new TiempoPromedioMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<TiempoPromedio> obtenerTiemposPromedioRegion(int idRegion) {

		String sql = TiempoPromedioMapa.BASE_SQL + " where id_region = ?; ";
		
		Object[] params = new Object[] { idRegion };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Facultades> obtenerFacultades(int idArea) {

String sql = FacultadesMapa.BASE_SQL + " where id_area = ?; ";
		
		Object[] params = new Object[] { idArea };
		FacultadesMapa mapper = new FacultadesMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Areas> obtenerAreas() {

		String sql = AreaMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new AreaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	

	public String insertaAreas(String nombre) {
		
		String sql = TiempoPromedioMapa.INSERT_SQL_AREAS + "(?)";

		Object[] params = new Object[] { nombre};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	
	public String actualizaAreas(int idArea, String nombre) {

		String sql = TiempoPromedioMapa.UPDATE_SQL_AREAS + " nombre = ? where id =?;";
		Object[] params = new Object[] { nombre, idArea};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
public String insertaFacultad(String nombre, int idArea) {
		
		String sql = TiempoPromedioMapa.INSERT_SQL_FACULTAD + "(?,?)";

		Object[] params = new Object[] { nombre , idArea};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	
	public String actualizaFacultad(int idFacultad, String nombre, int idArea) {

		String sql = TiempoPromedioMapa.UPDATE_SQL_AREAS + " nombre = ?, id_area = ? where id =?;";
		Object[] params = new Object[] { nombre, idArea, idFacultad};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	
public String insertaCarrera(int idFacultad, int idRegion, String nombre, String nivel, String modalidad, int plan, int periodoPromedio) {
		
		String sql = TiempoPromedioMapa.INSERT_SQL_CARRERA + "(?,?,?,?,?,?,?)";

		Object[] params = new Object[] { idFacultad, idRegion, nombre , nivel, modalidad, plan, periodoPromedio};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	
	public String actualizaCarrera(int idFacultad, int idRegion, String nombre, String nivel, String modalidad, int plan, int periodoPromedio, int idCarrera) {

		String sql = TiempoPromedioMapa.UPDATE_SQL_AREAS + " id_facultad = ?, id_region = ?, nombre = ?, nivel = ?, modalidad = ?, plan = ?, periodo_promedio = ? where id =?;";
		Object[] params = new Object[] { idFacultad, idRegion, nombre , nivel, modalidad, plan, periodoPromedio, idCarrera};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	

}
