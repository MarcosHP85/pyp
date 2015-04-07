package ar.nasa.ifs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVE_SEPARATE_OVERVIEW", schema = "IFSATA")
public class OtIfs {

	@Id
	@Column(name = "WO_NO")
	private Integer numOt;
	@Column(name = "PLANNED_WEEK")
	private Integer semanaPlan;
	@Column(name = "CONTRACT")
	private Integer plantaOt;
	@Column(name = "ORG_CODE")
	private String orgMant;
	@Column(name = "WO_NO_MADRE")
	private String otMadre;
	@Column(name = "MCH_CODE")
	private String componente;
	@Column(name = "TAREA")
	private String tarea;
	@Column(name = "MCH_POS")
	private String posicion;
	@Column(name = "PREPARED_BY")
	private String planifica;
	@Column(name = "REQ_PAQ_TAB")
	private String requierePaqTrabajo;
	@Column(name = "WORK_LEADER_SIGN")
	private String respTarea;
	@Column(name = "STATE")
	private String estadoOt;
	@Column(name = "WORK_TYPE_ID")
	private String tipoTrabajo;
	@Column(name = "PRIORITY_ID")
	private String prioridad;

	public String getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	public Integer getNumOt() {
		return numOt;
	}

	public void setNumOt(Integer numOt) {
		this.numOt = numOt;
	}

	public Integer getSemanaPlan() {
		return semanaPlan;
	}

	public void setSemanaPlan(Integer semanaPlan) {
		this.semanaPlan = semanaPlan;
	}

	public String getOrgMant() {
		return orgMant;
	}

	public void setOrgMant(String orgMant) {
		this.orgMant = orgMant;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getRequierePaqTrabajo() {
		return requierePaqTrabajo;
	}

	public void setRequierePaqTrabajo(String requierePaqTrabajo) {
		this.requierePaqTrabajo = requierePaqTrabajo;
	}

	public String getRespTarea() {
		return respTarea;
	}

	public void setRespTarea(String respTarea) {
		this.respTarea = respTarea;
	}

	public String getEstadoOt() {
		return estadoOt;
	}

	public void setEstadoOt(String estadoOt) {
		this.estadoOt = estadoOt;
	}

	@Override
	public String toString() {
		return "Ot [numOt=" + numOt + ", semanaPlan=" + semanaPlan
				+ ", orgMant=" + orgMant + ", otMadre=" + otMadre
				+ ", componente=" + componente + ", tarea=" + tarea
				+ ", requierePaqTrabajo=" + requierePaqTrabajo + ", respTarea="
				+ respTarea + ", estadoOt=" + estadoOt + "]";
	}

	public Integer getPlantaOt() {
		return plantaOt;
	}

	public void setPlantaOt(Integer plantaOt) {
		this.plantaOt = plantaOt;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}

	public String getPlanifica() {
		return planifica;
	}

	public void setPlanifica(String planifica) {
		this.planifica = planifica;
	}
}
