package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Pariente;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.PacienteRepository;
import com.proyecto.Egg.repository.ParienteRepository;
import com.proyecto.Egg.repository.RolRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;


@Service
public class ParienteService {

    @Autowired
    private ParienteRepository pr;
    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private PacienteRepository par;
    @Autowired
    private RolRepository rr;
    @Autowired
    private UsuarioService usuarioService;


    @Transactional
    public void crearPariente(String username,String password, String mail, String nombre, String apellido , Long paciente) throws MessagingException {

        Pariente pariente = new Pariente();

        pariente.setNombre(nombre);
        pariente.setApellido(apellido);
        pariente.setPaciente(par.findById(paciente).orElse(null));
        pariente.setUsuario(usuarioService.crearUsuarioMetodo(username,password,"PARIENTE",mail));

        pr.save(pariente);

    }

    @Transactional
    public void modificarPariente(String id,String nombre, String apellido, Long paciente){
        pr.modificar(id,nombre,apellido,par.findById(paciente).orElse(null));

    }

    @Transactional
    public void eliminarPariente(String id){
        pr.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Pariente> listarParientes(){

        return pr.findAll();

    }
    @Transactional(readOnly = true)
    public Pariente buscarParientePorId(String id){

        Optional<Pariente> parienteOptional = pr.findById(id);

        return parienteOptional.orElse(null);
    }


}
