
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.repository.MedicoRepository;
import com.proyecto.Egg.repository.RolRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository mr;
    @Autowired
    private UsuarioRepository ur;
    @Autowired
    private RolRepository rp;
    @Autowired
    private UsuarioService us;

    private static Pattern p = Pattern.compile("^[a-zA-Z]*$");

    public static boolean isAlpha(String s) {
        return p.matcher(s).find();
    }

    public void validarMedico(String nombre, String apellido) throws ErrorServicio{
        if(!isAlpha(nombre) || !isAlpha(apellido)){
            throw new ErrorServicio("Los datos de nombre/apellido deben tener solo letras");

        }


    }

    @Transactional
    public void crearMedico(Long matricula, String nombre, String apellido, String username,String password, String mail) throws ErrorServicio, MessagingException {
        validarMedico(nombre, apellido);
        Medico medico = new Medico();
        Usuario user  = new Usuario();
        medico.setMatricula(matricula);
        medico.setNombre(nombre);
        medico.setApellido(apellido);
        medico.setUsuario(us.crearUsuarioMetodo(username, password,"MEDICO",mail));

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
