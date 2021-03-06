package ar.nasa.pyp.ifs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "ACTIVE_WORK_ORDER_TAB", schema = "IFSATA", catalog = "IFSATA")
@DynamicUpdate
public class OtActivaIfs {

	private Long numOt;
	private String componente;
	private String tarea;
	private String error;
	private Character prioridad;
	private String orgCode;
	private String estado;
	private String tipoTrabajo;
	private String comentarioPla;
	private String directiva;
	private Date fechaRegistro;
	private String planifica;

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
	
	@Column(name = "WORK_DESCR_LO")
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
	
	@Column(name = "ERR_DESCR")
	public String getDirectiva() {
		return directiva;
	}
	public void setDirectiva(String directiva) {
		this.directiva = directiva;
	}
	
	@Column(name = "REG_DATE")
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@Column(name = "ERR_DESCR_LO")
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	@Column(name = "PREPARED_BY_ID")
	public String getPlanifica() {
		return planifica;
	}
	public void setPlanifica(String planifica) {
		this.planifica = planifica;
	}
}
