package ar.nasa.pyp.ei1.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReciUnidad1Repository extends CrudRepository<ReciUnidad1, Long> {

	List<ReciUnidad1> findByFechaCierreIsNull();
}
