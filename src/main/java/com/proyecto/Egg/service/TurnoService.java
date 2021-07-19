
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Turno;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
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

    public LocalDate conversorFecha(Date a){
        LocalDate b =  a.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return b;

    }

    public LocalTime conversorHora(Date h){
        LocalTime f =  h.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalTime();
        return f;

    }

    public Boolean isBetweenHourRange(LocalTime t){
        LocalTime start = LocalTime.of(0,0);
        LocalTime end = LocalTime.of(7,0);
        LocalTime startN = LocalTime.of(22,0);
        LocalTime endN = LocalTime.of(23,0);

        Boolean isNowInRange = Boolean.FALSE;

        if(t.equals(start)  ){
           return isNowInRange = Boolean.TRUE;

        }else {
            if(t.isAfter(start) && t.isBefore(end)){
                return     isNowInRange=Boolean.TRUE;

            }else {
                if(t.equals(startN)){
                    return isNowInRange = Boolean.TRUE;

                }else {
                    if(t.isAfter(startN) && t.isBefore(endN)){
                        return isNowInRange = Boolean.TRUE;

                    }


                }

            }


        }




        return isNowInRange;

    }

    public void validarTurno(Date fecha, Date hora) throws ErrorServicio {
        LocalDate fec = conversorFecha(fecha);
        LocalTime hor = conversorHora(hora);

        if( fec.isBefore(LocalDate.now()) ){
            throw new ErrorServicio("La fecha del turno no puede ser anterior a la actual");

        }
        if(isBetweenHourRange(hor)){
            throw  new ErrorServicio("No se pueden realizar visitas entre las 22 y 07 hs");
        }


    }


    @Transactional
    public void crearTurno(Date fecha,Date hora, Long paciente) throws ErrorServicio{
        validarTurno(fecha, hora);
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
    public List<Turno> buscarTurnoPorFecha(Date fecha){

        return tr.findTurnoByFecha(fecha);
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
