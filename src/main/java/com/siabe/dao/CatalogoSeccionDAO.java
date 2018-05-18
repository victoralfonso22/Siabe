package com.siabe.dao;


	import javax.sql.DataSource;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.dao.EmptyResultDataAccessException;
	import org.springframework.jdbc.core.support.JdbcDaoSupport;
	import org.springframework.stereotype.Repository;
	import org.springframework.transaction.annotation.Transactional;
	import com.siabe.modelo.CatalogoSeccion;
	import com.siabe.mapa.CatalogoSeccionMapa;
	import java.util.List;

	 
	@Repository
	@Transactional
	public class CatalogoSeccionDAO extends JdbcDaoSupport {
	 
	    @Autowired
	    public CatalogoSeccionDAO(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	    
	    
	    public List<CatalogoSeccion> obtenerCatalogoSeccion(){
	    	
	    	String sql = CatalogoSeccionMapa.BASE_SQL;    	
	    	try {
	    		return this.getJdbcTemplate().query(sql, new CatalogoSeccionMapa());
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	
	    }
	    
}
