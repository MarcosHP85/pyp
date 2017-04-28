package ar.nasa.ifs.service;

import java.util.List;

import ar.nasa.ifs.domain.OtIfsHistorica;


public interface OtIfsHistoricaService {
	
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAsc(String componente);
}
