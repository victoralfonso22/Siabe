package com.siabe.config;
 
import com.siabe.servicio.DetalleUsuarioServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.BeanNameViewResolver;


@ComponentScan
@Configuration
@EnableWebSecurity
public class Seguridad extends WebSecurityConfigurerAdapter {
 
	@Autowired
    DetalleUsuarioServicioImp userDetailsService;
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    
     
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
 
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.csrf().disable();
        
        http.sessionManagement().invalidSessionUrl("/sesionExpirada");
 
        // The pages does not require login
      //  http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
 
        // /userInfo page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        //http.authorizeRequests().antMatchers("/usuario").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
 
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/inicio").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/usuarios/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/catalogos/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/reportes/**").access("hasRole('ROLE_ADMIN')");
        //http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");
 
        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/inicio")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/salir");
 
    }
}