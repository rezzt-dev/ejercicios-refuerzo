/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio6;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Puente {

  private static Puente instance = null;
  private boolean ocupado = false;
  
  private Puente () {}
  
  public static synchronized Puente getInstance() {
    if (instance == null) {
      instance = new Puente();
    }
    return instance;
  }
  
  public synchronized boolean entrar (Vehiculo vehiculo, String entrada) {
    boolean sePuedeEntrar = true;
    
    if (ocupado) {
      System.out.println("Vehiculo " + vehiculo.getNumVehic() + " esperando en la entrada " + entrada);
      sePuedeEntrar = false;
    }
    
    ocupado = true;
    
    System.out.println("Vehículo " + vehiculo.getNumVehic() + " entrando al puente por la entrada " + entrada);
    sePuedeEntrar = true;
    
    return sePuedeEntrar;
  }
  
  public synchronized void salir (Vehiculo vehiculo) {
    System.out.println("Vehículo " + vehiculo.getNumVehic() + " saliendo del puente");
    ocupado = false;
  }

  
}
