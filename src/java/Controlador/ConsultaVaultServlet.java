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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.RegistroPassword;
import modelo.VaultDAO;

@WebServlet(name = "ConsultaVaultServlet", urlPatterns = { "/consultaVault" })
public class ConsultaVaultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String rutaTxt = getServletContext().getRealPath("/WEB-INF/vault.txt");
        List<RegistroPassword> listaAuditorias = VaultDAO.leerRegistros(rutaTxt);
        request.setAttribute("listaAuditorias", listaAuditorias);
        request.getRequestDispatcher("vaultHistorico.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
