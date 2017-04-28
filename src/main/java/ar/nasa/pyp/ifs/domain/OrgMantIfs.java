package ar.nasa.pyp.ifs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORG_CODE_ALLOWED_SITE_LOV", schema = "IFSATA", catalog = "IFSATA")
public class OrgMantIfs {

	private String orgMantId;
	private String descripcion;
	
	@Id
	@Column(name = "ORG_CODE")
	public String getOrgMantId() {
		return orgMantId;
	}
	public void setOrgMantId(String orgMantId) {
		this.orgMantId = orgMantId;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "OrgMantIfs [orgMantId=" + orgMantId + ", descripcion=" + descripcion + "]";
	}
}
