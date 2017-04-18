package ar.nasa.pyp.config.security;

import java.util.Collection;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import ar.nasa.pyp.service.UserServiceImpl;

public class DbUserDetailsContextMapper implements UserDetailsContextMapper {

	@Autowired
	UserServiceImpl userService;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		
		UserDetails user = userService.loadUserByUsername(username);
		
		try {
			Attributes attr = ctx.getAttributes();
			System.out.println("Desde CustomUserDetailsContextMapper.mapUserFromContext:");
			System.out.println("\t" + attr.get("sn").get(0) + ", " + attr.get("givenName").get(0) + ", " + attr.get("mail").get(0) + ", " + attr.get("description").get(0));
			System.out.println(user);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
		// TODO Auto-generated method stub
		System.out.println();
	}

}
