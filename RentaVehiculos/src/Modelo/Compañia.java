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
public class Compañia {
    private String nombre;
    private Vehiculo[] bdVehiculo;
    private int vReg; // vehiculos registrados

    public Compañia() {
        this(null, 0);
    }

    public Compañia(String nombre, int n) {
        this.nombre=nombre;
        this.bdVehiculo = new Vehiculo[n];
        this.vReg = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Vehiculo[] getBdVehiculo() {
        return bdVehiculo;
    }

    public void setBdVehiculo(Vehiculo[] bdVehiculo) {
        this.bdVehiculo = bdVehiculo;
    }

    public int getvReg() {
        return vReg;
    }

    public void setvReg(int vReg) {
        this.vReg = vReg;
    }
    
    public boolean verificarCupoBD(){
        return this.vReg < this.bdVehiculo.length;
    }
    
    public void addVehiculo(Vehiculo v){
        
            this.bdVehiculo[this.vReg]=v;
            this.vReg++;
        
    }
    public Vehiculo findVehiculo(String placa){
        for(int i=0; i< this.vReg;i++){
            Vehiculo v = this.bdVehiculo[i];
            if(placa.equalsIgnoreCase(v.getPlaca())){
                return v;
            }
        }
        return null;
    }
    
    public boolean verificaAlquiler(Vehiculo v){
       return v.isEstado();
    }
    
    public void rentarVehiculo(Vehiculo v, Date fecha){
                   
            if(v instanceof Autobus){
                 Autobus a = (Autobus)v;
                 a.setnKmRenta(a.getnKmDevuelve());
            }
            else{
                Tractor t = (Tractor)v;
                t.setfRenta(fecha);
            }
            v.setEstado(true);
            
    }
        
    
    public double devolverVehiculo(Vehiculo v, double km, Date fecha){
       
            v.setEstado(false);
            if(v instanceof Autobus){
                Autobus a = (Autobus)v;
                a.setnKmDevuelve(km);
                return a.calcularImporte();
                
            }
            else{
               Tractor t = (Tractor)v;
               t.setfDevolucion(fecha);
               return t.calcularImporte();
            }
    }
    
    @Override
    public String toString(){
        return "Nombre Empresa: " + this.nombre + "\n" +
               "Total cupo de vehiculos: " + this.bdVehiculo.length + "\n"+
               "Total registrados: " + this.vReg;
    }
    
    public Vehiculo[] vehiculosDisponibles(){
        Vehiculo[] disponible = new Vehiculo[this.vReg];
        int n=0;
        for(int i=0; i< this.vReg;i++){
            Vehiculo v = this.bdVehiculo[i];
            if(!v.isEstado()){
                disponible[n]=v;
                n++;
            }
        }
        return disponible;
    }
    
    
    
}
