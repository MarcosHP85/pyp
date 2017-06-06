package ar.nasa.pyp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.OtDoce;
import ar.nasa.pyp.domain.OtDoceRepository;

@Service
public class OtDoceServiceImpl implements OtDoceService {

	@Resource
	private OtDoceRepository otDoceRepository;
		
	@Override
	public List<OtDoce> findBySemana(Integer semana, Integer planta) {
		return otDoceRepository.findBySemana(semana);
	}

}
