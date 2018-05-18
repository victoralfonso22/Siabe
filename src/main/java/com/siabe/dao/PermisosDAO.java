package com.siabe.dao;

	
	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.jdbc.core.support.JdbcDaoSupport;
	import org.springframework.stereotype.Repository;
	import org.springframework.transaction.annotation.Transactional;
	import com.siabe.modelo.Permisos;
	import com.siabe.mapa.PermisosMapa;
	import java.util.List;

	 
	@Repository
	@Transactional
	public class PermisosDAO extends JdbcDaoSupport {
	 
	    @Autowired
	    public PermisosDAO(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	
	    public List<Permisos> obtenerPermisos(int idUsuario){
	    	
	    	String sql = PermisosMapa.BASE_SQL + " where id_usuario = ? and estatus = 1;";
	    	
	    	Object[] params = new Object[] { idUsuario}; 
	    	try {
	    		return this.getJdbcTemplate().query(sql,params, new PermisosMapa());
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    }
	    
	    public String cambiaPermisoU(int idUsuario, int idMenu, int estatus) {
	    	String respuesta = "";
	    	
	    	String sql = PermisosMapa.BASE_SQL + " where id_usuario = ? and id_catalogo_menu = ? and estatus = ?;";
	    	
	    	String sql1 = PermisosMapa.INSERT_SQL + "( ? , ? , 1)";
	    	
	    	String sql2 = "";
	    	if(estatus == 1) {
	    		sql2 = PermisosMapa.UPDATE_SQL + "estatus = 0 where id_usuario = ? and id_catalogo_menu = ?;";	
	    	}else {
	    		sql2 = PermisosMapa.UPDATE_SQL + "estatus = 1  where id_usuario = ? and id_catalogo_menu = ?; ";
	    	}
	    	
	    	Object[] params = new Object[] { idUsuario, idMenu, estatus}; 
	    	
	    	Object[] params1 = new Object[] { idUsuario, idMenu};
	    	
	    	Object[] params2 = new Object[] { idUsuario, idMenu};
	    	
	    	try {
	    		 List<Permisos> existeRegistro = this.getJdbcTemplate().query(sql,params, new PermisosMapa());
	    		 
	    		 if(existeRegistro.size() == 0) {
	    			 this.getJdbcTemplate().update(sql1,params1);	    			 
	    		 }else {
	    			 this.getJdbcTemplate().update(sql2,params2);
	    		 }
	    		 respuesta = "Done";
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	return respuesta;
	    	
	    }
}
