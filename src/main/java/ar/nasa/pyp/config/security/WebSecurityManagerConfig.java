package ar.nasa.pyp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ar.nasa.pyp.config.AuditorAwareImpl;
import ar.nasa.pyp.domain.User;
import ar.nasa.pyp.service.UserServiceImpl;

@Configuration
@EnableJpaAuditing
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityManagerConfig extends WebSecurityConfigurerAdapter {

	static String PATH_LOGIN = "/login";

	static String LDAP_DOMAIN = "cna1.central.nasa";
	static String LDAP_URL = "ldap://cna-ad0.cna1.central.nasa";
	static String LDAP_ROOT_DN = "DC=cna1,DC=central,DC=nasa";
	
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private Environment environment;
	
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
				.antMatchers("/json/**").permitAll()
				.antMatchers("/report/**").permitAll()
				.antMatchers("/historial").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.POST, PATH_LOGIN).permitAll()
				.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
			.cors()
				.and()
			.addFilterBefore(new JwtLoginFilter(PATH_LOGIN, authenticationManager()), 
					UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JwtAuthenticationFilter(),
					UsernamePasswordAuthenticationFilter.class);
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			for (String profile : environment.getActiveProfiles()) {
				if (profile.equals("ifs")) {
					auth.authenticationProvider(adAuthProvider());
					break;
				}
			}
			auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
	    return new AuditorAwareImpl();
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
						LDAP_DOMAIN,
						LDAP_URL,
						LDAP_ROOT_DN);
		activeDirectory.setUserDetailsContextMapper(userDetailsContextMapper());
		return activeDirectory;
	}

	@Bean
	public DbUserDetailsContextMapper userDetailsContextMapper() {
		return new DbUserDetailsContextMapper();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
