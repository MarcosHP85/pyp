package ar.nasa.pyp.ifs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.ifs.domain.OtIfs;
import ar.nasa.pyp.ifs.domain.OtIfsRepository;


@Service
public class OtIfsServiceImpl implements OtIfsService {

	@Resource
	private OtIfsRepository otIfsRepository;

	@Override
	@Transactional
	public List<OtIfs> list() {
		
		List<OtIfs> reportes = otIfsRepository.findOtIycPlanificacionServNormal();
		
		int unidadi = 0;
		int unidadii = 0;

		for (OtIfs r : reportes) {
			if (r.getPlantaOt() == 2000)
				unidadi++;
			else if (r.getPlantaOt() == 4000)
				unidadii++;
		}
		System.out.println(unidadi + " - " + unidadii + "\n");
		System.out.println(otIfsRepository.findAllMio().get(10));
		return otIfsRepository.findAll();
	}

	@Override
	public Long listCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OtIfs> listPage(Integer page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OtIfs getByOtId(Integer id) {
		return otIfsRepository.findOne(id);
	}

	@Override
	public void saveOrUpdate(OtIfs ot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OtIfs> getByPlantaYSemana(Integer planta, String semana) {
		Collection<Character> prioridades = new ArrayList<Character>();
		prioridades.add('2');
		prioridades.add('3');
		prioridades.add('4');
		return otIfsRepository.findByPlantaOtAndSemanaPlanAndPrioridadIn(planta, semana, prioridades);
	}
	
	@Override
	public List<OtIfs> getParadaByPlantaYSemana(Integer planta, String semana) {
		Collection<Character> prioridades = new ArrayList<Character>();
		prioridades.add('B');
		prioridades.add('C');
		prioridades.add('D');
		return otIfsRepository.findByPlantaOtAndSemanaPlanAndPrioridadIn(planta, semana, prioridades);
	}

}
