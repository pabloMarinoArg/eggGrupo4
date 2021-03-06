package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.service.PacienteService;
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
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService ps;


    @GetMapping
    public ModelAndView listadoPacientes(){
        ModelAndView mav = new ModelAndView("listadoPaciente");
        List<Paciente> listaPaciente = ps.listadoPacientes();
        mav.addObject("titulo","listado de pacientes");
        mav.addObject("listaPacientes",listaPaciente);
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/crear")
    public ModelAndView crearPaciente(){
        ModelAndView mav = new ModelAndView("crearPaciente");
        mav.addObject("paciente",new Paciente());
        mav.addObject("titulo","Crear Paciente");
        mav.addObject("action","guardar");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificar/{dni}")
    public ModelAndView modificarPaciente(@PathVariable Long dni){

            ModelAndView mav = new ModelAndView("crearPaciente");
            mav.addObject("paciente", ps.buscarPacientePorId(dni));
            mav.addObject("titulo", "Modificar Paciente");
            mav.addObject("action", "editar");

        return mav;
    
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("dni") Long dni, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date nacimiento) throws ErrorServicio {
        //Long edad = ps.calcularEdad(nacimiento);

        ps.crearPaciente(dni, nombre, apellido,nacimiento);

        return new RedirectView("/paciente");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/editar")
    public RedirectView editar(@RequestParam("dni") Long dni, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date nacimiento){
        ps.modificarPaciente(dni,nombre,apellido,nacimiento);
        return new RedirectView("/paciente");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{dni}")
    public RedirectView eliminar(@PathVariable Long dni){

        ps.eliminar(dni);

        return new RedirectView("/paciente");
    }




}
