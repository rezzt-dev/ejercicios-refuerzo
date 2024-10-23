/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Consumidor extends Thread {
  private String nombreCons;
  private final Buffer bufferTareas;
  private boolean running = true;
  
  public Consumidor (String inputNombre, Buffer inputBuffer) {
    this.nombreCons = inputNombre;
    this.bufferTareas = inputBuffer;
  }
  
  private void procesarTarea(Tarea tarea) {
    System.out.println("  - Procesando: " + tarea);
    
    try {
      Thread.sleep(tarea.getDuracionMs());
      System.out.println("  - Completado: " + tarea);
    } catch (InterruptedException ex) {
      Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  @Override
  public void run () {
    while (running && !Thread.currentThread().isInterrupted()) {
      try {
        Tarea tarea = bufferTareas.retirarTarea();
        procesarTarea(tarea);
        
      } catch (InterruptedException ex) {
        Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void detener () {
    this.running = false;
    interrupt();
  }
}
