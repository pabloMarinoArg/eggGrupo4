package com.proyecto.Egg.controller;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErroresController  implements ErrorController {

    @RequestMapping(value="/error", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {

        ModelAndView errorPage = new ModelAndView("error");
        String errormsj="";
        String errormsj2="";
        String errormsj3="";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400:
                errormsj = "El recurso solicitado no existe";
                break;
            case 403:
                errormsj = "No tiene permisos para acceder al recurso";
                break;
            case 401:
                errormsj = "No se encuentra autorizado";
                break;
            case 404:
                errormsj = "Página no encontrada";
                errormsj2= "No sabemos cómo llegaste hasta acá pero abajo tenés";
                errormsj3=" un botón para volver :D";
                break;
            case 500:
                errormsj = "Ocurrió un error interno";
                break;

        }

        errorPage.addObject("codigo",httpErrorCode);
        errorPage.addObject("mensaje", errormsj);
        errorPage.addObject("mensaje2",errormsj2);
        errorPage.addObject("mensaje3",errormsj3);

        return errorPage;
    }









    private int getErrorCode(HttpServletRequest httpRequest) {

        Map mapa = httpRequest.getParameterMap();
        for(Object key : mapa.keySet()) {
            String[] valores = (String[])mapa.get(key);
            for(String valor : valores) {
                System.out.println(key.toString()+": "+valor);

            }


        }

        Enumeration<String> atributos = httpRequest.getAttributeNames();
        while(atributos.hasMoreElements()) {
            String key = atributos.nextElement();
            System.out.println(key + ":" + httpRequest.getAttribute(key));
        }

        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }









    /*@Override
    public String getErrorPath() {

        return "/error";
    }*/




}
