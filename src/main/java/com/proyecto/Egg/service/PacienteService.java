
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pr;



    public void validarPaciente(Long dni, String nombre, String apellido, Date nacimiento) throws ErrorServicio{

        if(dni < 1000000){
            throw new ErrorServicio("El dni no puede ser menor a 1 millón");
        }
        if(!nombre.matches("[a-zA-Z]") && !apellido.matches("[a-zA-Z]")){
            throw new ErrorServicio("Los datos de nombre/apellido deben tener solo letras");
        }
        if(calcularEdad(nacimiento)<18){
            throw new ErrorServicio("No se permite el internado de menores a un hogar de adultos");
        }

    }


    @Transactional
    public void crearPaciente(Long dni, String nombre, String apellido, Date nacimiento) throws ErrorServicio {
        Paciente paciente = new Paciente();
        validarPaciente(dni, nombre, apellido, nacimiento);
        paciente.setDni(dni);
        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        //paciente.setEdad(edad);
        paciente.setNacimiento(nacimiento);


        pr.save(paciente);
    }

    public Long calcularEdad(Date nacimiento){
        /*Se transforma el Date a localdate*/
        LocalDate nac =  nacimiento.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
        Long edad = ChronoUnit.YEARS.between(nac,LocalDate.now());

        return edad;
    }

    @Transactional
    public void modificarPaciente(Long dni, String nombre, String apellido, Date nacimiento){
        pr.modificar(dni,nombre,apellido,nacimiento);
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
