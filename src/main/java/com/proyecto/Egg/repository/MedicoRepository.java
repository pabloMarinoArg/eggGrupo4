
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Modifying
    @Query("UPDATE Medico m SET m.nombre= :nombre, m.apellido =:apellido, ")
    void modificar(@Param("nombre") String nombre,@Param("apellido") String apellido);

    List<Medico> findMedicoByApellido(String apellido);

}
