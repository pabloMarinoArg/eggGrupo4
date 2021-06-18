package com.proyecto.Egg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Egg.entity.Roles;

@Repository
public interface RolRepository extends JpaRepository<Roles, String> {

	public Roles findByNombre(String nombre);
}
