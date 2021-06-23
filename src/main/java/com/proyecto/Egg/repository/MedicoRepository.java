
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    public List<Medico> findMedicoByApellido(String apellido);

}
