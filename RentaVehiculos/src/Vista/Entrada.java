/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.util.Scanner;

/**
 *
 * @author jairo
 */
public class Entrada {
    private static Scanner entrada = new Scanner(System.in);
    
    public static int leerInt(String dato){
        System.out.print(dato);
        return entrada.nextInt();
    }
    
    public static double leerDouble(String dato){
        System.out.print(dato);
        return entrada.nextDouble();
    }
    
    public static String leerLinea(String dato){
        System.out.print(dato);
        entrada = new Scanner(System.in);
        return entrada.nextLine();
    }
    
    public static String leerString(String dato){
        System.out.print(dato);
        return entrada.next();
    }
    
}
