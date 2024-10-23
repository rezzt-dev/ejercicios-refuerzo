/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio1;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class ContadorLinea extends Thread {
  private String linea;
  private int contPalabras;
  
  public ContadorLinea (String inputLinea) {
    this.linea = inputLinea;
  }
  
  @Override
  public void run () {
    String[] palabras = linea.split(" ");
    contPalabras = palabras.length;
  }
  
  public int getContadorPalabras() {
    return this.contPalabras;
  } 
}
