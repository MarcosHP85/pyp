package ar.nasa.pyp.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.nasa.ifs.domain.OtIfs;

@Entity
@Table(name = "ot")
public class Ot {

	private Integer id;
	private String componente;
	private String prioridad;
	private String orgMant;
	private String tipoTrabajo;
	private String tarea;
	private Paquete paquete;
	
	@Id
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "componente")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	
	@Column(name = "prioridad")
	public String getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}
	
	@Column(name = "org_mant")
	public String getOrgMant() {
		return orgMant;
	}
	public void setOrgMant(String orgMant) {
		this.orgMant = orgMant;
	}
	
	@Column(name = "tipo_trabajo")
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}
	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}
	
	@Column(name = "tarea")
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(
			name = "paquete_ot",
			joinColumns = @JoinColumn(name = "ot_id"),
			inverseJoinColumns = @JoinColumn(name = "paquete_id")
	)
	public Paquete getPaquete() {
		return paquete;
	}
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}
	
	public Ot(Integer id, String componente, String prioridad,
			String orgMant, String tipoTrabajo, String tarea) {
		super();
		this.id = id;
		this.componente = componente;
		this.prioridad = prioridad;
		this.orgMant = orgMant;
		this.tipoTrabajo = tipoTrabajo;
		this.tarea = tarea;
	}
	
	public Ot(OtIfs otIfs) {
		super();
		this.id = otIfs.getOtId();
		this.componente = otIfs.getComponente();
		this.prioridad = otIfs.getPrioridad();
		this.orgMant = otIfs.getOrgMant();
		this.tipoTrabajo = otIfs.getTipoTrabajo();
		this.tarea = otIfs.getTarea();
	}
	
	public Ot() {
		super();
	}
	
}
