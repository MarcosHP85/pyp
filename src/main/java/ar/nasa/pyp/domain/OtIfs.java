package ar.nasa.pyp.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ot_ifs")
public class OtIfs {

	@Id
	@Column(name = "num_ot")
	private Integer numOt;
	@Column(name = "semana_plan")
	private Integer semanaPlan;
	@Column(name = "planta_ot")
	private Integer plantaOt;
	@Column(name = "org_mant")
	private String orgMant;
	@Column(name = "ot_madre")
	private Integer otMadre;
	@Column(name = "componente")
	private String componente;
	@Column(name = "tarea")
	private String tarea;
	@Column(name = "posicion")
	private String posicion;
	@Column(name = "planifica")
	private String planifica;
	@Column(name = "requiere_paq_trabajo")
	private String requierePaqTrabajo;
	@Column(name = "resp_tarea")
	private String respTarea;
	@Column(name = "estado_ot")
	private String estadoOt;
	@Column(name = "tipo_trabajo")
	private String tipoTrabajo;
	@Column(name = "prioridad")
	private String prioridad;
	@Column(name = "inicio_prog")
	private Date inicioProg;

	public Date getInicioProg() {
		return inicioProg;
	}

	public void setInicioProg(Date inicioProg) {
		this.inicioProg = inicioProg;
	}

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

	public Integer getOtMadre() {
		return otMadre;
	}

	public void setOtMadre(Integer otMadre) {
		this.otMadre = otMadre;
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
