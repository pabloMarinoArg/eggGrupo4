
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, String> {
    @Modifying
    @Query("UPDATE seguimiento s SET s.comentario=:comentario,s.fecha=:fecha WHERE s.id=:id")
    void modificar(@Param("id") String id,@Param("comentario") String comentario,@Param("fecha") Date fecha);
}
