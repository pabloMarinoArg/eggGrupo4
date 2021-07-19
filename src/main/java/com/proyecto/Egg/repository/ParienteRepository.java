package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Pariente;
import com.proyecto.Egg.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ParienteRepository extends JpaRepository<Pariente, String> {

    @Modifying
    @Query("UPDATE Pariente p SET  p.nombre =:nombre, p.apellido=:apellido, p.paciente =:paciente WHERE p.id=:id ")
    void modificar(@Param("id") String id,@Param("nombre") String nombre,@Param("apellido") String apellido, @Param("paciente") Paciente paciente);





}
