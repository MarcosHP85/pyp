package ar.nasa.pyp.domain;

import java.util.Date;

public class OtDoce {

	private Integer id;
	private Integer semana;
	private String componente;
	private String orgMant;
	private String observaciones;
	private Date fechaInicio;
	private Date fechaFin;
	private Boolean lunes = false;
	private Boolean martes = false;
	private Boolean miercoles = false;
	private Boolean jueves = false;
	private Boolean viernes = false;
	private Boolean sabado = false;
	private Boolean domingo = false;
	
	public OtDoce() {
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSemana() {
		return semana;
	}
	public void setSemana(Integer semana) {
		this.semana = semana;
	}
	public String getComponente() {
		return componente;
	}
	public void setComponente(String componente) {
		this.componente = componente;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getLunes() {
		return lunes;
	}
	public void setLunes(Boolean lunes) {
		this.lunes = lunes;
	}
	public Boolean getMartes() {
		return martes;
	}
	public void setMartes(Boolean martes) {
		this.martes = martes;
	}
	public Boolean getMiercoles() {
		return miercoles;
	}
	public void setMiercoles(Boolean miercoles) {
		this.miercoles = miercoles;
	}
	public Boolean getJueves() {
		return jueves;
	}
	public void setJueves(Boolean jueves) {
		this.jueves = jueves;
	}
	public Boolean getViernes() {
		return viernes;
	}
	public void setViernes(Boolean viernes) {
		this.viernes = viernes;
	}
	public Boolean getSabado() {
		return sabado;
	}
	public void setSabado(Boolean sabado) {
		this.sabado = sabado;
	}
	public Boolean getDomingo() {
		return domingo;
	}
	public void setDomingo(Boolean domingo) {
		this.domingo = domingo;
	}
	public String getOrgMant() {
		return orgMant;
	}
	public void setOrgMant(String orgMant) {
		this.orgMant = orgMant;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Override
	public String toString() {
		return "OtDoce [id=" + id + ", semana=" + semana + ", componente=" + componente + ", orgMant=" + orgMant
				+ ", observaciones=" + observaciones + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", lunes=" + lunes + ", martes=" + martes + ", miercoles=" + miercoles + ", jueves=" + jueves
				+ ", viernes=" + viernes + ", sabado=" + sabado + ", domingo=" + domingo + "]";
	}

}
