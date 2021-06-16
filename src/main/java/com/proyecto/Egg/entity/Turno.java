
package com.proyecto.Egg.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Turno implements Serializable {
    
    @Id
    //Falta el generador automatico de Id
    private long id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private int hora;
   
    
    public Turno() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }
    
    
    
    
    
    
    
}


    

