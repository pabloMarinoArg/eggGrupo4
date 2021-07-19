package com.proyecto.Egg.controller;


import com.proyecto.Egg.entity.Usuario;

import com.proyecto.Egg.service.RolesService;
import com.proyecto.Egg.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {


    @Autowired
    private UsuarioService us;
    @Autowired
    private RolesService rs;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ModelAndView listarUsuarios(){

        ModelAndView mav = new ModelAndView("listaUsuario");
        List<Usuario> listaUsuarios = us.listarUsuarios();
        mav.addObject("titulo", "Listado usuarios");
        mav.addObject("listaUsuarios",listaUsuarios);
        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/crear")
    public ModelAndView crearUsuario(){

        ModelAndView mav = new ModelAndView("crearUsuario");
        mav.addObject("listaRol", rs.listadoRoles() );
        mav.addObject("usuario",new Usuario());
        mav.addObject("titulo","Crear Usuario");
        mav.addObject("action","guardar");
        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/modificar/{id}")
    public ModelAndView modificar(@PathVariable String id){
        ModelAndView mav = new ModelAndView("crearUsuario");
        mav.addObject("usuario", us.buscarUsuarioPorId(id));
        mav.addObject("listaRol",rs.listadoRoles());
        mav.addObject("titulo","Modificar Usuario");
        mav.addObject("action","editar");
        return mav;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("mail") String mail,@RequestParam("rol") String rol) throws MessagingException {

        us.crearUsuario(username, password, rol, mail);

        return new RedirectView("/usuario");
    }
    @PreAuthorize("hasRole('ADMIN')")
     @PostMapping("/editar")
    public RedirectView guardar(@RequestParam("id") String id,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("mail") String mail,@RequestParam("rol") String rol){

        us.modificarUsuario(id,username, password, mail,rol);

        return new RedirectView("/usuario");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/eliminar/{id}")
    public RedirectView eliminar(@PathVariable String id){

        us.eliminar(id);

        return new RedirectView("/usuario");
    }







}
