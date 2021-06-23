
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pr;

    @Transactional
    public void crearPaciente(Long dni, String nombre, String apellido, Integer edad, Date nacimiento){
        Paciente paciente = new Paciente();
        paciente.setDni(dni);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setEdad(edad);
        paciente.setNacimiento(nacimiento);


        pr.save(paciente);
    }

    @Transactional(readOnly = true)
    public List<Paciente> listadoPacientes (){

        return pr.findAll();
    }

    @Transactional
    public Paciente buscarPacientePorId(Long dni){

        Optional<Paciente> pacienteOptional = pr.findById(dni);

        return  pacienteOptional.orElse(null);

    }


    @Transactional
    public void eliminar (Long dni){
        pr.deleteById(dni);

    }




    
}
