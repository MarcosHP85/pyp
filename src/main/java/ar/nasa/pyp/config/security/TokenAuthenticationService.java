package ar.nasa.pyp.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000;
	static final String SECRET = "superSecretKey";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	public static void addAuthentication(HttpServletResponse response, String username, Collection<? extends GrantedAuthority> authorities) {
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}
		
		String jwt = Jwts.builder().setSubject(username)
				.claim("roles", roles)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS256, SECRET)//Base64.encode(SECRET.getBytes()))
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);
		// Hace publica la cabecera con el Token
		response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			List<?> roles = (List<?>) Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.get("roles");
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			for(Object role: roles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority((String)role);
				authorities.add(authority);
			}
			
			return user != null ? 
					new UsernamePasswordAuthenticationToken(user, null, authorities) : null;
		}
	
		return null;
	}
	
	
}
