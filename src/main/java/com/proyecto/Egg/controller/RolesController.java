package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Roles;
import com.proyecto.Egg.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/rol")
public class RolesController {

    @Autowired
    private RolesService rs;

    @GetMapping
    public ModelAndView listarRoles(){

        ModelAndView mav = new ModelAndView("listadoRoles");
        List<Roles> listaRoles = rs.listadoRoles();
        mav.addObject("titulo","Listado de roles");
        mav.addObject("listaRoles",listaRoles);
        return mav;


    }

    @GetMapping("/crear")
    public ModelAndView crear(){

        ModelAndView mav = new ModelAndView("crearRol");
        mav.addObject("titulo","Crear Rol");
        mav.addObject("rol",new Roles());
        mav.addObject("action","guardar");
        return mav;

    }

    @PostMapping("/guardar")
    public RedirectView guardar(@Param("nombre") String nombre){
        rs.crearRol(nombre);

        return new RedirectView("/rol");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar (@PathVariable String id) {
        rs.eliminar(id);
        return new RedirectView("/rol");
    }


}
