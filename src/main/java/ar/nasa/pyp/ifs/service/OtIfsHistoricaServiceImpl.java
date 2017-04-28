package ar.nasa.pyp.ifs.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.ifs.domain.OtIfsHistorica;
import ar.nasa.pyp.ifs.domain.OtIfsHistoricaRepository;

@Service
public class OtIfsHistoricaServiceImpl implements OtIfsHistoricaService {

	@Resource
	private OtIfsHistoricaRepository otIfsHistoricaRepository;
	
	@Override
	public List<OtIfsHistorica> findByComponenteLikeOrderByComponenteAsc(String componente) {
		return otIfsHistoricaRepository.findByComponenteLikeOrderByComponenteAscFechaFinDesc(componente);
	}

}
