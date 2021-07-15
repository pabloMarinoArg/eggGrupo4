
package com.proyecto.Egg.service;


import com.proyecto.Egg.entity.Usuario;
import com.proyecto.Egg.repository.RolRepository;
import com.proyecto.Egg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
      
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rp;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;
    
    @Transactional
    public void crearUsuario(String usuario, String password, String rol, String mail) throws MessagingException {
        String pass= password;
        Usuario usuarioo = new Usuario();
        usuarioo.setUsername(usuario);
        usuarioo.setMail(mail);
        usuarioo.setPassword(encoder.encode(password));
        if(rol!=null) {
        usuarioo.setRol(rp.findById(rol).orElse(null));
        }else {
        	usuarioo.setRol(rp.findByNombre("PARIENTE"));
        }
        String cuerpo = "Muchas gracias por registrarte:\nUsuario:"+usuario+"\nPassword: "+pass+"\nPor cualquier inconveniente por favor comunicarse con administración.";
        emailService.enviarCorreoAsincrono(mail,"Bienvenido a Casa Hogar ATARDECER",cuerpo);
        usuarioRepository.save(usuarioo);

        
    }

    @Transactional(readOnly=true)
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();


    }

    @Transactional
    public void modificarUsuario(String id, String username, String password, String mail){
        usuarioRepository.modificar(id,username,encoder.encode(password),mail);

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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.buscarPorNombreDeUsuario(username);
        if(usuario == null){

            throw new UsernameNotFoundException("No hay ningun usuario: "+username+" registrado");

        }

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        session.setAttribute("usuariosession",usuario);

        GrantedAuthority rol = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().getNombre());

        User user = new User(usuario.getUsername(), usuario.getPassword(), Collections.singletonList(rol));

        return user;
    }
}
