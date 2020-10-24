/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Autobus;
import Modelo.Compañia;
import Modelo.Tractor;
import Modelo.Vehiculo;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jairo
 */
public class VistaConsola {
    private Compañia company;
   

    public VistaConsola() {
    }
       
    public void vistaMenuPpal(){
        int opc;
        do{
            System.out.println("\n----- MENU PRINCIPAL ------");
            System.out.println("1. Crear Compañia");
            System.out.println("2. Adicionar nuevo vehiculo");
            System.out.println("3. Rentar Vehiculo");
            System.out.println("4. Devolver Vehiculo");
            System.out.println("0. Salir");
            System.out.println("---------------------------");
            opc = Entrada.leerInt("Seleccione: ");
                        
            switch(opc){
                case 0: System.out.println("Aplicativo cerrado con exito");break;
                case 1: this.vistaCrearCompañia();break;
                case 2: this.vistaAdicionarVehiculo();break;
                case 3: this.vistaRentarVehiculo();break;
                case 4: this.vistaDevolverVehiculo();break;
                default: System.out.println("!! Opcion no disponible, seleccione nuevamente ¡¡");
            }
           
        }while(opc!=0);   
    }
    public int vistaVehiculosDisponibles(){
        System.out.println("");
        Vehiculo[] disponible = this.company.vehiculosDisponibles();
        int i=0;
        System.out.println("VEHICULOS DISPONIBLE PARA RENTAR:");
        System.out.println("-----------------------------------------------");
        while(i<disponible.length && disponible[i]!=null){
            System.out.printf("%d.\n",i+1);
            System.out.println(disponible[i]);
            System.out.println("");
            i++;
        }
        
        System.out.printf("!!Total %d Vehiculos disponibles para rentar ¡¡\n", i);
        System.out.println("-----------------------------------------------");
        return i;
    }
    
    public Vehiculo vistaBuscarVehiculoRenta(){
        String placa = Entrada.leerString("Placa: ");
        Vehiculo v = this.company.findVehiculo(placa);
        return v;
    }
    
    public Date vistaLeerFecha(String titulo){
       System.out.println(titulo);
       Calendar cal = Calendar.getInstance();
       int dia = Entrada.leerInt("  Dia[dd]: ");
       cal.set(Calendar.DAY_OF_MONTH, dia);
       int mes = Entrada.leerInt("  Mes[mm]: ");
       cal.set(Calendar.MONTH, mes);
       int año = Entrada.leerInt("  Año[aaaa]: ");
       cal.set(Calendar.YEAR, año);
       return cal.getTime();
    }
    
    public void vistaRentarVehiculo(){
        System.out.println(" --- 3. RENTAR VEHICULO ---");
        if(this.company!=null){
            int continuar;
            do{
                int tDisponible = this.vistaVehiculosDisponibles();
                
                if(tDisponible>0){
                    Vehiculo v=this.vistaBuscarVehiculoRenta();
                    if(v!=null){
                        if(!this.company.verificaAlquiler(v)){
                            System.out.println("\nDatos adicionales: ");
                            System.out.println("----------------------------");
                            System.out.println(v);
                            Date fecha=null;
                            if(v instanceof Tractor){
                               System.out.println("\nDatos de renta: ");
                               System.out.println("----------------------------");
                               fecha = this.vistaLeerFecha("Fecha de renta: ");
                            }
                            this.company.rentarVehiculo(v, fecha);
                            System.out.println("\n!!EL vehiculo rentado con exito ¡¡\n");
                        }
                        else{
                            System.out.println("\n!!EL vehiculo no se encuentra disponible para alquiler ¡¡\n");
                        }
                    }
                    else{
                        System.out.println("\n!!EL vehiculo no se encuentra registrado ¡¡\n");
                    }
                    continuar=Entrada.leerInt("¿0->para rentar otro ?  ");
                }
                else{
                    continuar=1;
                }
            }while(continuar==0);    
        }
        else{
            System.out.println("\n!! No hay compañia creada, Opcion 1 Menu Ppal para crearla \n");
        }
    }
    public void vistaDevolverVehiculo(){
        System.out.println(" --- 4. DEVOLUCION VEHICULO ---");
        if(this.company!=null){
            int continuar;
            do{
                Vehiculo v = this.vistaBuscarVehiculoRenta();
                if(v!=null){
                    if(this.company.verificaAlquiler(v)){
                        System.out.println("\nDatos adicionales: ");
                        System.out.println("----------------------------");
                        System.out.println(v);
                        double km=0;
                        Date fecha=null;
                        System.out.println("\nDatos de devolucion: ");
                        System.out.println("-----------------------------");
                        if(v instanceof Autobus){
                            km = Entrada.leerDouble("Kilometraje actual: ");
                        }
                        else{
                           fecha = this.vistaLeerFecha("Fecha de devolucion: ");
                        }
                        double importe = this.company.devolverVehiculo(v, km, fecha);
                        System.out.println("Importe a pagar: " + importe + "\n");

                    }
                    else{
                        System.out.println("\n!! El vehiculo no se encuentra rentado \n");        
                    }
                }else{
                    System.out.println("\n!! El vehiculo no se encuentra registrado en la compañia \n");
                }
                continuar=Entrada.leerInt("¿0->para devolver otro ?  ");
                
            }while(continuar==0);    
        }
        else{
            System.out.println("\n!! No hay compañia creada, Opcion 1 Menu Ppal para crearla \n");
        }
    
    }
    public Vehiculo vistaCrearVehiculo(){
        System.out.println("Datos de Vehiculo: \n");
        Vehiculo v;
        String placa = Entrada.leerString("No Placa: ");
        int tipo;
        do{
            tipo = Entrada.leerInt("Tipo de vehiculo[0->Autobus , 1->Tractor]:");
        }while(tipo!=0 && tipo!=1);
        if(tipo==0){
            double kmActual= Entrada.leerDouble("Kilometraje: ");
            double tarifaKm = Entrada.leerDouble("Tarifa x Km: ");
            v = new Autobus(tarifaKm, placa, kmActual);
        }
        else{
            double tarifaDia = Entrada.leerDouble("Tarifa x Dia: ");
            v= new Tractor(tarifaDia, placa);
        }
        System.out.println("");
        return v;
    }
    
    public void vistaAdicionarVehiculo(){
       System.out.println(" --- 2. REGISTRO DE VEHICULOS ---");
        if(this.company!=null){
            int continuar;
            do{
                if(this.company.verificarCupoBD()){
                    Vehiculo v=this.vistaCrearVehiculo();
                    this.company.addVehiculo(v);
                    System.out.println("Registro exitoso...\n");
                    continuar=Entrada.leerInt("¿0->para registrar otro ?  ");
                }
                else{
                    continuar=1;
                    System.out.println("\n!! No se puede registrar nuevo vehiculo,  no hay espacio disponible\n");
                }
            }while(continuar==0);    
        }
        else{
            System.out.println("\n!! No hay compañia creada, Opcion 1 Menu Ppal para crearla \n");
        }
    
    }
    
   
    public void vistaCrearCompañia(){
        System.out.println(" \n--- 1. CREACION DE COMPAÑIA ---");
        if(this.company==null){
            String nombre = Entrada.leerLinea("Nombre: ");
            int cupos= Entrada.leerInt("No vehiculos permitidos: ");
            this.company = new Compañia(nombre, cupos);
            System.out.println("\n!! Registro correcto ¡¡ \n");
        }
        else{
            System.out.println("\n!! Ya la compañia se encuentra creada, sus datos son: !! ");
            System.out.println(this.company);
            System.out.println("-------------------------------------------------------\n");
        }
    }
    
    
    
}
