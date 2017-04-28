package ar.nasa.ifs.service;

import ar.nasa.ifs.domain.EmpleadoIfs;

public interface EmpleadoIfsService {

	public EmpleadoIfs findByEmpNo(String ifsUser);
	public EmpleadoIfs findByNameLike(String name);
}
