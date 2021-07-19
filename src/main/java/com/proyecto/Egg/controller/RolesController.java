package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Roles;
import com.proyecto.Egg.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/rol")
public class RolesController {

    @Autowired
    private RolesService rs;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView listarRoles(){

        ModelAndView mav = new ModelAndView("listadoRoles");
        List<Roles> listaRoles = rs.listadoRoles();
        mav.addObject("titulo","Listado de roles");
        mav.addObject("listaRoles",listaRoles);
        return mav;


    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/crear")
    public ModelAndView crear(){

        ModelAndView mav = new ModelAndView("crearRol");
        mav.addObject("titulo","Crear Rol");
        mav.addObject("rol",new Roles());
        mav.addObject("action","guardar");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificar/{id}")
    public ModelAndView modificarRol(@PathVariable String id){

        ModelAndView mav = new ModelAndView("crearRol");
        mav.addObject("titulo","Modificar Rol");
        mav.addObject("rol",rs.buscarRolPorId(id));
        mav.addObject("action","editar");
        return mav;

    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("nombre") String nombre){
        rs.crearRol(nombre);

        return new RedirectView("/rol");
    }
    @PreAuthorize("hasRole('ADMIN')")
     @PostMapping("/editar")
    public RedirectView editar(@RequestParam("id") String id,@RequestParam("nombre") String nombre){
        rs.modificarRol(id,nombre);

        return new RedirectView("/rol");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar (@PathVariable String id) {
        rs.eliminar(id);
        return new RedirectView("/rol");
    }


}
