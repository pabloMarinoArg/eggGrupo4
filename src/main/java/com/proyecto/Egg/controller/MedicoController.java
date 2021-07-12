package com.proyecto.Egg.controller;

import com.proyecto.Egg.entity.Medico;
import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.MedicoRepository;
import com.proyecto.Egg.service.MedicoService;
import com.proyecto.Egg.service.RolesService;
import com.proyecto.Egg.service.UsuarioService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoService ms;
    @Autowired
    private UsuarioService us;
    @Autowired
    private RolesService rs;


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
        mav.addObject("usuario",new Usuario());
        mav.addObject("listaRol", rs.listadoRoles() );
       // mav.addObject("listaUsuario",us.listarUsuarios());
        mav.addObject("titulo","Crear Medico");
        mav.addObject("action","guardar");
        return mav;

    }
    
    @GetMapping("/modificar/{matricula}")
     public ModelAndView modificarMedico(@PathVariable Long matricula){

        ModelAndView mav = new ModelAndView("crearMedico");
        mav.addObject("medico",ms.buscarMedicoPorId(matricula));
        mav.addObject("listaUsuario",us.listarUsuarios());
        mav.addObject("titulo","Modificar Medico");
        mav.addObject("action","editar");
        return mav;

    }
    
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("matricula") Long matricula, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido, @RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("mail") String mail,@RequestParam("rol") String rol){
        ms.crearMedico(matricula,nombre,apellido,username,password,mail,rol);

    return new RedirectView("/medico");
    }
    
    @PostMapping("/editar")
    public RedirectView editar(@RequestParam("matricula") Long matricula, @RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido){
        ms.modificarMedico(matricula,nombre,apellido);

    return new RedirectView("/medico");
    }

    @PostMapping("/eliminar/{matricula}")
    public RedirectView eliminar (@PathVariable Long matricula){
        ms.eliminar(matricula);

        return new RedirectView("/medico");
    }

}
