
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.MedicoRepository;
import com.proyecto.Egg.repository.RolRepository;
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
    @Autowired
    private RolRepository rp;

    @Transactional
    public void crearMedico(Long matricula, String nombre, String apellido, String username,String password, String mail,String rol){
        Medico medico = new Medico();
        Usuario user  = new Usuario();
        medico.setMatricula(matricula);
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        user.setUsername(username);
        user.setPassword(password);
        user.setMail(mail);
        user.setRol(rp.findById(rol).orElse(null));

        medico.setUsuario(user);
        ur.save(user);
        mr.save(medico);

    }
    
    @Transactional
    public void modificarMedico(Long matricula, String nombre, String apellido){
    
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
