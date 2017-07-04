package ar.nasa.pyp.rdn.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Novedades")
public class NovedadDiaria {

	private Long id;
	private String descripcion;
	private Long ot;
	
	@Id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getOt() {
		return ot;
	}
	public void setOt(Long ot) {
		this.ot = ot;
	}
	
	
}
