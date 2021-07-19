package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Pariente;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.errorservicio.ErrorServicio;
import com.proyecto.Egg.service.PacienteService;
import com.proyecto.Egg.service.ParienteService;
import com.proyecto.Egg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import javax.mail.MessagingException;
import java.util.List;

@Controller
@RequestMapping("/pariente")
public class ParienteController {

    @Autowired
    private ParienteService ps;
    @Autowired
    private PacienteService pas;
    @Autowired
    private UsuarioService us;


    @GetMapping
    public ModelAndView listarParientes(){
        ModelAndView mav = new ModelAndView("listadoPariente");
        mav.addObject("listaPariente", ps.listarParientes());
        mav.addObject("titulo","Listado de parientes");

        return mav;

    }

    @GetMapping("/crear")
    public ModelAndView crear(){

        ModelAndView mav = new ModelAndView("crearPariente");
        mav.addObject("pariente", new Pariente());
        mav.addObject("titulo","Ingresar pariente");
        mav.addObject("listaPaciente", pas.listadoPacientes());
        mav.addObject("usuario",new Usuario());
        mav.addObject("action","guardar");
        return mav;
    }
    @GetMapping("/modificar/{id}")
    public ModelAndView modificar(@PathVariable String id){

        ModelAndView mav = new ModelAndView("modificarPariente");
        mav.addObject("pariente", ps.buscarParientePorId(id));
        mav.addObject("titulo","Ingresar pariente");
        mav.addObject("listaPaciente", pas.listadoPacientes());

        mav.addObject("action","editar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,@RequestParam("mail") String mail,@RequestParam("password") String password,@RequestParam("username") String username,@RequestParam("paciente") Long paciente) throws MessagingException {
        //Long edad = ps.calcularEdad(nacimiento);

        ps.crearPariente(username,password,mail, nombre, apellido, paciente);

        return new RedirectView("/pariente");
    }

    @PostMapping("/editar")
    public RedirectView editar(@RequestParam String id,@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long paciente){

        ps.modificarPariente(id,nombre,apellido,paciente);

        return new RedirectView("/pariente");
    }
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminarPariente(@PathVariable String id){

        ps.eliminarPariente(id);

        return new RedirectView("/pariente");
    }


}
