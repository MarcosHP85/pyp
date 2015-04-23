package ar.nasa.pyp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.domain.Programacion;
import ar.nasa.pyp.domain.ProgramacionRepository;

@Service
public class ProgramacionServiceImpl implements ProgramacionService {

	@Resource
	private ProgramacionRepository programacionRepository;
	
	@Override
	public void save(Programacion programacion) {
		programacionRepository.save(programacion);
	}

	@Override
	public Programacion findByTitulo(String titulo) {
		return programacionRepository.findByTitulo(titulo);
	}

}
