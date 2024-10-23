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
public class Usuario extends Thread {
  private final int pisoDestino;
  private final Ascensor ascensor;
  private final String nombreUser;

  public Usuario(String nombreUser, int pisoDestino, Ascensor ascensor) {
    this.pisoDestino = pisoDestino;
    this.ascensor = ascensor;
    this.nombreUser = nombreUser;
  }
  
  @Override
  public void run () {
    System.out.println(nombreUser + " quiere ir al piso " + pisoDestino);
    ascensor.moverAscensor(pisoDestino);
    System.out.println(nombreUser + " ha llegado al piso " + pisoDestino);
  }
}
