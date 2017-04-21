package ar.nasa.pyp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cosa_audited")
public class CosaAudited {

	public CosaAudited(String createdBy, String updatedBy, LocalDateTime createdOn,
			LocalDateTime updatedOn) {
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	public CosaAudited() {
		// TODO Auto-generated constructor stub
	}
	private Long idCosaAudited;
	private Cosa cosa;
	@CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
    @CreatedDate
    private LocalDateTime createdOn;
    @LastModifiedDate
    private LocalDateTime updatedOn;
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_cosa_audited")
	public Long getIdCosaAudited() {
		return idCosaAudited;
	}
	public void setIdCosaAudited(Long idCosaAudited) {
		this.idCosaAudited = idCosaAudited;
	}

	@Override
	public String toString() {
		return "CosaAudited [idCosaAudited=" + idCosaAudited + ", cosa=" + cosa + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cosa_id")
	public Cosa getCosa() {
		return cosa;
	}
	public void setCosa(Cosa cosa) {
		this.cosa = cosa;
	}
    
}
