/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio4;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Ascensor {
  private int pisoActual;
  private int numMaxPisos;
  
  public Ascensor (int numMaxPisos) {
    this.numMaxPisos = numMaxPisos;
    pisoActual = 0;
  }
  
  public synchronized void moverAscensor(int pisoDestino) {
    System.out.println(" > Ascensor en piso " + pisoActual + ". Moviendo al piso " + pisoDestino);
    
    while (pisoActual != pisoDestino) {
      try {
        Thread.sleep(1000);
        
        if (pisoActual < pisoDestino) {
          pisoActual++;
        } else pisoActual--;
        
        System.out.println("  - Ascensor en piso " + pisoActual);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    System.out.println(" > Ascensor llego al piso " + pisoDestino);
  }
}
