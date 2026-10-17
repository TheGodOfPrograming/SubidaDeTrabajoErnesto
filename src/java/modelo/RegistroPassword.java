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
public class RegistroPassword {
    private String clave;
    private String nivelFortaleza;
    private String fechaRegistro;

    public RegistroPassword(String clave, String nivelFortaleza, String fechaRegistro) {
        this.clave = clave;
        this.nivelFortaleza = nivelFortaleza;
        this.fechaRegistro = fechaRegistro;
    }

    public String getClave() {
        return clave;
    }

    public String getNivelFortaleza() {
        return nivelFortaleza;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
}
