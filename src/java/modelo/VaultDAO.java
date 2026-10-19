package modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author diurno
 */
public class VaultDAO {

    public static void guardarRegistro(RegistroPassword registro, String rutaFichero) {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(rutaFichero, true), StandardCharsets.UTF_8))) {
            out.println(registro.getClave() + ";" + registro.getNivelFortaleza() + ";" + registro.getFechaRegistro());
        } catch (IOException e) {
             System.out.println("¡Boom! Error al guardar: " + e.getMessage());
            }
    }

    public static List<RegistroPassword> leerRegistros(String rutaFichero) {
        List<RegistroPassword> lista = new ArrayList<>();
        File archivo = new File(rutaFichero);
        if (!archivo.exists())
            return lista;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 3) {
                    lista.add(new RegistroPassword(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el vault: " + e.getMessage());
        }
        return lista;
    }
}
