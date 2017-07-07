package ar.nasa.pyp.ei1.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "indis")
public class ReciUnidad1 {

	private Long id;
	private Integer idImportancia;
	private String equipo;
	private String descIndis;
	private Long nroOt;
	private Date fechaCierre;
	
	@Id
	@Column(name = "id_idisponibilidad")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "id_importancia")
	public Integer getIdImportancia() {
		return idImportancia;
	}
	public void setIdImportancia(Integer idImportancia) {
		this.idImportancia = idImportancia;
	}
	
	@Column(name = "equipo")
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	
	@Column(name = "desc_indis")
	public String getDescIndis() {
		return descIndis;
	}
	public void setDescIndis(String descIndis) {
		this.descIndis = descIndis;
	}
	
	@Column(name = "nro_aaot")
	public Long getNroOt() {
		return nroOt;
	}
	public void setNroOt(Long nroOt) {
		this.nroOt = nroOt;
	}
	
	@Column(name = "fecha_cierre_reci")
	public Date getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
}
