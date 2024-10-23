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
public class SimuladorBiblioteca {
  public static void main (String[] args) {
    try {
      Biblioteca biblioteca = new Biblioteca();
      biblioteca.agregarLibro(new Libro("El Quijote"));
      biblioteca.agregarLibro(new Libro("Cien años de soledad"));
      biblioteca.agregarLibro(new Libro("1984"));
      
      List<Lector> lectores = new ArrayList<>();
      for (int i=0; i<=5; i++) {
        lectores.add(new Lector("Lector " + i, biblioteca));
      }
      
      for (Lector lector : lectores) {
        lector.start();
      }
      
      for (Lector lector : lectores) {
        lector.interrupt();
      }
      
      for (Lector lector : lectores) {
        lector.join();
      }
      
      System.out.println(" > Simulacion terminada.");
      
    } catch (InterruptedException ex) {
      Logger.getLogger(SimuladorBiblioteca.class.getName()).log(Level.SEVERE, null, ex);
    }    
  }
}
