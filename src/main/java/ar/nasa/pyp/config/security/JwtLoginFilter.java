package ar.nasa.pyp.config.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter implements Filter {

	public JwtLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		AccountCredentials credentials = new ObjectMapper()
				.readValue(request.getInputStream(), AccountCredentials.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						Collections.emptyList())
				);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain filter, Authentication auth) {

		// Se genera y agrega el Token al header
		TokenAuthenticationService.addAuthentication(response, auth.getName(), auth.getAuthorities());
		
		// Se agrega el usuario JSON al response
		response.setContentType("text/x-json;charset=UTF-8");
		try {
			response.getWriter()
				.write(new ObjectMapper().writeValueAsString(auth.getPrincipal()));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
