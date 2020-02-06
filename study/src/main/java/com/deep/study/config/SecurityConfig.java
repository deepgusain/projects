package com.deep.study.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String[] OPEN_PATHS = { "/", "/admin/login", "/user/updatePassword/**", "/admin/publicApi/**",
			"/user/publicApi/**" };

	@Autowired
	private DataSource dataSource;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	RestAuthenticationEntryPoint restEntryPoint;

	// @Autowired
	// CustomCorsFilter customCorsFilter;

	@Autowired
	JwtAuthenticationFilter jwtAuthFilter;

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
		web.ignoring().antMatchers(OPEN_PATHS);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(OPEN_PATHS).permitAll().anyRequest().authenticated()
				// .and().csrf().disable()
				.and().httpBasic();

		/**
		 * Add jwt filter to extract token and inspect
		 */
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		/**
		 * Entry point for un-authenticated requests
		 */
		http.exceptionHandling().authenticationEntryPoint(restEntryPoint);
		/**
		 * CORS configuration
		 */
		// http.cors();
		// http.addFilterBefore(customCorsFilter,
		// ChannelProcessingFilter.class);

		/**
		 * Shallow Etag filter to add response headers
		 */
		ShallowEtagHeaderFilter etagFilter = new ShallowEtagHeaderFilter();
		http.addFilterBefore(etagFilter, UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable();

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());

		// need to check with jdbc
		/*
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .passwordEncoder(encoder())
		 * .usersByUsernameQuery("select name as username, password, 'true' as enabled from User where name=?"
		 * )
		 * .authoritiesByUsernameQuery("select u.name as username,r.name as authority  from Role r ,User u, user_role ur where u.id=ur.user_id and r.id=ur.role_id and u.name=?"
		 * );
		 */

		// hard coded with encoded
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("admin").password(encoder().encode("admin")).roles("ADMIN")
		 * .and()
		 * .withUser("user").password(encoder().encode("user")).roles("USER");
		 */

		// hard coded user, pass and role
		/*
		 * UserBuilder users = User.withDefaultPasswordEncoder();
		 * auth.inMemoryAuthentication()
		 * .withUser(users.username("admin").password("admin").roles("ADMIN"))
		 * .withUser(users.username("user").password("user").roles("USER"));
		 */
	}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() { final
	 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	 * authProvider.setUserDetailsService(userDetailsService);
	 * authProvider.setPasswordEncoder(encoder()); return authProvider; }
	 */

	// used for encoding
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(12);
	}

	/*
	 * @Bean public UserDetailsService userDetailsService(){ JdbcDaoImpl
	 * jdbcDaoImpl= new JdbcDaoImpl(); jdbcDaoImpl.setDataSource(dataSource);
	 * jdbcDaoImpl.
	 * setUsersByUsernameQuery("select name, password, 'true' as enabled from User where name=?"
	 * ); jdbcDaoImpl.
	 * setAuthoritiesByUsernameQuery("select u.name,r.name from Role r ,User u, user_role ur where u.id=ur.user_id and r.id=ur.role_id and u.name="
	 * ); return jdbcDaoImpl; }
	 */

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encoded = bCryptPasswordEncoder.encode("admin");
		System.out.println(encoded);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
