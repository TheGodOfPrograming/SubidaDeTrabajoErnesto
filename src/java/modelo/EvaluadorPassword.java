/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author diurno
 */
public class EvaluadorPassword {

    // Hago una tabla que contenga todas las tiempo de rotura
    private static final String[][] TIEMPOS_ROTURA = {
            /* 4 caracteres  */{ "Instantaneo", "Instantaneo", "Instantaneo", "Instantaneo", "Instantaneo" },
            /* 5 caracteres */ { "Instantaneo", "Instantaneo", "Instantaneo", "Instantaneo", "Instantaneo" },
            /* 6 caracteres */ { "Instantaneo", "Instantaneo", "Instantaneo", "1 segundo", "5 segundos" },
            /* 7 caracteres */ { "Instantaneo", "Instantaneo", "25 segundos", "1 minuto", "6 minutos" },
            /* 8 caracteres */ { "Instantaneo", "5 segundos", "22 minutos", "1 hora", "8 horas" },
            /* 9 caracteres */ { "Instantaneo", "2 minutos", "19 horas", "3 dias", "3 semanas" },
            /* 10 caracteres */{ "Instantaneo", "58 minutos", "1 mes", "7 meses", "5 años" },
            /* 11 caracteres */{ "2 segundos", "1 dia", "5 años", "41 años", "400 años" },
            /* 12 caracteres */{ "25 segundos", "3 semanas", "300 años", "2k años", "34k años" },
            /* 13 caracteres */{ "4 minutos", "1 año", "16k años", "100k años", "2m años" },
            /* 14 caracteres */{ "41 minutos", "51 años", "800k años", "9m años", "200m años" },
            /* 15 caracteres */{ "6 horas", "1k años", "43m años", "600m años", "15 bn años" },
            /* 16 caracteres */{ "2 dias", "34k años", "2bn años", "37bn años", "1tn años" },
            /* 17 caracteres */{ "4 semanas", "800k años", "100bn años", "2tn años", "93tn años" },
            /* 18 caracteres */{ "9 meses", "23m años", "6tn años", "100 tn años", "7qd años" }
    };

    // Mediante la verificación compruebo que contiene para situar la columna
    private static int determinarColumnaComplejidad(String contraseña) {
        boolean tieneNum = contraseña.matches(".*\\d.*");
        boolean tieneMinus = contraseña.matches(".*[a-z].*");
        boolean tieneMayus = contraseña.matches(".*[A-Z].*");
        boolean tieneSimbolo = contraseña.matches(".*[^a-zA-Z0-9].*");

        if (tieneNum && tieneMayus && tieneMinus && tieneSimbolo)
            return 4;
        if (tieneNum && tieneMayus && tieneMinus)
            return 3;
        if (tieneMayus && (tieneMinus || tieneNum))
            return 2;
        if (tieneMinus && !tieneMayus && !tieneNum && !tieneSimbolo)
            return 1;
        if (tieneNum && !tieneMayus && !tieneMinus && !tieneSimbolo)
            return 0;
        return 2;
    }

    // Aqui comprobamos el tiempo de rotura mediante la longitud cuanto más larga
    // sea mas abajo de la fila se situa
    public static String estimarTiempoRotura(String contraseña) {
        if (contraseña == null || contraseña.isEmpty())
            return "No hay contraseña";

        int longitud = contraseña.length();
        if (longitud < 4)
            return "Instantaneo";
        int indiceFila = Math.min(longitud - 4, 14);
        int indiceColumna = determinarColumnaComplejidad(contraseña);

        return TIEMPOS_ROTURA[indiceFila][indiceColumna];
    }

    public static int puntuacionContraseña(String contraseña) {
        String tiempo = estimarTiempoRotura(contraseña);
        if(tiempo.contains("No hay contraseña"))return 0;
        if (tiempo.equals("Instantaneo"))
            return 5;
        if (tiempo.contains("segundos") || tiempo.contains("segundo"))
            return 15;
        if (tiempo.contains("minuto") || tiempo.contains("minutos"))
            return 30;
        if (tiempo.contains("hora") || tiempo.contains("horas"))
            return 45;
        if (tiempo.contains("dia") || tiempo.contains("dias"))
            return 60;
        if (tiempo.contains("semana") || tiempo.contains("semanas") || tiempo.contains("month")
                || tiempo.contains("meses"))
            return 75;
        if (tiempo.contains("año") || tiempo.contains("años"))
            return 100; 
        return 50;
    }

    public static String generarRecomendaciones(String contraseña) {
        int columna = determinarColumnaComplejidad(contraseña);
        int longitud = contraseña.length();
        String rec = " ";

        if (longitud < 8) {
        rec += "<li> 1. Deberias aummentar el número de caracteres, no es complicado hackearlo </li>";
    } else if (longitud < 12) {
        rec += "<li> 1. Aunque la longitud no esta mal deberias aumentarla a 12 caracteres al menos </li> ";
    } else {
        rec += "<li> 1. La cantidad de carateres es excelente. </li>";
    }
        if (columna < 2) {
        rec += "<li> 2. Solo tienes números y letras simple. Combina mayúsculas y minúsculas </li>";
    } else if (columna < 4) {
        rec +="<li> 2. Decente combinación de letras, pero te faltan símbolos </li>";
    } else {
        rec += "<li> 2. Excelente combinación de letras y símbolos. </li>";
    }

        if (longitud >= 12 && columna == 4) {
         rec += "<li> 3. Tu contraseña es muy buena, recuerda no usarla para numeros sitios y que no lo escribas en cualquier parte. </li>";
    } else {
         rec += "<li> 3. Mezcla palabras sin sentido con símbolos para llegar al nivel máximo de seguridad sin olvidarla. </li>";
        return rec;
    }

}