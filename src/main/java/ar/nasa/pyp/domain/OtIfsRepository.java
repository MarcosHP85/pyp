package ar.nasa.pyp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OtIfsRepository extends PagingAndSortingRepository<OtIfs, Integer> {

	@Query("select o from OtIfs o where o.planifica = 'MPARODI'")
	List<OtIfs> findAllMio();
	
	@Query("SELECT o FROM OtIfs o "
			+ "WHERE o.orgMant like 'C%' and o.estadoOt = 'Planificacion' and o.tipoTrabajo in ('COR','ELE','OTR','TAP') and o.prioridad in ('2','3','4')")
	List<OtIfs> findOtIycPlanificacionServNormal();
	
	@Query("SELECT o FROM OtIfs o "
			+ "WHERE o.orgMant like 'C%' and o.estadoOt = 'Planificacion' and o.tipoTrabajo in ('COR','ELE','OTR','TAP') and o.prioridad in ('A','B','C','D','E')")
	List<OtIfs> findOtIycPlanificacionParada();
	
	@Procedure("IFSATA.CHECK_PRIVILEGIES")
	void getObject();
	
}
