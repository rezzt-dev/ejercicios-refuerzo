/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class testContador {
  public static void main (String[] args) {
    String fileName = "./archivo.txt";
    List<ContadorLinea> hilos = new ArrayList<>();
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {
      String linea;
      
      while ((linea = bReader.readLine()) != null) {
        ContadorLinea hilo = new ContadorLinea(linea);
        hilos.add(hilo);
        hilo.start();
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(testContador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(testContador.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    int totalPalabras = 0;
    
    for (ContadorLinea hilo : hilos) {
      try {
        hilo.join();
        totalPalabras += hilo.getContadorPalabras();
      } catch (InterruptedException e) {
        System.out.println("Hilo interrumpido: " + e.getMessage());
      }
    }
    
    System.out.println(" > Numero total de palabras: " + totalPalabras);
  }
}
