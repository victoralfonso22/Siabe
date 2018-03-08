package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siabe.modelo.Usuario;
import org.springframework.jdbc.core.RowMapper;

public class UsuarioMapa implements RowMapper<Usuario> {

	public static final String BASE_SQL //
			= "Select u.id, u.usuario, u.password From usuarios u ";

	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long userId = rs.getLong("id");
		String userName = rs.getString("usuario");
		String encrytedPassword = rs.getString("password");

		return new Usuario(userId, userName, encrytedPassword);
	}

}
