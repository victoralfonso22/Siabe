package com.siabe.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Usuario;
import com.siabe.mapa.UsuarioMapa;
import java.util.List;

 
@Repository
@Transactional
public class UsuarioDAO extends JdbcDaoSupport {
 
    @Autowired
    public UsuarioDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Usuario findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = UsuarioMapa.BASE_SQL + " where u.usuario = ? and estatus = 1; ";
 
        Object[] params = new Object[] { userName };
        UsuarioMapa mapper = new UsuarioMapa();
        try {
            Usuario userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    public String insertaUsuario(String userName, String password, String nombre) {
    	
    	
    	String sql = UsuarioMapa.INSERT_SQL + "(?, ?, ?,?)";
    	
    	
    	Object[] params = new Object[] { userName, password, 1, nombre };    	
        try {
            this.getJdbcTemplate().update(sql, params);
            
            Usuario userInfo = findUserAccount(userName);
            
            String sqlRoles = "insert into usuarios_roles (id_usuario, id_rol) values ("+userInfo.getIdUsuario()+",1)";
            String sqlRoles2 = "insert into usuarios_roles (id_usuario, id_rol) values ("+userInfo.getIdUsuario()+",2)";
            
            this.getJdbcTemplate().update(sqlRoles);
            this.getJdbcTemplate().update(sqlRoles2);
            
            return "Done";
        } catch (EmptyResultDataAccessException e) {
        	 return "Error";
        }     	
    }
    
    public List<Usuario> obtenerUsuarios(){
    	
    	String sql = UsuarioMapa.BASE_SQL;    	
    	try {
    		return this.getJdbcTemplate().query(sql, new UsuarioMapa());
    		
    	   // return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    	
    }
    
public List<Usuario> obtenerUsuariosId(Long id){
    	
    	String sql = UsuarioMapa.BASE_SQL + "where id !=?;";
    	Object[] params = new Object[] { id}; 
    	try {
    		return this.getJdbcTemplate().query(sql,params, new UsuarioMapa());
    		
    	   // return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    	
    }
    
public String actualizaEstatusUsuario(int id, int estatus){
    	
		if(estatus == 1) {
			estatus = 0;
		}else {
			estatus = 1;
		}
    	String sql = UsuarioMapa.UPDATE_SQL  + "estatus = ? where id =?;";	
    	Object[] params = new Object[] { estatus,id}; 
    	//System.out.println(sql+ id+ estatus);
        try {
            this.getJdbcTemplate().update(sql, params);
                     
            return "Done";
        } catch (EmptyResultDataAccessException e) {
        	 return "Error";
        } 
    	
    }


public String actualizaPassword(String password, int id){
	
	
	String sql = UsuarioMapa.UPDATE_SQL  + "password = ? where id =?;";	
	Object[] params = new Object[] { password,id}; 
//	System.out.println(sql+ id+ password);
    try {
        this.getJdbcTemplate().update(sql, params);
                 
        return "Done";
    } catch (EmptyResultDataAccessException e) {
    	 return "Error";
    } 
	
}

}
