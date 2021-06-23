
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository mr;

    @Transactional
    public void crearMedico(Long matricula, String nombre, String apellido){
        Medico medico = new Medico();
        medico.setMatricula(matricula);
        medico.setNombre(nombre);
        medico.setApellido(apellido);


        mr.save(medico);
    }

    @Transactional(readOnly = true)
    public List<Medico> listarMedicoPorApellido(String apellido){

        return  mr.findMedicoByApellido(apellido);

    }

    @Transactional(readOnly = true)
    public List<Medico> listadoMedicos(){

        return mr.findAll();
    }
    @Transactional
    public void eliminar(Long matricula){

        mr.deleteById(matricula);
    }

    @Transactional
    public Medico buscarMedicoPorId(Long matricula){

        Optional<Medico> medicoOptional = mr.findById(matricula);

        return medicoOptional.orElse(null);
    }
}
