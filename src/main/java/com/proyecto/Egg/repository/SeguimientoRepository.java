
package com.proyecto.Egg.repository;

import com.proyecto.Egg.entity.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguimientoRepository extends JpaRepository<Seguimiento, String> {
    
}
