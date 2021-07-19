package com.proyecto.Egg.controller;


import com.proyecto.Egg.entity.Turno;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.service.PacienteService;
import com.proyecto.Egg.service.TurnoService;
import com.proyecto.Egg.service.UsuarioService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView listarTurno(){
        ModelAndView mav = new ModelAndView("listarTurno");
        List<Turno> listaTurno = ts.listadoTurnos();
        mav.addObject("titulo","Listado de turnos");
        mav.addObject("listaTurno",listaTurno);
        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/crear")
    public ModelAndView crearTurno(){
        ModelAndView mav = new ModelAndView("crearTurno");
        mav.addObject("turno",new Turno());
        mav.addObject("paciente",ps.listadoPacientes());

        mav.addObject("titulo","Crear Turno");
        mav.addObject("action","guardar");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
     @GetMapping("/modificar/{id}")
    public ModelAndView modificarTurno(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("crearTurno");
        mav.addObject("turno",ts.buscarTurnoPorId(id));
        mav.addObject("paciente",ps.listadoPacientes());
        mav.addObject("titulo","Editar Turno");
        mav.addObject("action","editar");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam @DateTimeFormat(pattern = "HH:mm") Date hora, @RequestParam("paciente") Long paciente) throws ErrorServicio{


            ts.crearTurno(fecha, hora, paciente);



        return new RedirectView("/turno");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/editar")
    public RedirectView editar(@RequestParam("id") Long id,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,@RequestParam @DateTimeFormat(pattern = "HH:mm") Date hora,@RequestParam("paciente") Long paciente){

        ts.modificarTurno(id,fecha, hora, paciente);

        return new RedirectView("/turno");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("eliminar/{id}")
    public RedirectView eliminar(@PathVariable Long id){
        ts.eliminar(id);

        return new RedirectView("/turno");
    }
}
