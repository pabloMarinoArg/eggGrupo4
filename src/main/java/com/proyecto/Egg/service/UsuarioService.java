
package com.proyecto.Egg.service;


import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.RolRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    
    
}
