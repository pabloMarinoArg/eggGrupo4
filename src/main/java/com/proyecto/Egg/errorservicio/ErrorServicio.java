package com.proyecto.Egg.errorservicio;

public class ErrorServicio extends Exception{

    public ErrorServicio(String msj) {
        super(msj);

    }

    public ErrorServicio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
