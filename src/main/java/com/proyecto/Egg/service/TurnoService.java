
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Turno;
import com.proyecto.Egg.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository tr;
    @Autowired
    private UsuarioService us;
    @Autowired
    private PacienteService ps;

    @Transactional
    public void crearTurno(Date fecha,Date hora, Long paciente){
        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setHora(hora);
        turno.setPaciente(ps.buscarPacientePorId(paciente));

        tr.save(turno);
    }
    @Transactional
    public void modificarTurno(Long id, Date fecha, Date hora, Long paciente){
        
        tr.modificar(id,fecha,hora);
        
    }
   /* public void crearTurno(Date fecha,Date hora, String usuario, Long paciente){
        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setHora(hora);
        turno.setUsuario(us.buscarUsuarioPorId(usuario));
        turno.setPaciente(ps.buscarPacientePorId(paciente));

        tr.save(turno);
    }*/

    @Transactional(readOnly = true)
    public List<Turno> listadoTurnos (){

        return tr.findAll();

    }

    @Transactional
    public Turno buscarTurnoPorId(Long id){

        Optional<Turno> turnoOptional = tr.findById(id);

        return  turnoOptional.orElse(null);
    }

    @Transactional
    public void eliminar(Long id){

        tr.deleteById(id);

    }


}
