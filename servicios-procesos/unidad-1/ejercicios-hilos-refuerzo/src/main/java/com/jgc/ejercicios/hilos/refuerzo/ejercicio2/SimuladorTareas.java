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
public class SimuladorTareas {
  public static void main (String[] args) {
    Buffer buffer = new Buffer(10);
    Productor productor = new Productor("Productor", buffer);
    Consumidor consumidor = new Consumidor("Consumidor", buffer);
    
    productor.start();
    consumidor.start();
    
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    productor.detener();
    consumidor.detener();
    
    try {
      productor.join();
      consumidor.join();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    System.out.println(" > Simulador de Tareas Terminado.");

  }
}
