package ar.nasa.pyp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ar.nasa.pyp.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityManagerConfig extends WebSecurityConfigurerAdapter {

	static String PATH_LOGIN = "/login";
	
	@Autowired
	UserServiceImpl userService;
//	@Autowired
//	DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http
			.sessionManagement()
	        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        	.and()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/report/**").permitAll()
				.antMatchers("/historial").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.POST, PATH_LOGIN).permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
			.cors()
				.and()
			.addFilterBefore(jwtLoginFilter(), 
					UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter(), 
					UsernamePasswordAuthenticationFilter.class);
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
//			auth.authenticationProvider(ldapAuthProvider());
//			auth.authenticationProvider(adAuthProvider());
			auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@Bean
//	public LdapDbAuthenticationProvider ldapAuthProvider() {
//		return new LdapDbAuthenticationProvider();
//	}
	
	@Bean
	public JwtLoginFilter jwtLoginFilter() throws Exception {
		return new JwtLoginFilter(PATH_LOGIN, authenticationManager());
	}
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
	
	@Bean
	public ActiveDirectoryLdapAuthenticationProvider adAuthProvider() {
		ActiveDirectoryLdapAuthenticationProvider activeDirectory = 
				new ActiveDirectoryLdapAuthenticationProvider(
						"cna1.central.nasa",
						"ldap://cna-ad0.cna1.central.nasa",
						"DC=cna1,DC=central,DC=nasa");
		activeDirectory.setUserDetailsContextMapper(userDetailsContextMapper());
		return activeDirectory;
	}
	
	@Bean
	public DbUserDetailsContextMapper userDetailsContextMapper() {
		return new DbUserDetailsContextMapper();
	}
	
//	@Bean
//	public PersistentTokenBasedRememberMeServices rememberMeAuthenticationProvider() {
//		return new PersistentTokenBasedRememberMeServices("springRocks",userService,tokenRepository());
//	}
//	
//	@Bean
//	public PersistentTokenRepository tokenRepository() {
//		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
//		tokenRepository.setDataSource(dataSource);
//		return tokenRepository;
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
