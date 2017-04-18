package ar.nasa.pyp.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.nasa.pyp.domain.Role;
import ar.nasa.pyp.domain.User;
import ar.nasa.pyp.service.RoleServiceImpl;
import ar.nasa.pyp.service.UserServiceImpl;

public class LdapDbAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserServiceImpl userService;
	@Autowired
	RoleServiceImpl roleService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails user = null;
		
		try {
			// Busca si el usuario está registrado 
			user = userService.loadUserByUsername(name);
		
		} catch (UsernameNotFoundException e) {
			
			try {
				// Busca en el Active Directory
				user = authenticateLdap(name, password);
				
			} catch (NamingException ne) {
				// No existe en la BD y tampoco se conecta por Ldap
				if ( getSpecificError49Code(ne.getMessage()) == 775 )
					throw new LockedException("Cuenta de usuario bloqueada");
				
				return null;
			}
		}
		
		if (user != null && passwordCorrect(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(user,
					authentication.getCredentials(),
					user.getAuthorities());
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> arg) {
		return true;
	}

	private boolean passwordCorrect(String password, String encodedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(password, encodedPassword);
	}
	
	private UserDetails authenticateLdap(String username, String password) throws NamingException {
		UserDetails user = null;
		User tmp = null;

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
	    try {
	    	Properties props = getLdapProperties(username, password);
	        LdapContext ctx = new InitialLdapContext(props, null);
	        ctx.setRequestControls(null);
	        
	        NamingEnumeration<?> namingEnum = ctx.search(
	        		props.getProperty(Context.PROVIDER_URL),
	        		"(&(objectClass=user)(sAMAccountName=" + username + "))", 
	        		getSimpleSearchControls());
	        
	        while ( namingEnum.hasMore() ) {
	            SearchResult result = (SearchResult) namingEnum.next ();    
	            Attributes attrs = result.getAttributes ();
	            
	            tmp = new User();
		    	tmp.setPassword(encoder.encode(password));
		    	tmp.setUsername(username);
		    	tmp.setEnabled(true);
		    	tmp.setApellido(attrs.get("sn").toString().substring(4));
		    	tmp.setNombre(attrs.get("givenName").toString().substring(11));
		    	tmp.setEmail(attrs.get("mail").toString().substring(6));
		    	
	            System.out.println(attrs.get("sn") + "\t" + attrs.get("mail") + "\t" + attrs.get("givenName") + "\t" + attrs.get("description") + "\t" + getListOU(attrs.get("distinguishedName").toString()));
	            
	        } 
	        namingEnum.close();
	        ctx.close();
	        
	        user = userService.loadUserByUsername(username);
	        
	    } catch (UsernameNotFoundException e) {
	    	Set<Role> roles = new HashSet<Role>();
	    	roles.add(roleService.findByName("ROLE_NUEVO"));
	    	
	    	tmp.setRoles(roles);
	    	userService.save(tmp);
	    	return tmp;
	    }
	    
		return user;
	}
	
	private Properties getLdapProperties(String username, String password) {
		Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        props.put(Context.PROVIDER_URL, "ldap://10.10.1.20:3268/DC=cna1,DC=central,DC=nasa");
        props.put(Context.SECURITY_PRINCIPAL, "CNA1\\" + username);
        props.put(Context.SECURITY_CREDENTIALS, password);
        
        return props;
	}
	
	private static Integer getSpecificError49Code(final String exceptionMsg) {
		String pattern = "\\[LDAP: error code 49.*data (\\d+).*";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(exceptionMsg);
		
		if (m.find()) {
			return Integer.parseInt(m.group(1));
		}
		
		return -1;
	}
	
	private static List<String> getListOU(final String distingish) {
		List<String> list = new ArrayList<String>();
		String pattern = "OU=(.*?),";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(distingish);
		
		while ( m.find() ) {
			list.add(m.group(1));
		}
		return list;
	}
	
	private static SearchControls getSimpleSearchControls() {
		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchControls.setTimeLimit(30000);
		
		String[] attrs = {"sn","mail","givenName","description","distinguishedName"};
		searchControls.setReturningAttributes(attrs);
		
		return searchControls;
	}
}
