package ar.nasa.pyp.ifs.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface OtActivaIfsRepository extends PagingAndSortingRepository<OtActivaIfs, Long> {

	public List<OtActivaIfs> findByNumOtIn(List<Long> numOts);
}
