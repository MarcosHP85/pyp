package ar.nasa.pyp.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.User;
import ar.nasa.pyp.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Resource
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		User user = userRepository
				.findSecurityUserByUsername(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("UserName " + userName
					+ " not found");
		}
		return user;
	}
	
	public void save(User user) {
		
		userRepository.save(user);
	}

}
