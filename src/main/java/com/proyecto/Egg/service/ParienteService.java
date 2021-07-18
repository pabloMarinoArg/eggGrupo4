package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Pariente;
import com.proyecto.Egg.repository.PacienteRepository;
import com.proyecto.Egg.repository.ParienteRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    public void crearPariente(String usuario, List<Paciente> listaPaciente){

        Pariente pariente = new Pariente();

        pariente.setUsuario(ur.findById(usuario).orElse(null));
        pariente.setListaPaciente(listaPaciente);

        pr.save(pariente);
    }

    @Transactional
    public void modificarPariente(String id, List<Paciente> listaPaciente){
        pr.modificar(id,listaPaciente);

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
