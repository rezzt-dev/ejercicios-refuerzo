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
public class Tarea {
  private int id;
  private int duracionMs;
  
  public Tarea (int inputId, int inputDuracionMs) {
    this.id = inputId;
    this.duracionMs = inputDuracionMs;
  }

  public int getId() {
    return id;
  }

  public int getDuracionMs() {
    return duracionMs;
  }

  public void setDuracionMs(int duracionMs) {
    this.duracionMs = duracionMs;
  }
  
  
}
