package ar.nasa.pyp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.ifs.domain.OtIfsHistorica;
import ar.nasa.ifs.domain.OtIfsHistoricaRepository;

@Service
public class OtIfsHistoricaServiceImpl implements OtIfsHistoricaService {

	@Resource
	private OtIfsHistoricaRepository otIfsHistoricaRepository;
	
	@Override
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAsc(String componente) {
		return otIfsHistoricaRepository.findByComponenteLikeOrderByComponenteAscFechaFinDesc(componente);
	}

}
