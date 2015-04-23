package ar.nasa.pyp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramacionRepository extends JpaRepository<Programacion, Integer> {

	Programacion findByTitulo(String titulo);
}
