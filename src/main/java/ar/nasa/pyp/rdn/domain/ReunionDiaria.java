package ar.nasa.pyp.rdn.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Reunion", schema = "dbo", catalog = "ReunionDiaria")
@IdClass(ReunionId.class)
public class ReunionDiaria {

	private Long id;
	private Integer revision;
	private Integer idUnidad;
	private Date fechaHora;
	private List<NovedadDiaria> novedades;
	
	public ReunionDiaria() {
		super();
	}
	
	@Id
	@Column(name = "Id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "Revision")
	public Integer getRevision() {
		return revision;
	}
	public void setRevision(Integer revision) {
		this.revision = revision;
	}
	
	@Column(name = "IdUnidad")
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer unidad) {
		this.idUnidad = unidad;
	}
	
	@Column(name = "FechaHora")
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fecha) {
		this.fechaHora = fecha;
	}

	@OneToMany
	@JoinColumns({
		@JoinColumn(name = "IdReunion"),
		@JoinColumn(name = "Revision")
	})
	public List<NovedadDiaria> getNovedades() {
		return novedades;
	}
	public void setNovedades(List<NovedadDiaria> novedades) {
		this.novedades = novedades;
	}

}
