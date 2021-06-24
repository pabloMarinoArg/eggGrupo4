package com.proyecto.Egg.service;

import com.proyecto.Egg.entity.Roles;
import com.proyecto.Egg.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    @Autowired
    private RolRepository rr;

    @Transactional
    public void crearRol(String nombre){
        Roles rol = new Roles();
        rol.setNombre(nombre);
        rr.save(rol);
    }

    @Transactional
    public void eliminar(String id){
        rr.deleteById(id);
    }

    @Transactional
    public Roles buscarRolPorId(String id){
        Optional<Roles> rolOptional = rr.findById(id);
        return rolOptional.orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Roles> listadoRoles(){

        return rr.findAll();

    }

    @Transactional
    public Roles buscarRolPorNombre(String nombre){
        return rr.findByNombre(nombre);
    }


}
