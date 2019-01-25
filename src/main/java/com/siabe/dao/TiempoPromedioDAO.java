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

	
	/********************************************************************************************************CARRERAS****************************************************************************/
	public TiempoPromedio regresarTiempoPromedio(int idTiempoPromedio) {
		String sql = TiempoPromedioMapa.BASE_SQL + " where id_carrera = ? ; ";

		Object[] params = new Object[] { idTiempoPromedio };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			TiempoPromedio periodoInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return periodoInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<TiempoPromedio> regresarTiempoPromedioTermino(String termino) {
String sql = TiempoPromedioMapa.BASE_SQL + " where carrera like '%"+termino+"%' ;";
		//System.out.println(sql);		
		try {
			return this.getJdbcTemplate().query(sql, new TiempoPromedioMapa());

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
	
	public List<TiempoPromedio> obtenerTiemposPromedioIdPeriodo(int idPeriodo) {

		String sql = TiempoPromedioMapa.BASE_SQL + " where id_periodo = ? order by area,carrera; ";
		
		
		
		Object[] params = new Object[] { idPeriodo };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<TiempoPromedio> obtenerTiemposPromedioRegion(int idRegion) {

		String sql = TiempoPromedioMapa.BASE_SQL + " where id_region = ? order by area,carrera; ";
		
		Object[] params = new Object[] { idRegion };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	public List<TiempoPromedio> tiemposPromedioIdPeriodoIdRegion(int idPeriodo, int idRegion) {

		String sql = TiempoPromedioMapa.BASE_SQL + " where id_periodo = ? and id_region = ?; ";
		
		Object[] params = new Object[] { idPeriodo , idRegion };
		TiempoPromedioMapa mapper = new TiempoPromedioMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<TiempoPromedio> tiemposPromedioIdPeriodoIdRegionInput(int idPeriodo, int idRegion, String termino) {
		String sql;
		if(idRegion != 0) {
		sql = TiempoPromedioMapa.BASE_SQL + " where carrera like '%"+termino+"%' and id_periodo = "+idPeriodo+" and id_region = "+idRegion+" order by area,carrera; ";
		}else {
			sql = TiempoPromedioMapa.BASE_SQL + " where carrera like '%"+termino+"%' and id_periodo = "+idPeriodo+" order by area,carrera; ";
		}
		
		try {
			return this.getJdbcTemplate().query(sql, new TiempoPromedioMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
public String insertaCarrera(int idFacultad, int idArea, int idRegion, String nombre, String nivel, String modalidad, int plan, int periodoPromedio, int idPeriodo) {
		
		String sql = TiempoPromedioMapa.INSERT_SQL_CARRERA + "(?,?,?,?,?,?,?,?,?)";

		Object[] params = new Object[] { idFacultad, idArea,idRegion, nombre , nivel, modalidad, plan, periodoPromedio,idPeriodo};			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return e.getMessage();
		}
	}
	
	
	public String actualizaCarrera(int idFacultad, int idArea, int idRegion, String nombre, String nivel, String modalidad, int plan, int periodoPromedio,int estatus, int idCarrera) {

		String sql = TiempoPromedioMapa.UPDATE_SQL_CARRERA + " id_facultad = ?, id_area=?, id_region = ?, nombre = ?, nivel = ?, modalidad = ?, plan = ?, periodo_promedio = ?, estatus = ? where id =?;";
		Object[] params = new Object[] { idFacultad,idArea, idRegion, nombre , nivel, modalidad, plan, periodoPromedio, estatus,idCarrera};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}

	}
	
	public List<TiempoPromedio> autocompleteCarrera(String cadena) {

		String	sql = TiempoPromedioMapa.BASE_SQL + " where "+cadena+" ";
	
		try {
			return this.getJdbcTemplate().query(sql, new TiempoPromedioMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	/*********************************************************************************AREAS*******************************************************************************/
	public List<Areas> obtenerAreas() {

		String sql = AreaMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new AreaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Areas> autocompleteAreas(int idRegion, String termino) {

		String	sql = AreaMapa.BASE_SQL_REGION + " where a.nombre like '%"+termino+"%' and c.id_region = "+idRegion+" and c.estatus= 1 GROUP BY a.id, a.nombre; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new AreaMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	public List<Areas> obtenerAreasRegiones(int idRegion) {

		String sql = AreaMapa.BASE_SQL_REGION + " WHERE c.id_region =? GROUP BY a.id";
		
	//	System.out.println(AreaMapa.BASE_SQL_REGION + " WHERE c.id_region = "+idRegion+" GROUP BY a.id");
		
		Object[] params = new Object[] { idRegion };
		AreaMapa mapper = new AreaMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

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
	
	/**************************************************************************************FACULTADES****************************************************************************/
	public List<Facultades> obtenerFacultades(int idArea, int idRegion) {

String sql = FacultadesMapa.BASE_SQL_REGION + " where f.id_area = ? and c.id_region = ? group by f.id ";

//System.out.println(FacultadesMapa.BASE_SQL_REGION + " where f.id_area = "+idArea+" and c.id_region = "+idRegion+" group by f.id ");
		
		Object[] params = new Object[] { idArea, idRegion };
		FacultadesMapa mapper = new FacultadesMapa();
		try {
			return this.getJdbcTemplate().query(sql, params, mapper);

		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	
	
	public List<Facultades> autocompleteFacultad(int idRegion,int idArea, String termino) {

		String	sql = FacultadesMapa.BASE_SQL_REGION + " where f.nombre like '%"+termino+"%' and c.id_region = "+idRegion+" and c.id_area = "+idArea+" and c.estatus= 1 GROUP BY f.id, f.nombre; ";
	
		
		try {
			return this.getJdbcTemplate().query(sql, new FacultadesMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	
	
	
	public List<Facultades> obtenerFacultades() {

		String sql = FacultadesMapa.BASE_SQL;
		try {
			return this.getJdbcTemplate().query(sql, new FacultadesMapa());

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	
	

	
public String insertaFacultad(String nombre) {
		
		String sql = TiempoPromedioMapa.INSERT_SQL_FACULTAD + "(?)";

		Object[] params = new Object[] { nombre };			
		try {			
			this.getJdbcTemplate().update(sql, params);
			
			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return "Error";
		}
	}
	
	
	public String actualizaFacultad(int idFacultad, String nombre) {

		String sql = TiempoPromedioMapa.UPDATE_SQL_FACULTAD + " nombre = ? where id =?;";
		Object[] params = new Object[] { nombre,  idFacultad};
		try {
			this.getJdbcTemplate().update(sql, params);

			return "Done";
		} catch (EmptyResultDataAccessException e) {
			return e.getMessage();
		}

	}
	

}
