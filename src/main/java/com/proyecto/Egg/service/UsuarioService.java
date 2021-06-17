
package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
      
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    public void crearUsuario(String usuario, String password, String rol, String mail){
     
        Usuario usuarioo = new Usuario();
        usuarioo.setUsername(usuario);
        usuarioo.setMail(mail);
        usuarioo.setPassword(password);
        usuarioo.setRol(rol);
        
        usuarioRepository.save(usuarioo);
        
    }
    
    
}
