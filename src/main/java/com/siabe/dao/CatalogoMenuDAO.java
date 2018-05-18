package com.siabe.dao;


	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.jdbc.core.support.JdbcDaoSupport;
	import org.springframework.stereotype.Repository;
	import org.springframework.transaction.annotation.Transactional;
	import com.siabe.modelo.CatalogoMenu;
	import com.siabe.mapa.CatalogoMenuMapa;
	import java.util.List;

	 
	@Repository
	@Transactional
	public class CatalogoMenuDAO extends JdbcDaoSupport {
	 
	    @Autowired
	    public CatalogoMenuDAO(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	    
	    public List<CatalogoMenu> obtenerCatalogoMenuSeccion(int idSeccion){
	    	
	    	String sql = CatalogoMenuMapa.BASE_SQL + " where id_catalogo_secciones = ?;";
	    	
	//    	System.out.println(sql);
	    	
	    	Object[] params = new Object[] { idSeccion}; 
	    	try {
	    		return this.getJdbcTemplate().query(sql,params, new CatalogoMenuMapa());
	    		
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	
	    }
	    
	    
public List<CatalogoMenu> obtenerCatalogoMenu(){
	    	
	    	String sql = CatalogoMenuMapa.BASE_SQL_PERMISOS ;
	    	
	   // 	System.out.println(sql);
	    	
	    	 
	    	try {
	    		return this.getJdbcTemplate().query(sql, new CatalogoMenuMapa());
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	
	    }
	    
}
