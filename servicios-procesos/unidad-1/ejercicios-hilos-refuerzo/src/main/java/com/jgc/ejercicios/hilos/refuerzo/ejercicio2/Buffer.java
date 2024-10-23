/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Buffer {
  private List<Tarea> listaTareas = new ArrayList<>(10);
  private final int capacidad;
  
  public Buffer (int inputCapacidad) {
    this.capacidad = inputCapacidad;
  }
  
  public synchronized void agregarTarea (Tarea inputTarea) throws InterruptedException {
    while (listaTareas.size() >= capacidad) {
      wait();
    }
    
    listaTareas.add(inputTarea);
    notifyAll();
  }
  
  public synchronized Tarea retirarTarea () throws InterruptedException {
    while (listaTareas.isEmpty()) {
      wait();
    }
    
    Tarea tareaRetirada = listaTareas.remove(0);
    notifyAll();
    
    return tareaRetirada;
  }
}
