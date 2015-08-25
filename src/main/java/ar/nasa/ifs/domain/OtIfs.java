package ar.nasa.ifs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ACTIVE_SEPARATE_OVERVIEW", schema = "IFSATA")
public class OtIfs {

	private Integer otId;
	private String semanaPlan;
	private Integer plantaOt;
	private OrgMantIfs orgMant;
	private String otMadre;
	private String componente;
	private String tarea;
	private String posicion;
	private String planifica;
	private String requierePaqTrabajo;
	private String respTarea;
	private String estadoOt;
	private String tipoTrabajo;
	private Character prioridad;

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

	@ManyToOne
	@JoinColumn(name="ORG_CODE")
	public OrgMantIfs getOrgMant() {
		return orgMant;
	}
	public void setOrgMant(OrgMantIfs orgMant) {
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

	@Column(name = "STATE")
	public String getEstadoOt() {
		return estadoOt;
	}
	public void setEstadoOt(String estadoOt) {
		this.estadoOt = estadoOt;
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
	
	@Override
	public String toString() {
		return "Ot [numOt=" + otId + ", semanaPlan=" + semanaPlan
				+ ", orgMant=" + orgMant + ", otMadre=" + otMadre
				+ ", componente=" + componente + ", tarea=" + tarea
				+ ", requierePaqTrabajo=" + requierePaqTrabajo + ", respTarea="
				+ respTarea + ", estadoOt=" + estadoOt + "]";
	}
}
