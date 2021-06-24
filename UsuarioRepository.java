package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    @Modifying
    @Query("UPDATE Usuario u SET u.username = :username, u.password= :password, u.mail = :mail WHERE u.id = :id")
    void modificar(@Param("id") String id, @Param("username") String username, @Param("password") String password);

    @Query("SELECT u FROM Usuario u WHERE u.username = :username")
    Usuario buscarPorNombreDeUsuario(@Param("username") String username);

}
