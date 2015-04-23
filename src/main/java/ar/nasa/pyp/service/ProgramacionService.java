package ar.nasa.pyp.service;

import ar.nasa.pyp.domain.Programacion;

public interface ProgramacionService {

	public Programacion findByTitulo(String titulo);
	public void save(Programacion programacion); 
}
