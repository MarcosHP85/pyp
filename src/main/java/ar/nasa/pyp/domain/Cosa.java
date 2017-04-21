package ar.nasa.pyp.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cosa")
public class Cosa {

	private Integer idCosa;
	private String name;
	private List<CosaAudited> auditeds;
    
	@CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime updatedOn;
    
	@Id
	@JsonIgnore
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cosa")
	public Integer getIdCosa() {
		return idCosa;
	}
	public void setIdCosa(Integer idCosa) {
		this.idCosa = idCosa;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Cosa [idCosa=" + idCosa + ", name=" + name + ", auditeds=" + auditeds + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
	
	@OneToMany(mappedBy="cosa")
	public List<CosaAudited> getAuditeds() {
		return auditeds;
	}
	public void setAuditeds(List<CosaAudited> auditeds) {
		this.auditeds = auditeds;
	}
	

	
}
