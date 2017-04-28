package ar.nasa.pyp.ifs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVE_WORK_ORDER_TAB", schema = "IFSATA", catalog = "IFSATA")
public class OtActivaIfs {

	private Long id;
	private String componente;

	@Id
	@Column(name = "WO_NO")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "MCH_CODE")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
}
