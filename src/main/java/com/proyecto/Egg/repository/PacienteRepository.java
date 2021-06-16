
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository  extends JpaRepository<Paciente, Long>{
    
}
