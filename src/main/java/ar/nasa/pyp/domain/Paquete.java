package ar.nasa.pyp.domain;

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

@Entity
@Table(name = "paquete")
public class Paquete {

	private Integer id;
	private Set<Ot> ots;
	
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
			name = "paquete_ot",
			joinColumns = @JoinColumn(name = "paquete_id"),
			inverseJoinColumns = @JoinColumn(name = "ot_id")
	)
	public Set<Ot> getOts() {
		return ots;
	}
	public void setOts(Set<Ot> ots) {
		this.ots = ots;
	}
	
	public Paquete() {
		super();
	}
}
