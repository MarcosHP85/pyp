package ar.nasa.pyp.ifs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.ifs.domain.OtActivaIfs;
import ar.nasa.pyp.ifs.domain.OtActivaIfsRepository;

@Service
public class OtActivaIfsServiceImpl implements OtActivaIfsService {

	@Resource
	private OtActivaIfsRepository otActivaIfsRepository;
	
	@Override
	public OtActivaIfs encontrarOtNumero(Long numero) {
		return otActivaIfsRepository.findOne(numero);
	}

	@Override
	public List<OtActivaIfs> searchOts(List<Long> numOts) {
		return otActivaIfsRepository.findByNumOtIn(numOts);
	}

}
