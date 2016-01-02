/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author ireene
 */
public class NewClass {

    public static void main(String[] args) {
        /*Primero creamos una persona y la asociamos con dos libros*/
        Sesion sesion1 = new Sesion();
        sesion1.setActivo(false);

        Sesion sesion2 = new Sesion();
        sesion2.setActivo(true);

        Usuario persona1 = new Usuario();
        persona1.setNombre("Persona 1");

        persona1.addSesion(sesion1);
        persona1.addSesion(sesion2);
        sesion1.setUsuario(persona1);
        sesion2.setUsuario(persona1);

    }
}
