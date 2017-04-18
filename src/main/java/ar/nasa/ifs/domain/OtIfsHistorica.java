package ar.nasa.ifs.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HISTORICAL_SEPARATE_OVERVIEW", schema = "IFSATA", catalog = "IFSATA")
public class OtIfsHistorica {

	private Integer otId;
	private String semanaPlan;
	private Integer plantaOt;
	private String orgMant;
	private String otMadre;
	private String componente;
	private String tarea;
	private String posicion;
	private String planifica;
	private String requierePaqTrabajo;
	private String respTarea;
	private String tipoTrabajo;
	private Character prioridad;
	private String trabajoRealizado;
	private String realizadoPor;
	private Date fechaInicio;
	private Date fechaFin;

	@Id
	@Column(name = "WO_NO")
	public Integer getOtId() {
		return otId;
	}
	public void setOtId(Integer numOt) {
		this.otId = numOt;
	}

	@Column(name = "PLANNED_WEEK")
	public String getSemanaPlan() {
		return semanaPlan;
	}
	public void setSemanaPlan(String semanaPlan) {
		this.semanaPlan = semanaPlan;
	}

	@Column(name="ORG_CODE")
	public String getOrgMant() {
		return orgMant;
	}
	public void setOrgMant(String orgMant) {
		this.orgMant = orgMant;
	}

	@Column(name = "MCH_CODE")
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}

	@Column(name = "WO_NO_MADRE")
	public String getOtMadre() {
		return otMadre;
	}
	public void setOtMadre(String otMadre) {
		this.otMadre = otMadre;
	}

	@Column(name = "TAREA")
	public String getTarea() {
		return tarea;
	}
	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	@Column(name = "REQ_PAQ_TAB")
	public String getRequierePaqTrabajo() {
		return requierePaqTrabajo;
	}
	public void setRequierePaqTrabajo(String requierePaqTrabajo) {
		this.requierePaqTrabajo = requierePaqTrabajo;
	}

	@Column(name = "WORK_LEADER_SIGN")
	public String getRespTarea() {
		return respTarea;
	}
	public void setRespTarea(String respTarea) {
		this.respTarea = respTarea;
	}

	@Column(name = "CONTRACT")
	public Integer getPlantaOt() {
		return plantaOt;
	}
	public void setPlantaOt(Integer plantaOt) {
		this.plantaOt = plantaOt;
	}

	@Column(name = "MCH_POS")
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	@Column(name = "WORK_TYPE_ID")
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}
	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}

	@Column(name = "PREPARED_BY")
	public String getPlanifica() {
		return planifica;
	}
	public void setPlanifica(String planifica) {
		this.planifica = planifica;
	}
	
	@Column(name = "PRIORITY_ID")
	public Character getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Character prioridad) {
		this.prioridad = prioridad;
	}
	
	@Column(name = "PERFORMED_ACTION_LO")
	public String getTrabajoRealizado() {
		return trabajoRealizado;
	}
	public void setTrabajoRealizado(String trabajoRealizado) {
		this.trabajoRealizado = trabajoRealizado;
	}
	
	@Column(name = "WORK_DONE")
	public String getRealizadoPor() {
		return realizadoPor;
	}
	public void setRealizadoPor(String realizadoPor) {
		this.realizadoPor = realizadoPor;
	}
	
	@Column(name = "REAL_S_DATE")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Column(name = "REAL_F_DATE")
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public String toString() {
		return "OtIfsHistorica [otId=" + otId + ", semanaPlan=" + semanaPlan + ", plantaOt=" + plantaOt + ", orgMant="
				+ orgMant + ", otMadre=" + otMadre + ", componente=" + componente + ", tarea=" + tarea + ", posicion="
				+ posicion + ", planifica=" + planifica + ", requierePaqTrabajo=" + requierePaqTrabajo + ", respTarea="
				+ respTarea + ", tipoTrabajo=" + tipoTrabajo + ", prioridad=" + prioridad + ", trabajoRealizado="
				+ trabajoRealizado + ", realizadoPor=" + realizadoPor + ", fechaInicio=" + fechaInicio + ", fechaFin="
				+ fechaFin + "]";
	}

}
