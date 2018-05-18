package com.siabe.mapa;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.siabe.modelo.MenuSeccion;

public class MenuSeccionMapa implements RowMapper<MenuSeccion> {

		public static final String BASE_SQL //
				= "select * from(SELECT m.id idMenu, m.modulo, s.seccion, p.id idPermiso, p.estatus estatusPermiso, s.id idSeccion FROM  `catalogo_menu` m \r\n" + 
						"left JOIN catalogo_secciones s ON s.id = m.id_catalogo_secciones\r\n" + 
						"right join permisos p on p.`id_catalogo_menu` = m.id where id_usuario = 10\r\n" + 
						"\r\n" + 
						"union \r\n" + 
						"\r\n" + 
						"SELECT m.id idMenu, m.modulo, s.seccion,0 idPermiso, 0 estatusPermiso, s.id idSeccion FROM  `catalogo_menu` m \r\n" + 
						"left JOIN catalogo_secciones s ON s.id = m.id_catalogo_secciones)x group by x.idSeccion, x.idMenu";
		
		
		public static final String BASE_SECCION //
		= "SELECT * \r\n" + 
				"FROM (\r\n" + 
				"\r\n" + 
				"SELECT m.id idMenu, m.modulo, s.seccion, p.id idPermiso, p.estatus estatusPermiso, s.id idSeccion\r\n" + 
				"FROM  `catalogo_menu` m\r\n" + 
				"LEFT JOIN catalogo_secciones s ON s.id = m.id_catalogo_secciones\r\n" + 
				"RIGHT JOIN permisos p ON p.`id_catalogo_menu` = m.id\r\n" +
				"UNION \r\n" + 
				"SELECT m.id idMenu, m.modulo, s.seccion, 0 idPermiso, 0 estatusPermiso, s.id idSeccion\r\n" + 
				"FROM  `catalogo_menu` m\r\n" + 
				"LEFT JOIN catalogo_secciones s ON s.id = m.id_catalogo_secciones\r\n" + 
				")x\r\n" + 
				"GROUP BY x.idSeccion";
		

		@Override
		public MenuSeccion mapRow(ResultSet rs, int rowNum) throws SQLException {

			int idMenu = rs.getInt("idMenu");
			String modulo = rs.getString("modulo");
			String seccion = rs.getString("seccion");
			int idPermiso = rs.getInt("idPermiso");
			int estatusPermiso = rs.getInt("estatusPermiso");
			int idSeccion = rs.getInt("idSeccion");
			
			return new MenuSeccion(idMenu, modulo, seccion, idPermiso, estatusPermiso, idSeccion);
		}

}
