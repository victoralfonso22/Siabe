package com.siabe.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.PermisosMenu;
import com.siabe.mapa.PermisosMenuMapa;
import java.util.List;

@Repository
@Transactional
public class PermisosMenuDAO extends JdbcDaoSupport {

	@Autowired
	public PermisosMenuDAO(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	public List<PermisosMenu> obtenerPermisosMenu(int idUsuario) {

		String sql = PermisosMenuMapa.MENU_SQL + " and p.id_usuario = ? order by cs.prioridad, cm.prioridad;";

		Object[] params = new Object[] { idUsuario };
		try {
			return this.getJdbcTemplate().query(sql, params, new PermisosMenuMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PermisosMenu> obtenerPermisosMenuTodos() {

		String sql = PermisosMenuMapa.MENUTODO_SQL + " order by cs.prioridad, cm.prioridad;";

		
		try {
			return this.getJdbcTemplate().query(sql, new PermisosMenuMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<PermisosMenu> obtenerPermisosMenuXSeccion(int idUsuario) {

		String sql = PermisosMenuMapa.MENU_SQL + " and p.id_usuario = ? group by cs.seccion order by cs.prioridad, cm.prioridad;";
	//	System.out.println(sql);
		Object[] params = new Object[] { idUsuario };
		try {
			return this.getJdbcTemplate().query(sql, params, new PermisosMenuMapa());

			// return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
