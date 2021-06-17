/*
Roles -> id, pariente (apoderado)(leer), medico (modificar historia), 
admin (para manejar el sistema)
 */
package com.proyecto.Egg.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Roles implements Serializable {

	    @Id
	    @GeneratedValue(generator="uuid")
	    @GenericGenerator(name="uuid",strategy = "uuid2")
	    private String id;
	 	private String nombre;
	 	
	 	public Roles() {
		}
	 	
	
		
		public String getId() {
			return id;
		}



		public void setId(String id) {
			this.id = id;
		}



		public String getNombre() {
			return nombre;
		}



		public void setNombre(String nombre) {
			this.nombre = nombre;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Roles other = (Roles) obj;
			if (nombre == null) {
				if (other.nombre != null)
					return false;
			} else if (!nombre.equals(other.nombre))
				return false;
			return true;
		}



		@Override
		public String toString() {
			return nombre;
		}
	 	
    }


