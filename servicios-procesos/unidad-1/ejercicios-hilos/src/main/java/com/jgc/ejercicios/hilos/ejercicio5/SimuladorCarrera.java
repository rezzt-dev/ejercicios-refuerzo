/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class SimuladorCarrera {
  public static void main(String[] args) {
    Testigo testigo = new Testigo();
    Atleta[] atletas = new Atleta[4];
    
    System.out.println("Comienza la carrera de relevos");
    long tiempoInicioCarrera = System.currentTimeMillis();
    
    for (int i = 0; i < 4; i++) {
      atletas[i] = new Atleta(i + 1, testigo);
      atletas[i].start();
    }
    
    for (Atleta atleta : atletas) {
      try {
        atleta.join();
      } catch (InterruptedException ex) {
        Logger.getLogger(SimuladorCarrera.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    long tiempoFinCarrera = System.currentTimeMillis();
    System.out.println("Carrera terminada. Tiempo total: " + (tiempoFinCarrera - tiempoInicioCarrera) / 1000.0 + " segundos");
  }
}
