
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TurnoRepository extends JpaRepository<Turno,Long>{
    @Modifying
    @Query("UPDATE Turno t SET t.fecha = :fecha, t.hora= :hora WHERE t.id = :id")
    void modificar(@Param("id") Long id, @Param("fecha") Date fecha, @Param("hora") Date hora);

    List<Turno> findTurnoByFecha(Date fecha);
    //@Query("SELECT u FROM Usuario u WHERE u.username = :username")
    //Turno buscarPorPaciente(@Param("pacienteVisitado") PacienteVisitado pacienteVisitado);
}
