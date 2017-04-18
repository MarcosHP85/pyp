package ar.nasa.pyp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class Role {

	private Integer idRole;
	private String name;
//	private Set<User> users;
	
	public Role() {
	}
	public Role(String name) {
		this.name = name;
	}
	public Role(Integer idRole, String name) {
		this.idRole = idRole;
		this.name = name;
	}
//	public Role(Integer idRole, String name, Set<User> users) {
//		this.idRole = idRole;
//		this.name = name;
//		this.users = users;
//	}
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_role")
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	public Set<User> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
	
	@Override
	public String toString() {
		return "Role [idRole=" + idRole + ", name=" + name + "]";
	}

}
