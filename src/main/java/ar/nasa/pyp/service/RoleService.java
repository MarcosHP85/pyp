package ar.nasa.pyp.service;

import ar.nasa.pyp.domain.Role;

public interface RoleService {

	public Role findByName(String name);
}
