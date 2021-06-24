package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.repository.MedicoRepository;
import com.proyecto.Egg.service.MedicoService;
import org.dom4j.rule.Mode;
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
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService ms;


    @GetMapping
    public ModelAndView listaMedicos(){

        ModelAndView mav = new ModelAndView("listaMedico");
        List<Medico> listaMedicos = ms.listadoMedicos();
        mav.addObject("titulo","Listado de médicos");
        mav.addObject("listaMedicos", listaMedicos);
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearMedico(){

        ModelAndView mav = new ModelAndView("crearMedico");
        mav.addObject("medico",new Medico());
        mav.addObject("titulo","Crear Medico");
        mav.addObject("action","guardar");
        return mav;

    }
    @PostMapping("/guardar")
    public RedirectView guardar(@Param("matricula") Long matricula, @Param("nombre") String nombre, @Param("apellido") String apellido){
        ms.crearMedico(matricula,nombre,apellido);

    return new RedirectView("/medico");
    }

    @PostMapping("/eliminar/{matricula}")
    public RedirectView eliminar (@PathVariable Long matricula){
        ms.eliminar(matricula);

        return new RedirectView("/medico");
    }

}