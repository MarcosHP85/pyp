package ar.nasa.pyp.ifs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVE_WORK_ORDER_TAB", schema = "IFSATA", catalog = "IFSATA")
public class OtActivaIfs {

	private Long numOt;
	private String componente;
	private String tarea;
	private Character prioridad;
	private String orgCode;
	private String estado;
	private String tipoTrabajo;
	private String comentarioPla;

	@Id
	@Column(name = "WO_NO")
	public Long getNumOt() {
		return numOt;
	}
	public void setNumOt(Long numOt) {
		this.numOt = numOt;
	}
	
	@Column(name = "MCH_CODE")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	
	@Column(name = "ERR_DESCR_LO")
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}
	
	@Column(name = "PRIORITY_ID")
	public Character getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Character prioridad) {
		this.prioridad = prioridad;
	}
	
	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Column(name = "WO_STATUS_ID")
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name = "WORK_TYPE_ID")
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}
	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}
	
	@Column(name = "COM_INT_PLA")
	public String getComentarioPla() {
		return comentarioPla;
	}
	public void setComentarioPla(String comentarioPla) {
		this.comentarioPla = comentarioPla;
	}
}
