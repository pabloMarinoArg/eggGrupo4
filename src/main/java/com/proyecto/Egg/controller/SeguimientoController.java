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
   @GetMapping("/modificar/{id}")
    public ModelAndView modificarSeguimiento(@PathVariable String id){
        ModelAndView mav = new ModelAndView("crearSeguimiento");
        mav.addObject("seguimiento",ss.buscarSeguimientoPorId(id));
        mav.addObject("titulo","Crear Seguimiento");
        mav.addObject("action","editar");
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
    public RedirectView guardar(@RequestParam("comentario")String comentario, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fecha,@RequestParam("medicoId") Long matricula){

        ss.crearSeguimiento(comentario, fecha, matricula);

        return new RedirectView("/seguimiento");
    }
    @PostMapping("/editar")
    public RedirectView guardar(@RequestParam("id")String id,@RequestParam("comentario")String comentario, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date fecha,@RequestParam("medicoId") Long matricula){

        ss.modificarSeguimiento(id,comentario, fecha, matricula);

        return new RedirectView("/seguimiento");
    }
	
	

    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar (@PathVariable String id){
        ss.eliminar(id);

        return new RedirectView("/seguimiento");
    }



}
