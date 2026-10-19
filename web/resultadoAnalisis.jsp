
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <title>Resultado de Análisis</title>
    <link rel="stylesheet" href="PerfilDeVista.css">
</head>
<body>
        <main role="main">
            <article>
                <div>
                    <h1> Resultado de Análisis </h1>
                    <section>
                        <h5> Nivel de fortaleza</h5>
                        <div class="progressBarCointener"> 
                        <div class="progressBar NF" style="width: ${nivelFortaleza}%;">${nivelFortaleza}%</div>
                        </div>
                    </section>
                    <section>
                        <h5>Tiempo estimado de rotura</h5>
                        <div> 
                            <p><strong>${tiempoRotura}</strong></p>
                        </div>
                    </section>
                    <section>
                        <h5>Recomendaciones</h5>
                        <div>
                            <ul>
                                ${recomendaciones}
                            </ul>
                        </div> 
                    </section>
                    <section>
                        <form action="guardaVault" method="post">
                            <input type="hidden" name="claveAuditada" value="${param.password}">
                            <input type="submit" value="Guardar en Vault"/>
                        </form>
                        <br>
                        <a href="index.jsp"><button type="button">Volver a intentar</button></a>
                    </section>
                </div>
            </article>
        </main>
    </body>
</html>
