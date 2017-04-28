package ar.nasa.pyp.service;

import javax.annotation.Resource;

import ar.nasa.pyp.ifs.domain.EmpleadoIfs;
import ar.nasa.pyp.ifs.domain.EmpleadoIfsRepository;

public class EmpleadoIfsServiceImpl implements EmpleadoIfsService {

	@Resource
	private EmpleadoIfsRepository empleadoIfsRepository;
	
	@Override
	public EmpleadoIfs findByNameLike(String name) {
		return empleadoIfsRepository.findByNameLike(name);
	}

}
