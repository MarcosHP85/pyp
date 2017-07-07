package ar.nasa.pyp.ei1.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.ei1.domain.ReciUnidad1;
import ar.nasa.pyp.ei1.domain.ReciUnidad1Repository;

@Service
public class ReciUnidad1ServiceImpl implements ReciUnidad1Service {

	@Resource
	ReciUnidad1Repository reciUnidad1Repository;
	
	@Override
	public List<ReciUnidad1> equiposIndisponibles() {
		return reciUnidad1Repository.findByFechaCierreIsNull();
	}

}
