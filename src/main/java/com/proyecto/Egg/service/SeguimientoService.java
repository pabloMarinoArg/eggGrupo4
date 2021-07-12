
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
    private MedicoService ms;

    @Autowired
    private PacienteService ps;

    @Transactional
    public void crearSeguimiento(String comentario, Date fecha, Long medico,Long paciente){
        Seguimiento seguimiento = new Seguimiento();

        seguimiento.setComentario(comentario);
        seguimiento.setFecha(fecha);
      
        seguimiento.setMedico(ms.buscarMedicoPorId(medico));
        seguimiento.setPaciente(ps.buscarPacientePorId(paciente));
        sr.save(seguimiento);
    }
    
    @Transactional
    public void modificarSeguimiento(String id, String comentario,Date fecha){
        sr.modificar(id,comentario, fecha);
           
    }

    @Transactional(readOnly = true)
    public List<Seguimiento> listarSeguimientos (){

        return sr.findAll();

    }
    @Transactional(readOnly = true)
    public List<Seguimiento> buscarSeguimientoPorPaciente(Long paciente){

        List<Seguimiento> seguimientoLista = sr.findSeguimientoByPaciente(ps.buscarPacientePorId(paciente));

        return seguimientoLista;


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
