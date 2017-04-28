package ar.nasa.pyp.ifs.service;

import java.util.List;

import ar.nasa.pyp.ifs.domain.OtIfsHistorica;


public interface OtIfsHistoricaService {
	
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAsc(String componente);
}
