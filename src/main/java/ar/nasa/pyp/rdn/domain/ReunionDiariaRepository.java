package ar.nasa.pyp.rdn.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReunionDiariaRepository extends CrudRepository<ReunionDiaria, Long> {

	@Query(value = "SELECT top 2 Id,max(Revision) as Revision,IdUnidad,FechaHora FROM Reunion left join Novedades on Reunion.Id = Novedades and Reunion.Revision = Novedades.Revision group by Id,IdUnidad,FechaHora order by FechaHora desc", //SELECT top 2 Id,max(Revision) as Revision,IdUnidad,FechaHora FROM Reunion group by Id,IdUnidad,FechaHora order by FechaHora desc",
			nativeQuery = true)
	List<ReunionDiaria> findUltimasReuniones();
}
