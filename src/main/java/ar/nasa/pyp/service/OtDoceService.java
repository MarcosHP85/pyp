package ar.nasa.pyp.service;

import java.util.List;

import ar.nasa.pyp.domain.OtDoce;

public interface OtDoceService {

	public List<OtDoce> findBySemana(Integer semana);
	public List<OtDoce> findByPlantaAndSemana(Integer planta, Integer semana);
	public List<OtDoce>	findByOtIn(List<Long> ots);
}
