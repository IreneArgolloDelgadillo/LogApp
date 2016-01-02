/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logapp;

import java.sql.Date;

/**
 *
 * @author ireene
 */
public class Salario {

    private int ci_usuario;
    private Date fecha_salario;
    private int descuentos;
    private int bonos;
    private int total;

    public Salario(int ci_usuario, Date fecha_salario, int descunetos, int bonos, int total) {
        this.ci_usuario = ci_usuario;
        this.fecha_salario = fecha_salario;
        this.descuentos = descunetos;
        this.bonos = bonos;
        this.total = total;
    }

    public int getCi_usuario() {
        return ci_usuario;
    }

    public void setCi_usuario(int ci_usuario) {
        this.ci_usuario = ci_usuario;
    }

    public Date getFecha_salario() {
        return fecha_salario;
    }

    public void setFecha_salario(Date fecha_salario) {
        this.fecha_salario = fecha_salario;
    }

    public int getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(int descuentos) {
        this.descuentos = descuentos;
    }

    public int getBonos() {
        return bonos;
    }

    public void setBonos(int bonos) {
        this.bonos = bonos;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object get(int index) {
        switch (index) {
            case (0):
                return ci_usuario;
            case (1):
                return fecha_salario;
            case (2):
                return descuentos;
            case (3):
                return bonos;
            case (4):
                return total;
            default:
                return null;

        }
    }

}
