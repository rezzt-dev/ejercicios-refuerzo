/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class WordFileReader implements Callable<FileData> {
  private final String nombreArchivo;
  private final String palabraBuscada;
  
  public WordFileReader (String nombreArchivo, String palabraBuscada) {
    this.nombreArchivo = nombreArchivo;
    this.palabraBuscada = palabraBuscada;
  }
  
  @Override
  public FileData call() throws Exception {
    FileData fileData = new FileData(nombreArchivo, palabraBuscada);
    int numLinea = 0;
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(nombreArchivo))) {
      String linea;
      
      while((linea = bReader.readLine()) != null) {
        numLinea++;
        
        String[] palabras = linea.split(" ");
        
        for (String palabra : palabras) {
          if (palabra.equalsIgnoreCase(palabraBuscada)) {
            fileData.incrementarOcurrencias();
            fileData.agregarLinea(numLinea, linea);
          }
        }
      }
    }
    
    return fileData;
  }
}
