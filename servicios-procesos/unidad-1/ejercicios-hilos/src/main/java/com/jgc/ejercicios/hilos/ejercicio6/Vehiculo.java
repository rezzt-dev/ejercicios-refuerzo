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
public class Vehiculo extends Thread {
  private int numVehic;
  
  public Vehiculo (int inputNumVehic) {
    this.numVehic = inputNumVehic;
  }
  
  public int getNumVehic () {
    return this.numVehic;
  }
  
  @Override
  public void run () {
    Puente puente = Puente.getInstance();
    String entrada = Math.random() < 0.5 ? "norte" : "sur";
    
    while (!puente.entrar(this, entrada)) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
    }
    
    try {
      Thread.sleep((long) (Math.random() * 3000 + 1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    puente.salir(this);
  }
}
