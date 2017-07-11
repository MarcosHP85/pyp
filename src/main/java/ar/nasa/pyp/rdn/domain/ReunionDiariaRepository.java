package ar.nasa.pyp.rdn.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReunionDiariaRepository extends CrudRepository<ReunionDiaria, Long> {

	@Query(value = "SELECT DISTINCT reunion "
			+ "FROM ReunionDiaria reunion"
			+ " JOIN FETCH reunion.novedades n"
			+ " JOIN FETCH n.equipo "
			+ "WHERE reunion.fechaHora = (SELECT MAX(r.fechaHora) FROM ReunionDiaria r)"
			+ " AND reunion.revision >= ALL(SELECT rr.revision FROM ReunionDiaria rr WHERE rr.id = reunion.id)")
	List<ReunionDiaria> findUltimasReuniones();
}
