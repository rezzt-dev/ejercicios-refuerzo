/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio3;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class UsarAula {
  public static void main (String[] args) {
    Saludar saludo = new Saludar();
    List<Thread> hilos = new ArrayList<>();
    
    for (int i=0; i <= 5; i++) {
      hilos.add(new Aula("Alumno" + i, saludo, false));
    }
    
    Thread profesor = new Aula("Profesor", saludo, true);
    
    for (Thread hilo : hilos) {
      hilo.start();
    }
    
    profesor.start();
    
    try {
      for (Thread hilo : hilos) {
        hilo.join();
      }
      
      profesor.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    System.out.println(" > Todos los hilos ha terminado.");
  }
}
