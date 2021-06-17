/*
Roles -> id, pariente (apoderado)(leer), medico (modificar historia), 
admin (para manejar el sistema)
 */
package com.proyecto.Egg.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

public class RolesEntidad implements Serializable {

    @Entity
    public class Roles implements Serializable {

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        private long id;
        private String nombre;

        @ManyToOne
        private Roles roles ;

        public Roles() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

    }

}
