package ar.nasa.pyp.service;

import ar.nasa.pyp.ifs.domain.EmpleadoIfs;

public interface EmpleadoIfsService {

	public EmpleadoIfs findByNameLike(String name);
}
