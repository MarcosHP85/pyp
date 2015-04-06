package ar.nasa.pyp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.nasa.pyp.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findSecurityUserByUsername(String userName);

}
