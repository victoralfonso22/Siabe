package com.siabe.servicio;

import java.util.ArrayList;
import java.util.List;
 
import com.siabe.dao.RolesDAO;
import com.siabe.dao.UsuarioDAO;
import com.siabe.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
@Service
public class DetalleUsuarioServicioImp implements UserDetailsService  {

	@Autowired
    private UsuarioDAO UserDAO;
 
    @Autowired
    private RolesDAO RoleDAO;
 
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    	Usuario appUser = this.UserDAO.findUserAccount(userName);
 
        if (appUser == null) {
        //    System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
 
      //  System.out.println("Found User: " + appUser);
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.RoleDAO.getRoleNames(appUser.getIdUsuario());
 
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(appUser.getUsuario(), //
                appUser.getPassword(), grantList);
 
        return userDetails;
    }
 
}
