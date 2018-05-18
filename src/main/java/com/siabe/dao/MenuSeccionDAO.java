package com.siabe.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siabe.mapa.MenuSeccionMapa;
import com.siabe.modelo.MenuSeccion;


@Repository
@Transactional
public class MenuSeccionDAO extends JdbcDaoSupport{
	

	 
	    @Autowired
	    public MenuSeccionDAO(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	    
	    public List<MenuSeccion> obtenerMenuSeccionPermisos(){
	    	
	    	String sql = MenuSeccionMapa.BASE_SQL ;
	    	
//	    	System.out.println(sql);
	  
	    	try {	  
	    		return this.getJdbcTemplate().query(sql, new MenuSeccionMapa());
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	
	    }
	    
public List<MenuSeccion> obtenerMenuSeccion(){
	    	
	    	String sql = MenuSeccionMapa.BASE_SECCION ;
	    	
	//    	System.out.println(sql);
	  
	    	try {	  
	    		return this.getJdbcTemplate().query(sql, new MenuSeccionMapa());
	    		
	    	   // return userInfo;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
	    	
	    }

}
