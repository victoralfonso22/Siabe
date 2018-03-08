package com.siabe.dao;

import java.util.List;

import javax.sql.DataSource;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
 
@Repository
@Transactional
public class RolesDAO extends JdbcDaoSupport {
 
    @Autowired
    public RolesDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public List<String> getRoleNames(Long idUsuario) {
        String sql = "Select r.rol " //
                + " from usuarios_roles ur, roles r " //
                + " where ur.id_rol = r.id and ur.id_usuario = ? ";
 
        Object[] params = new Object[] { idUsuario };
 
        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
 
        return roles;
    }

}
