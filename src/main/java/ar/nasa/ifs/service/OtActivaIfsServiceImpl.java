package ar.nasa.ifs.service;

import javax.annotation.Resource;

import ar.nasa.ifs.domain.OtActivaIfs;
import ar.nasa.ifs.domain.OtActivaIfsRepository;

public class OtActivaIfsServiceImpl implements OtActivaIfsService {

	@Resource
	private OtActivaIfsRepository otActivaIfsRepository;
	
	@Override
	public OtActivaIfs encontrarOtNumero(Long numero) {
		return otActivaIfsRepository.findOne(numero);
	}

}
