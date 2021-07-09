
package com.proyecto.Egg.service;


import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.RolRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
      
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rp;
    
    @Transactional
    public void crearUsuario(String usuario, String password, String rol, String mail){
     
        Usuario usuarioo = new Usuario();
        usuarioo.setUsername(usuario);
        usuarioo.setMail(mail);
        usuarioo.setPassword(password);
        if(rol!=null) {
        usuarioo.setRol(rp.findById(rol).orElse(null));
        }else {
        	usuarioo.setRol(rp.findByNombre("PARIENTE"));
        }
        usuarioRepository.save(usuarioo);
        
    }

    @Transactional(readOnly=true)
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();


    }

    @Transactional
    public void modificarUsuario(String id, String username, String password, String mail){
        usuarioRepository.modificar(id,username,password,mail);

    }

    @Transactional
    public Usuario buscarUsuarioPorId(String id){

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        return usuarioOptional.orElse(null);
    }

    @Transactional
    public void eliminar(String id){

        usuarioRepository.deleteById(id);

    }

    
    
}
