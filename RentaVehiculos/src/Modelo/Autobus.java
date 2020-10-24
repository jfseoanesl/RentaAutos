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
public class Autobus extends Vehiculo {
    private double pKm, nKmRenta, nKmDevuelve;

    public Autobus() {
        this(0,null,0);
    }
    
    public Autobus(double pkm, String p, double kmActual){
        super(p);
        this.pKm=pkm;
        this.nKmDevuelve=kmActual;
        this.nKmRenta=kmActual;
    }

    public double getpKm() {
        return pKm;
    }

    public void setpKm(double pKm) {
        this.pKm = pKm;
    }

    public double getnKmRenta() {
        return nKmRenta;
    }

    public void setnKmRenta(double nKmRenta) {
        this.nKmRenta = nKmRenta;
    }

    public double getnKmDevuelve() {
        return nKmDevuelve;
    }

    public void setnKmDevuelve(double nKmDevuelve) {
        this.nKmDevuelve = nKmDevuelve;
    }
    
    @Override
    public double calcularImporte(){
        return (this.nKmDevuelve - this.nKmRenta) * this.pKm;
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n" + 
               "Vehiculo: Autobus \n" + 
               "Precio x Km: " + this.pKm + "\n" + 
               "Kilometraje: " + this.nKmDevuelve;
    }    
}
