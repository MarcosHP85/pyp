package ar.nasa.pyp.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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
	public OtIfs get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(OtIfs ot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
