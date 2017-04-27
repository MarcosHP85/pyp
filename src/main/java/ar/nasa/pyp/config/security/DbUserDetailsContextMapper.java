package ar.nasa.pyp.config.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import ar.nasa.pyp.domain.Role;
import ar.nasa.pyp.domain.User;
import ar.nasa.pyp.service.RoleServiceImpl;
import ar.nasa.pyp.service.UserServiceImpl;

public class DbUserDetailsContextMapper implements UserDetailsContextMapper {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	RoleServiceImpl roleService;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
			Collection<? extends GrantedAuthority> authorities) {
		
		UserDetails user = null;
		
		try {
			Attributes attr = ctx.getAttributes();
			
			try {
				user = userService.loadUserByUsername(username);
			
			} catch(UsernameNotFoundException e) {
				
				user = crearUsuario(username,
						attr.get("givenName").get(0).toString(),
						attr.get("sn").get(0).toString(),
						attr.get("mail").get(0).toString());
			}
			
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
	
	private UserDetails crearUsuario(String username, String nombre, 
			String apellido, String email) {	
		Role role = roleService.findByName("ROLE_NUEVO");
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);
		
		User tmp = new User(username, true, nombre, apellido, email, roles);
		
		userService.save(tmp);
		
		return (UserDetails) tmp;
	}
}
