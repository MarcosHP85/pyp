package ar.nasa.pyp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.Ot;
import ar.nasa.pyp.domain.OtRepository;

@Service
public class OtServiceImpl implements OtService {

	@Resource
	private OtRepository otRepository;
	
	@Override
	public Ot findOne(Integer id) {
		return otRepository.findOne(id);
	}
	
	@Override
	public void save(Ot ot) {
		otRepository.save(ot);
	}

}
