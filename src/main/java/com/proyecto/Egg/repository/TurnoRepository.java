
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{
    @Modifying
    @Query("UPDATE Turno t SET t.fecha = :fecha, t.hora= :hora, t.mail = :mail WHERE u.id = :id")
    void modificar(@Param("id") String id, @Param("username") String username, @Param("password") String password);

    //@Query("SELECT u FROM Usuario u WHERE u.username = :username")
    //Turno buscarPorPaciente(@Param("pacienteVisitado") PacienteVisitado pacienteVisitado);
}
