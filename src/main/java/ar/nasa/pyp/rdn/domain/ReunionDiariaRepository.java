package ar.nasa.pyp.rdn.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReunionDiariaRepository extends CrudRepository<ReunionDiaria, Long> {

	@Query("select r.reunionId.idReunion,r.idUnidad,r.fechaHora,max(r.reunionId.revision) as revision from ReunionDiaria r group by r.reunionId.idReunion,r.idUnidad,r.fechaHora")
	List<ReunionDiaria> findUltimasReuniones();
}
