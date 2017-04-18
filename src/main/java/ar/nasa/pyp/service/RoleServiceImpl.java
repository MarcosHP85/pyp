package ar.nasa.pyp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.Role;
import ar.nasa.pyp.domain.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleRepository roleRepository;
	
	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

}
