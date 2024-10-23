/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio3;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Saludar {

  private boolean profesorSaludo = false;
  
  public synchronized void saludarProfe (String nombreAlumno) {
    while (!profesorSaludo) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    System.out.println(nombreAlumno + " -> Buenos dias Profesor");
  }
  
  public synchronized void responderSaludo () {
    System.out.println("Profesor --> Buenos dias");
    profesorSaludo = true;
    notifyAll();
  }
}
