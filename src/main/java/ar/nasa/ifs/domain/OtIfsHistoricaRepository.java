package ar.nasa.ifs.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface OtIfsHistoricaRepository extends PagingAndSortingRepository<OtIfsHistorica, Integer> {
		
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAscFechaFinDesc(String componente);
	
}
