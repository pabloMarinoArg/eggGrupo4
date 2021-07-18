package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Paciente;
import com.proyecto.Egg.entity.Pariente;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.service.PacienteService;
import com.proyecto.Egg.service.ParienteService;
import com.proyecto.Egg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
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
    public ModelAndView crearPariente(){
        List<Paciente> lista = new ArrayList<>();
        ModelAndView mav = new ModelAndView("crearPariente");
        mav.addObject("pariente", new Pariente());
        mav.addObject("titulo","Ingresar pariente");
        mav.addObject("listaPaciente", pas.listadoPacientes());
        mav.addObject("usuario",new Usuario());
        mav.addObject("lista",lista);
        mav.addObject("action","guardar");
        return mav;
    }
    @GetMapping("/editar/{id}")
    public ModelAndView editarPariente(@PathVariable String id){
        List<Paciente> lista = new ArrayList<>();
        ModelAndView mav = new ModelAndView("crearPariente");
        mav.addObject("pariente", ps.buscarParientePorId(id));
        mav.addObject("titulo","Ingresar pariente");
        mav.addObject("listaPaciente", pas.listadoPacientes());
        mav.addObject("lista",lista);
        mav.addObject("action","modificar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarPariente(@RequestParam("usuario") String usuario, List<Paciente> listaPaciente){

        ps.crearPariente(usuario, listaPaciente);

       return new RedirectView("/pariente");
    }

    @PostMapping("/modificar")
    public RedirectView modificarPariente(@RequestParam String id, @RequestParam List<Paciente> listaPaciente){

        ps.modificarPariente(id,listaPaciente);

        return new RedirectView("/pariente");
    }
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminarPariente(@RequestParam String id){

        ps.eliminarPariente(id);

        return new RedirectView("/pariente");
    }


}
