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
public class SimuladorTrafico {
  public static void main (String[] args) {
    for (int i = 1; i <= 10; i++) {
      new Thread(new Vehiculo(i)).start();
    } 
  }
}
