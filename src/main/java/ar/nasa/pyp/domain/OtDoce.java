package ar.nasa.pyp.domain;

public class OtDoce {

	private Integer id;
	private Integer semana;
	private String observaciones;
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

	@Override
	public String toString() {
		return "OtDoce [id=" + id + ", semana=" + semana + ", observaciones="
				+ observaciones + ", lunes=" + lunes + ", martes=" + martes
				+ ", miercoles=" + miercoles + ", jueves=" + jueves
				+ ", viernes=" + viernes + ", sabado=" + sabado + ", domingo="
				+ domingo + "]";
	}
	
}
