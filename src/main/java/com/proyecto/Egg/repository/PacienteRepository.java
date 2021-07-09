
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long>{
    @Modifying
    @Query("UPDATE Paciente p SET p.nombre =:nombre, p.apellido=:apellido,p.nacimiento=:nacimiento WHERE p.dni=:dni")
    void modificar(@Param("dni") Long dni,@Param("nombre") String nombre, @Param("apellido") String apellido,@Param("nacimiento")Date nacimiento);
}
