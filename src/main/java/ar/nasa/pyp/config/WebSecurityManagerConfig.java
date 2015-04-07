package ar.nasa.pyp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.nasa.pyp.service.UserServiceImpl;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityManagerConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserServiceImpl userService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.rememberMe()
        		.key("kdiaovnfawvioanvavclkcnwq")
        		.and()
            .authorizeRequests()
            	.antMatchers("/resources/**").permitAll()
//            	.antMatchers("/indicadores/**").permitAll()
            	.antMatchers("/about").permitAll()
            	.antMatchers("/admin/**").hasRole("ADMIN")
            	.antMatchers("/**").hasRole("ADMIN")
            	.antMatchers("/db/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_DBA')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
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
