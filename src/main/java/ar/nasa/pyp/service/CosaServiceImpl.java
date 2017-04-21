package ar.nasa.pyp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.Cosa;
import ar.nasa.pyp.domain.CosaRepository;

@Service
public class CosaServiceImpl implements CosaService {

	@Resource
	private CosaRepository cosaRepository;

	@Override
	public Cosa save(Cosa c) {
		// TODO Auto-generated method stub
		return cosaRepository.save(c);
	}
}
