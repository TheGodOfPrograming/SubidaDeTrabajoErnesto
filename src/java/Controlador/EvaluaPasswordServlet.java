/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author diurno
 */


import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.EvaluadorPassword;

@WebServlet(name = "EvaluaPasswordServlet", urlPatterns = {"/evaluaPassword"}) 
public class EvaluaPasswordServlet extends HttpServlet {
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String clave = request.getParameter("password");
        
        String nombreUsuario = request.getParameter("name");
        if (nombreUsuario != null && !nombreUsuario.trim().isEmpty()) {
            request.getSession().setAttribute("nombreUsuario", nombreUsuario);
        }
        
        int punt = EvaluadorPassword.puntuacionContraseña(clave);
        String tiempoRotura = EvaluadorPassword.estimarTiempoRotura(clave);
        String recomendaciones = EvaluadorPassword.generarRecomendaciones(clave);
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss"));
        
        Cookie cookieAuditoria = new Cookie("ultimaAuditoria", fechaActual);
        cookieAuditoria.setMaxAge(24 * 60 * 60); 
        response.addCookie(cookieAuditoria);
        
        request.setAttribute("nivelFortaleza", punt);
        request.setAttribute("tiempoRotura", tiempoRotura);
        request.setAttribute("recomendaciones", recomendaciones);
        
        request.getRequestDispatcher("resultadoAnalisis.jsp").forward(request, response);
    }
}
