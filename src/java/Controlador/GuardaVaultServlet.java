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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.EvaluadorPassword;
import modelo.RegistroPassword;
import modelo.VaultDAO;

@WebServlet(name = "GuardaVaultServlet", urlPatterns = { "/guardaVault" })
public class GuardaVaultServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clave = request.getParameter("claveAuditada");

        String rutaTxt = getServletContext().getRealPath("/WEB-INF/vault.txt");

        int fortaleza = EvaluadorPassword.puntuacionContraseña(clave);
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        RegistroPassword nuevoRegistro = new RegistroPassword(clave, fortaleza + "%", fecha);
        VaultDAO.guardarRegistro(nuevoRegistro, rutaTxt);
        HttpSession session = request.getSession();
        Integer contador = (Integer) session.getAttribute("contadorClaves");
        if (contador == null) {
            contador = 1;
        } else {
            contador++;
        }
        session.setAttribute("contadorClaves", contador);
        response.sendRedirect("consultaVault");
    }
}
