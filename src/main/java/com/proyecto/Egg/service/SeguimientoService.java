
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.repository.SeguimientoRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SeguimientoService {

    @Autowired
    private SeguimientoRepository sr;

    @Autowired
    private UsuarioService us;

    @Autowired
    private MedicoService ms;

    @Transactional
    public void crearSeguimiento(String comentario, Date fecha, String usuario, Long matricula){
        Seguimiento seguimiento = new Seguimiento();

        seguimiento.setComentario(comentario);
        seguimiento.setFecha(fecha);
        seguimiento.setUsuario(us.buscarUsuarioPorId(usuario));
        seguimiento.setMedico(ms.buscarMedicoPorId(matricula));

        sr.save(seguimiento);
    }

    @Transactional(readOnly = true)
    public List<Seguimiento> listarSeguimientos (){

        return sr.findAll();

    }

    @Transactional
    public Seguimiento buscarSeguimientoPorId(String id){

        Optional<Seguimiento> seguimientoOptional = sr.findById(id);

        return seguimientoOptional.orElse(null);
    }

    @Transactional
    public void eliminar (String id){

        sr.deleteById(id);

    }
    
}
