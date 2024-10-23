/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Lector extends Thread {
  private String nombreLector;
  private Biblioteca biblioteca;
  private Random random = new Random();
  
  public Lector (String inputNombre, Biblioteca inputBiblioteca) {
    this.nombreLector = inputNombre;
    this.biblioteca = inputBiblioteca;
  }
  
  @Override
  public void run () {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        try {
          Libro libro = biblioteca.prestarLibro();
          System.out.println(nombreLector + " esta leyendo " + libro.getTitulo());
        
          Thread.sleep(random.nextInt(3000) + 2000);
          biblioteca.devolverLibro(libro);
          System.out.println(nombreLector + " ha devuelto " + libro.getTitulo());
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
          break;
         }
      }
    } finally {
      System.out.println(nombreLector + " ha terminado de leer.");
    }
  }
}
