package ar.nasa.pyp.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "programacion")
public class Programacion {

	private Integer id;
	private String titulo;
	private Integer planta;
	private Date fechaInicio;
	private Date fechaFin;
	private List<Ot> ots;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "programacion_ot",
			joinColumns = @JoinColumn(name = "programacion_id"),
			inverseJoinColumns = @JoinColumn(name = "ot_id")
	)
	public List<Ot> getOts() {
		return ots;
	}
	public void setOts(List<Ot> ots) {
		this.ots = ots;
	}
	
	@Column(name = "titulo")
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name = "planta")
	public Integer getPlanta() {
		return planta;
	}
	public void setPlanta(Integer planta) {
		this.planta = planta;
	}
	
	@Column(name = "f_inicio")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	@Column(name = "f_fin")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
