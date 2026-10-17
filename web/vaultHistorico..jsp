<%@page import="java.util.List" %>
    <%@page import="modelo.RegistroPassword" %>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html lang="es">

            <head>
                <meta charset="utf-8">
                <title>Listado de Contraseñas</title>
                <link rel="stylesheet" href="PerfilDeVista.css">
            </head>

            <body>
                <main role="main">
                    <article>
                        <div>
                            <h1>Histórico del Vault </h1>
                            <p>Total de claves guardadas en esta sesión: <strong>${sessionScope.contadorClaves != null ?
                                    sessionScope.contadorClaves : 0}</strong></p>

                            <table>
                                <thead>
                                    <tr>
                                        <th>Contraseña</th>
                                        <th>Nivel de Fortaleza</th>
                                        <th>Fecha de Auditoría</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% List<RegistroPassword> lista = (List<RegistroPassword>)
                                            request.getAttribute("listaAuditorias");

                                            if (lista != null && !lista.isEmpty()) {

                                            for (RegistroPassword registro : lista) {
                                            %>
                                            <tr>
                                                <td>
                                                    <%= registro.getClave() %>
                                                </td>
                                                <td>
                                                    <%= registro.getNivelFortaleza() %>
                                                </td>
                                                <td>
                                                    <%= registro.getFechaRegistro() %>
                                                </td>
                                            </tr>
                                            <% } } else { %>
                                                <tr>
                                                    <td colspan="3" style="text-align: center;">El vault está vacío
                                                    </td>
                                                </tr>
                                                <% } %>
                                </tbody>
                            </table>
                            <br>
                            <a href="index.jsp"><button type="button">Volver a inicio</button></a>
                        </div>
                    </article>
                </main>
            </body>

            </html>