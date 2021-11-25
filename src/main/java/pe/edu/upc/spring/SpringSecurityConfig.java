package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSucessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsServiceImpl;

@EnableGlobalMethodSecurity(securedEnabled = true)

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JpaUserDetailsServiceImpl userDetailsService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private LoginSucessHandler sucessHandler;

	protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()

					.antMatchers("/planner/**").access("hasRole('ROLE_PRESTADOR') or hasRole('ROLE_ADMIN')")
					.antMatchers("/customer/**").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_ADMIN')")
					.antMatchers("/event/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_CLIENTE')")
					.antMatchers("/pago/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENTE')")
					.antMatchers("/eventPlanner/**").access("hasRole('ROLE_PRESTADOR') or hasRole('ROLE_ADMIN')")
					.antMatchers("/paymentMethod/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENTE')")
					.antMatchers("/review/**").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_ADMIN')")
					.antMatchers("/request/**").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_ADMIN')")					
					 .antMatchers("/welcome/bienvenido").permitAll()
					 .antMatchers("/welcome/nosotros").permitAll()
	
					.antMatchers("/welcome/**").access("hasRole('ROLE_PRESTADOR') or hasRole('ROLE_CLIENTE') or hasRole('ROLE_ADMIN')").and().formLogin()
					
					.successHandler(sucessHandler).loginPage("/login").loginProcessingUrl("/login")
					.defaultSuccessUrl("/welcome/bienvenido1").permitAll().and().logout().logoutSuccessUrl("/login")
					.permitAll().and().exceptionHandling().accessDeniedPage("/welcome/error");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
