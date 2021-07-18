package com.proyecto.Egg.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class PrincipalController {

    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public ModelAndView index(){
        return new ModelAndView("index");

    }

    @GetMapping("/inicio")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView inicio(){
        return new ModelAndView("inicio");

    }


    @GetMapping("/salir")
    public ModelAndView salir() {

        return new ModelAndView("index");

    }
}
