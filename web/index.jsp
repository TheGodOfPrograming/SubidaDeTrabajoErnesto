<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Buscamos la cookie
    String fechaUltimaAuditoria = "Parece que es tu primera vez";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("ultimaAuditoria".equals(c.getName())) {
                fechaUltimaAuditoria = c.getValue();
                break;
            }
        }
    }

    // Sacamos el nombre del usuario de la sesión  (asumiendo que se guardo como "nombreUsuario")
    String nombreUser = (String) session.getAttribute("nombreUsuario");
    if (nombreUser == null) {
        nombreUser = "Nombre Desconocido"; // Por si no se conoce el nombre 
    }
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <title>FORMULARIO DE AUDITORÍA</title>
        <link rel="stylesheet" href="EstiloDelVista.css">
    </head>
    <body>
        <main role="main">
            <article>
                <div>
                    <h2>¡Bienvenido, <%= nombreUser %>!</h2>
                    <p>Tu última auditoría fue: <strong><%= fechaUltimaAuditoria %></strong></p>
                </div>
                <div>
                    <form action="evaluaPassword" method="post">
                    <table>
                        <caption>Rellena el formulario </caption>
                        <thead>
                            <tr>
                                <th>Datos</th>
                                <th colspan="2">Rellenar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><label for="nombre">Nombre: </label> </td>
                                <td colspan="2"><input name="name" type="text" id="nombre" required/></td>
                            </tr>
                            <tr>
                                <td><label for="usuario">Usuario:  </label> </td>
                                <td colspan="2"><input name="user" type="email" id="usuario" required/></td>
                            </tr>
                            <tr>
                                <td><label for="contraseña">Contraseña:  </label> </td>
                                <td colspan="2"><input name="password" type="password" id="contraseña" required/></td>
                            </tr>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td><input type="button" value="Solicitar clave segura" onclick="generarClaveMagica()" /></td>
                                <td><input type="submit" value="Auditar Clave" /></td>
                                <td><input type="reset" value="Borrar todo" /></td>
                            </tr>
                        </tfoot>
                    </table>
                    </form>
                </div>
            </article>
        </main>
        <footer>
        </footer>
        <script>
            function generarClaveMagica() {

                const letrasMayus = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                const letrasMinus = "abcdefghijklmnopqrstuvwxyz";
                const numeros = "0123456789";
                const simbolos = "!@#$%^&*()_+-=[]{}|;:,.<>?";
                
               
                const todosLosCaracteres = letrasMayus + letrasMinus + numeros + simbolos;
                let claveGenerada = "";
                
               
                for (let i = 0; i < 16; i++) {
                    let aleatorio = Math.floor(Math.random() * todosLosCaracteres.length);
                    claveGenerada += todosLosCaracteres.charAt(aleatorio);
                }
                
               
                let inputPass = document.getElementById("contraseña");
                inputPass.value = claveGenerada;
                
                
                inputPass.type = "text";
            }
        </script>
    </body>
</html>