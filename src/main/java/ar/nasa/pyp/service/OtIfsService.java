package ar.nasa.pyp.service;

import java.util.List;

import ar.nasa.ifs.domain.OtIfs;


public interface OtIfsService {
	
	public OtIfs getByOtId(Integer id);
	
	public List<OtIfs> list();
	public Long listCount();
	public List<OtIfs> listPage(Integer page);
	
	public void saveOrUpdate(OtIfs ot);
	
	public void delete(int id);
}
