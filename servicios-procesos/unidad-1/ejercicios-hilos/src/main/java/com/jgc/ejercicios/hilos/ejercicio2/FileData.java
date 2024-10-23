/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio2;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class FileData {
  private final String nombreArchivo;
  private final String palabraBuscada;
  private int numOcurrencias;
  private final Map<Integer, String> lineasConPalabra;
  
  public FileData (String nombreArchivo, String palabraBuscada) {
    this.nombreArchivo = nombreArchivo;
    this.palabraBuscada = palabraBuscada;
    this.numOcurrencias = 0;
    this.lineasConPalabra = new HashMap<>();
  }
  
  public void incrementarOcurrencias () {
    this.numOcurrencias++;
  }
  
  public void agregarLinea (int numLinea, String contenidoLinea) {
    this.lineasConPalabra.put(numLinea, palabraBuscada);
  }

  public String getNombreArchivo() {
    return nombreArchivo;
  }

  public String getPalabraBuscada() {
    return palabraBuscada;
  }

  public int getNumOcurrencias() {
    return numOcurrencias;
  }

  public Map<Integer, String> getLineasConPalabra() {
    return lineasConPalabra;
  }

}
