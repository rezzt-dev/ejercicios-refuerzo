/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Biblioteca {
  private List<Libro> libros = new ArrayList<>();
  
  public synchronized void agregarLibro (Libro inputLibro) {
    libros.add(inputLibro);
    notifyAll();
  }
  
  public synchronized Libro prestarLibro () throws InterruptedException {
    while (true) {
      for (Libro libro : libros) {
        if (libro.isDisponible()) {
          libro.setDisponible(false);
          return libro;
        }
      }

      System.out.println(Thread.currentThread().getName() + " esta esperando un libro.");
      wait();
    }
  }
  
  public synchronized void devolverLibro (Libro inputLibro) {
    inputLibro.setDisponible(true);
    notifyAll();
  }
}
