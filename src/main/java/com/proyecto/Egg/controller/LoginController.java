package com.proyecto.Egg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout ){

        ModelAndView mav = new ModelAndView("login");
        if(error!=null) {
            mav.addObject("error", "Usuario o clave incorrecta");
        }
        if(logout != null) {

            mav.addObject("logout","Adiós, ¡esperamos verte pronto!");

        }
        return mav;


    }


}
