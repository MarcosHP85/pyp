package ar.nasa.pyp.ifs.service;

import java.util.List;

import ar.nasa.pyp.ifs.domain.OtActivaIfs;

public interface OtActivaIfsService {

	public OtActivaIfs encontrarOtNumero(Long numero);
	public List<OtActivaIfs> searchOts(List<Long> numOts);
	public OtActivaIfs actualizarOt(OtActivaIfs ot);
}
