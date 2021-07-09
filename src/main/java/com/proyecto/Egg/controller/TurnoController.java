package com.proyecto.Egg.controller;

import com.proyecto.Egg.RolEnum;
import com.proyecto.Egg.entity.Turno;
import com.proyecto.Egg.service.PacienteService;
import com.proyecto.Egg.service.TurnoService;
import com.proyecto.Egg.service.UsuarioService;
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
    @Autowired
    private PacienteService ps;
    @Autowired
    private UsuarioService us;

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
        mav.addObject("paciente",ps.listadoPacientes());
        mav.addObject("titulo","Crear Turno");
        mav.addObject("action","guardar");
        return mav;

    }
    
     @GetMapping("/modificar/{id}")
    public ModelAndView crearTurno(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("crearTurno");
        mav.addObject("turno",ts.buscarTurnoPorId(id));
        mav.addObject("paciente",ps.listadoPacientes());
        mav.addObject("titulo","Editar Turno");
        mav.addObject("action","editar");
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
    public RedirectView guardar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,@RequestParam @DateTimeFormat(pattern = "HH:mm") Date hora,@RequestParam("paciente") Long paciente){

        ts.crearTurno(fecha, hora, paciente);

        return new RedirectView("/turno");
    }
    @PostMapping("/editar")
    public RedirectView editar(@RequestParam("id") Long id,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,@RequestParam @DateTimeFormat(pattern = "HH:mm") Date hora,@RequestParam("paciente") Long paciente){

        ts.modificarTurno(id,fecha, hora, paciente);

        return new RedirectView("/turno");
    }
    @PostMapping("eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id){
        ts.eliminar(id);

        return new RedirectView("/turno");
    }
}
