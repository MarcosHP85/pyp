package ar.nasa.pyp.rdn.domain;

import java.io.Serializable;

class ReunionId implements Serializable {
	
	private static final long serialVersionUID = 7141933952377989672L;
	Long id;
	Integer revision;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getRevision() {
		return revision;
	}
	public void setRevision(Integer revision) {
		this.revision = revision;
	}
	
	public ReunionId() {
		super();
	}
	
	public ReunionId(Long id, Integer revision) {
		super();
		this.id = id;
		this.revision = revision;
	}
}