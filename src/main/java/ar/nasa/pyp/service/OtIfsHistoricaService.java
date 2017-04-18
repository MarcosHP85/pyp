package ar.nasa.pyp.service;

import java.util.List;

import ar.nasa.ifs.domain.OtIfsHistorica;


public interface OtIfsHistoricaService {
	
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAsc(String componente);
}
