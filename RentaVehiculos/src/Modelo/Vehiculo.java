/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jairo
 */
public class Vehiculo {
    private String placa;
    private boolean estado; // alquilado(true) - Disponible (false)

    public Vehiculo() {
    }

    public Vehiculo(String placa) {
        this.placa = placa;
        this.estado=false;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public double calcularImporte(){
        return 0;
    }
    
    @Override
    public String toString(){
        return "Placa: " + this.placa+"\n"+
               "Alquilado: " + this.estado;
    }
}
