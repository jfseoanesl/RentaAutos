/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author jairo
 */
public class Tractor extends Vehiculo{
    private double pDia;
    private Date fRenta, fDevolucion;

    public Tractor() {
        super();
    }

    public Tractor(double pDia, String p) {
        super(p);
        this.pDia = pDia;
        this.fRenta = new Date();
        this.fDevolucion = new Date();
    }

    public double getpDia() {
        return pDia;
    }

    public void setpDia(double pDia) {
        this.pDia = pDia;
    }

    public Date getfRenta() {
        return fRenta;
    }

    public void setfRenta(Date fRenta) {
        this.fRenta = fRenta;
    }

    public Date getfDevolucion() {
        return fDevolucion;
    }

    public void setfDevolucion(Date fDevolucion) {
        this.fDevolucion = fDevolucion;
    }
    
    @Override
    public double calcularImporte(){
       double df = this.fDevolucion.getTime() - this.fRenta.getTime();
       int d = (int) df / (1000 * 60 * 60 * 24);
       return d * this.pDia;
    }
    
    @Override
    public String toString(){
        return super.toString()+"\n" + 
               "Vehiculo: Tractor \n"+
               "Precio x Dia: " + this.pDia;
    }
}
