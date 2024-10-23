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
public class Testigo {
  private int poseedor = 1;
  
  public synchronized void pasarTestigo (int inputDorsal) {
    while (poseedor != inputDorsal) {
      try {
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    
    poseedor = (inputDorsal % 4) + 1;
    notifyAll();
  }
}
