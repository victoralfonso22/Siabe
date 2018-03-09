package com.siabe.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.siabe.modelo.Usuario;
import com.siabe.mapa.UsuarioMapa;
 
@Repository
@Transactional
public class UsuarioDAO extends JdbcDaoSupport {
 
    @Autowired
    public UsuarioDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public Usuario findUserAccount(String userName) {
        // Select .. from App_User u Where u.User_Name = ?
        String sql = UsuarioMapa.BASE_SQL + " where u.usuario = ? ";
 
        Object[] params = new Object[] { userName };
        UsuarioMapa mapper = new UsuarioMapa();
        try {
            Usuario userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    

}
