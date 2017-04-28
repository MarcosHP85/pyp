package ar.nasa.pyp.ifs.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoIfsRepository extends JpaRepository<EmpleadoIfs, String> {

	public EmpleadoIfs findByEmpNo(String ifsUser);
	public EmpleadoIfs findByNameLike(String name);
}
