
package com.proyecto.Egg.entity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Medico implements Serializable {
    
    @Id
    @Column(unique=true)
    private Long matricula;
    private String nombre;
    private String apellido;
    @OneToMany(mappedBy = "medico")
    private List<Seguimiento> seguimientos;
    @OneToOne(cascade = CascadeType.ALL)
    private Usuario usuario;

    public Medico() {
    }

    /*Esto hace que antes de remover el objeto medico, se haga null en sus componentes hijos
    * De esta manera podemos borrar un elemento "padre" y dejar sus hijos*/
    @PreRemove
    public void nullSeguimiento (){
        seguimientos
                .forEach(seg-> seg.setMedico(null));

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Seguimiento> getSeguimientos() {
        return seguimientos;
    }

    public void setSeguimientos(List<Seguimiento> seguimientos) {
        this.seguimientos = seguimientos;
    }
    
    public Long getMatricula() {
        return matricula;
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

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
}
