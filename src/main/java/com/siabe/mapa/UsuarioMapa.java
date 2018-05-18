package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapa implements RowMapper<Usuario> {

	public static final String BASE_SQL //
			= "Select u.id, u.usuario, u.password, u.estatus, u.nombre, u.super From usuarios u ";
	
	public static final String INSERT_SQL //
	= "INSERT INTO usuarios (usuario,password,estatus,nombre) values ";
	
	public static final String UPDATE_SQL //
	= "update usuarios set ";

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long userId = rs.getLong("id");
		String userName = rs.getString("usuario");
		String encrytedPassword = rs.getString("password");
		int estatus = rs.getInt("estatus");
		String nombre = rs.getString("nombre");
		int superU = rs.getInt("super");

		return new Usuario(userId, userName, encrytedPassword,estatus, nombre,superU);
	}

}
