package ar.nasa.pyp.rdn.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Reunion")
public class ReunionDiaria {

	@EmbeddedId
	private ReunionId reunionId;
	private Integer idUnidad;
	private Date fechaHora;
	
	public Integer getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Integer unidad) {
		this.idUnidad = unidad;
	}
	
	public Date getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Date fecha) {
		this.fechaHora = fecha;
	}
	
	public ReunionId getReunionId() {
		return reunionId;
	}
	public void setReunionId(ReunionId reunionId) {
		this.reunionId = reunionId;
	}
	
}

@Embeddable
class ReunionId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7141933952377989672L;
	Long idReunion;
	Integer revision;
	
	public Long getIdReunion() {
		return idReunion;
	}
	public void setIdReunion(Long reunionId) {
		this.idReunion = reunionId;
	}
	
	public Integer getRevision() {
		return revision;
	}
	public void setRevision(Integer revision) {
		this.revision = revision;
	}
}