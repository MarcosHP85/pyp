package ar.nasa.pyp.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ar.nasa.ifs.domain.OtIfs;
import ar.nasa.ifs.domain.OtIfsRepository;


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
		return otIfsRepository.findAllMio();
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
	public Page<OtIfs> getServicioNormalIycSemana(Integer semana, Pageable pageable) {
		Collection<Character> prioridades = new HashSet<Character>();
		prioridades.add('2');
		prioridades.add('3');
		prioridades.add('4');
		return otIfsRepository.findByPrioridadInAndOrgMantStartingWithAndSemanaPlan(prioridades, 'C', semana, pageable);
		
	}
	
	@Override
	public Set<OtIfs> getServicioNormalIycSemana(Integer semana) {
		Collection<Character> prioridades = new HashSet<Character>();
		prioridades.add('2');
		prioridades.add('3');
		prioridades.add('4');
		return otIfsRepository.findByPrioridadInAndOrgMantStartingWithAndSemanaPlan(prioridades, 'C', semana);
		
	}


}
