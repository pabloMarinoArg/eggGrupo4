package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Turno;
import com.proyecto.Egg.service.TurnoService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService ts;

    @GetMapping
    public ModelAndView listarTurno(){
        ModelAndView mav = new ModelAndView("listarTurno");
        List<Turno> listaTurno = ts.listadoTurnos();
        mav.addObject("titulo","Listado de turnos");
        mav.addObject("listaTurno",listaTurno);
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearTurno(){
        ModelAndView mav = new ModelAndView("crearTurno");
        mav.addObject("turno",new Turno());
        mav.addObject("titulo","Crear Turno");
        mav.addObject("action","guardar");
        return mav;

    }

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Integer hora;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Paciente pacienteVisitado;*/

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,@Param("hora") Integer hora,@Param("usuarioId") String usuarioId,@Param("pacienteId") Long pacienteId){

        ts.crearTurno(fecha, hora, usuarioId, pacienteId);

        return new RedirectView("/turno");
    }
    @PostMapping("eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id){
        ts.eliminar(id);

        return new RedirectView("/turno");
    }
}
