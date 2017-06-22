package ar.nasa.pyp.config.security;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.nasa.pyp.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000;
	static final String SECRET = "superSecretKey";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";
	
	public static void addAuthentication(HttpServletResponse response, User user, Collection<? extends GrantedAuthority> authorities) {
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority authority : authorities) {
			roles.add(authority.getAuthority());
		}

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
	    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
	    
		String jwt = Jwts.builder().setSubject(user.getUsername())
				.claim("roles", roles)
				.claim("principal", user)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(signatureAlgorithm, signingKey)
				.compact();
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + jwt);

		// Hace publica la cabecera con el Token
		response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			
			Claims body = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
			
			String user = body.getSubject();
			ObjectMapper mapper = new ObjectMapper();
			User principal = mapper.convertValue(body.get("principal"), User.class);
			List<?> roles = (List<?>) body.get("roles");
			
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			for(Object role: roles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority((String)role);
				authorities.add(authority);
			}
			
			return user != null ? 
					new UsernamePasswordAuthenticationToken(principal, null, authorities) : null;
		}
	
		return null;
	}
	
	
}
