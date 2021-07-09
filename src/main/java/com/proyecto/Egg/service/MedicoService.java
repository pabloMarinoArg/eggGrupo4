
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.repository.MedicoRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository mr;
    @Autowired
    private UsuarioRepository ur;

    @Transactional
    public void crearMedico(Long matricula, String nombre, String apellido, String usuario){
        Medico medico = new Medico();
        medico.setMatricula(matricula);
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setUsuario(ur.findById(usuario).orElse(null));


        mr.save(medico);
    }
    
    @Transactional
    public void modificarMedico(Long matricula, String nombre, String apellido, String usuario){
    
        mr.modificar(matricula,nombre,apellido);
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
