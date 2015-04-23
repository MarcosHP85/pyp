package ar.nasa.pyp.service;

import ar.nasa.pyp.domain.Ot;

public interface OtService {

	public Ot findOne(Integer id);
	public void save(Ot ot);
}
