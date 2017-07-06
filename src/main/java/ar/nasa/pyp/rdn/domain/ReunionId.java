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
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ReunionId reunion = (ReunionId) obj;
		if (revision != reunion.revision)
			return false;
		if (id != reunion.id)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 1;
		hash = 31 * hash + revision.hashCode();
		hash = 31 * hash + id.hashCode();
		
		return hash;
	}
}