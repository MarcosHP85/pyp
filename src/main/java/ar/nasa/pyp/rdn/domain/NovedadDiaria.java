package ar.nasa.pyp.rdn.domain;

import javax.persistence.Column;
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
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Column(name = "OT")
	public Long getOt() {
		return ot;
	}
	public void setOt(Long ot) {
		this.ot = ot;
	}
	
	
}
