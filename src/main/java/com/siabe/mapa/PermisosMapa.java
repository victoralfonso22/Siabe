package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Permisos;
import org.springframework.jdbc.core.RowMapper;

public class PermisosMapa implements RowMapper<Permisos> {

	public static final String BASE_SQL //
			= "Select id, id_usuario, id_catalogo_menu, estatus From permisos ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO permisos (id_usuario,id_catalogo_menu,estatus) values ";
	
	public static final String UPDATE_SQL //
	= "update permisos set ";
		
	@Override
	public Permisos mapRow(ResultSet rs, int rowNum) throws SQLException {

		int idPermiso = rs.getInt("id");
		int idUsuario = rs.getInt("id_usuario");
		int idCatalogoMenu = rs.getInt("id_catalogo_menu");
		int estatus = rs.getInt("estatus");
		
		return new Permisos(idPermiso, idUsuario, idCatalogoMenu,estatus);
	}

}
