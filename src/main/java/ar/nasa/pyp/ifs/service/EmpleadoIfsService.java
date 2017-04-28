package ar.nasa.pyp.ifs.service;

import ar.nasa.pyp.ifs.domain.EmpleadoIfs;

public interface EmpleadoIfsService {

	public EmpleadoIfs findByEmpNo(String ifsUser);
	public EmpleadoIfs findByNameLike(String name);
}
