/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio5;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Atleta extends Thread {
  private final int dorsal;
  private final Testigo testigo;
  
  public Atleta (int inputDorsal, Testigo inputTestigo) {
    this.dorsal = inputDorsal;
    this.testigo = inputTestigo;
  }
  
  @Override
  public void run () {
    testigo.pasarTestigo(dorsal);
    System.out.println("Atleta " + dorsal + "comienza a correr.");
    long tiempoInicio = System.currentTimeMillis();
    
    try {
      Thread.sleep((long) (Math.random() * 2000 + 9000));
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    
    long tiempoFin = System.currentTimeMillis();
    System.out.println("Atleta " + dorsal + " termina de correr. Tiempo: " + (tiempoFin - tiempoInicio) / 1000.0 + " segundos");
    testigo.pasarTestigo((dorsal%4) + 1);
  }
}
