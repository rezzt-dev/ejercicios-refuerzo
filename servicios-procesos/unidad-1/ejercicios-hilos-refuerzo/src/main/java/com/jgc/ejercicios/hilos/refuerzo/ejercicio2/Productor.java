/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio2;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Productor extends Thread {
  private String nombreProd;
  private Buffer bufferProd;
  private int contId = 0;
  private boolean running = true;

  public Productor(String nombreProd, Buffer bufferProd) {
    this.nombreProd = nombreProd;
    this.bufferProd = bufferProd;
  }
  
  private Tarea generateTarea () {
    int id = contId++;
    int duracion = (int) (Math.random() * (1000 - 100 + 1)) + 100;
    return new Tarea(contId++, duracion);
  }
  
  @Override
  public void run () {
    while (running) {
      Tarea tarea = generateTarea();
      System.out.println("Tarea generada: " + tarea);
      
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.out.println("Productor interrumpido");
        break;
      }
    }
  }
  
  public void detener() {
    this.running = false;
    interrupt();
  }
}
