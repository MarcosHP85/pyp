package ar.nasa.pyp.rdn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.rdn.domain.ReunionDiaria;
import ar.nasa.pyp.rdn.domain.ReunionDiariaRepository;

@Service
public class ReunionDiariaServiceImpl implements ReunionDiariaService {

	@Resource
	ReunionDiariaRepository reunionDiariaRepository;
	
	@Override
	public List<ReunionDiaria> ultimasReuniones() {
		return reunionDiariaRepository.findUltimasReuniones();
	}

}
