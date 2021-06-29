package com.proyecto.Egg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.Egg.entity.Roles;

@Repository
public interface RolRepository extends JpaRepository<Roles, String> {
	@Modifying
	@Query("UPDATE Roles r SET r.nombre WHERE r.id=:id")
	void modificar(@Param("id") String id,@Param("nombre") String nombre);
	Roles findByNombre(String nombre);
}
