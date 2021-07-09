
package com.proyecto.Egg.entity;

import java.io.Serializable;
import javax.persistence.Column;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Paciente implements Serializable {
    
    @Id
    @Column(unique=true)
    private Long dni;
    private String nombre;
    private String apellido;
    
    @Temporal(TemporalType.DATE)
    private Date nacimiento;
    
    
    public Paciente() {
    }


    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

   

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }
    
    
    
    
}
