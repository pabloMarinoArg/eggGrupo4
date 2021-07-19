package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.service.MedicoService;
import com.proyecto.Egg.service.PacienteService;
import com.proyecto.Egg.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seguimiento")
public class SeguimientoController {

    @Autowired
    private SeguimientoService ss;
    @Autowired
    private MedicoService ms;
    @Autowired
    private PacienteService ps;
    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','PARIENTE')")
    @GetMapping
    public ModelAndView listarSeguimiento (){
        ModelAndView mav = new ModelAndView("listadoSeguimiento");
        List<Seguimiento> listaSegumiento = ss.listarSeguimientos();
        mav.addObject("listaSeguimientos",listaSegumiento);
        mav.addObject("titulo","Listado de seguimientos");
        return mav;
    }
    @PreAuthorize("hasAnyRole('ADMIN','MEDICO')")
    @GetMapping("/crear")
    public ModelAndView crearSeguimiento (){
        ModelAndView mav = new ModelAndView("crearSeguimiento");
        mav.addObject("seguimiento",new Seguimiento());
        mav.addObject("listaMedico", ms.listadoMedicos());
        mav.addObject("listaPaciente",ps.listadoPacientes());
        mav.addObject("titulo","Crear Seguimiento");
        mav.addObject("action","guardar");
        return mav;

    }
    @PreAuthorize("hasAnyRole('ADMIN','MEDICO','PACIENTE')")
    @GetMapping("/listarSeguimiento/{paciente}")
    public ModelAndView listarSeguimientoPaciente(@PathVariable Long paciente){
        ModelAndView mav = new ModelAndView("listadoSeguimiento");
        mav.addObject("listaSeguimientos",ss.buscarSeguimientoPorPaciente(paciente));
        mav.addObject("titulo","Seguimiento ");
        //mav.addObject("action","");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
   @GetMapping("/modificar/{id}")
    public ModelAndView modificarSeguimiento(@PathVariable String id){
        ModelAndView mav = new ModelAndView("crearSeguimiento");
        mav.addObject("seguimiento",ss.buscarSeguimientoPorId(id));
        mav.addObject("titulo","Crear Seguimiento");
        mav.addObject("action","editar");
        return mav;

    }

    @PreAuthorize("hasAnyRole('ADMIN','MEDICO')")
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("comentario")String comentario, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fecha,@RequestParam("medico") Long medico,@RequestParam("paciente") Long paciente){

        ss.crearSeguimiento(comentario, fecha, medico, paciente);

        return new RedirectView("/seguimiento");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/editar")
    public RedirectView guardar(@RequestParam("id")String id,@RequestParam("comentario")String comentario, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fecha){

        ss.modificarSeguimiento(id,comentario, fecha);

        return new RedirectView("/seguimiento");
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar (@PathVariable String id){
        ss.eliminar(id);

        return new RedirectView("/seguimiento");
    }



}
