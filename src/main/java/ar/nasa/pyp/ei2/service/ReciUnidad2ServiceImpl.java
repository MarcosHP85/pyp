package ar.nasa.pyp.ei2.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ar.nasa.pyp.ei2.domain.ReciUnidad2;
import ar.nasa.pyp.ei2.domain.ReciUnidad2Repository;

@Service
public class ReciUnidad2ServiceImpl implements ReciUnidad2Service {

	@Resource
	ReciUnidad2Repository reciUnidad2Repository;
	
	@Override
	public List<ReciUnidad2> equiposIndisponibles() {
		return reciUnidad2Repository.findByFechaCierreIsNull();
	}


}
