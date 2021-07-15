package com.proyecto.Egg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

   /* public void enviarCorreo(String to, String asunto, String cuerpo) throws MessagingException {
        //String[] para = new String[]{to};
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(asunto);
        helper.setText(cuerpo);
        sender.send(message);
    }*/

    public void enviarCorreoAsincrono(String to, String asunto, String cuerpo){
        new Thread(()-> {
           try {
               MimeMessage message = sender.createMimeMessage();
               MimeMessageHelper helper = new MimeMessageHelper(message);
               helper.setFrom(from);
               helper.setTo(to);
               helper.setSubject(asunto);
               helper.setText(cuerpo);
               sender.send(message);
           }catch(MessagingException e){
               e.printStackTrace();
               System.out.println("Error al enviar correo");

           }

        }).start();




    }
}
