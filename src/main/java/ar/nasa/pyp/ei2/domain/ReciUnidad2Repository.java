package ar.nasa.pyp.ei2.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ReciUnidad2Repository extends CrudRepository<ReciUnidad2, Long> {

	List<ReciUnidad2> findByFechaCierreIsNull();
}
