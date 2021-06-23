
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {



}
