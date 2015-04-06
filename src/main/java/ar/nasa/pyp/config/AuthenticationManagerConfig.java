package ar.nasa.pyp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.nasa.pyp.service.UserServiceImpl;

@Configuration
public class AuthenticationManagerConfig extends
		GlobalAuthenticationConfigurerAdapter {

	@Autowired
	UserServiceImpl userService;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
