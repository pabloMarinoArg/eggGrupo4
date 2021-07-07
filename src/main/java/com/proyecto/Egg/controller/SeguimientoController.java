package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Seguimiento;
import com.proyecto.Egg.service.SeguimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
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

    @GetMapping
    public ModelAndView listarSeguimiento (){
        ModelAndView mav = new ModelAndView("listadoSeguimiento");
        List<Seguimiento> listaSegumiento = ss.listarSeguimientos();
        mav.addObject("listaSeguimientos",listaSegumiento);
        mav.addObject("titulo","Listado de seguimientos");
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearSeguimiento (){
        ModelAndView mav = new ModelAndView("crearSeguimiento");
        mav.addObject("seguimiento",new Seguimiento());
        mav.addObject("titulo","Crear Seguimiento");
        mav.addObject("action","guardar");
        return mav;

    }
    /*private String id;
	private String comentario;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@OneToOne
	private Usuario usuario;
	@ManyToOne
	private Medico medico;
    * */
    @PostMapping("/guardar")
    public RedirectView guardar(@Param("comentario")String comentario, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fecha,@Param("usuarioId") String usuarioId,@Param("medicoId") Long matricula){

        ss.crearSeguimiento(comentario, fecha, usuarioId, matricula);

        return new RedirectView("/seguimiento");
    }

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar (@PathVariable String id){
        ss.eliminar(id);

        return new RedirectView("/seguimiento");
    }



}
