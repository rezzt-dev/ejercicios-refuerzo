/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.refuerzo.ejercicio1;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class Libro {
  private String titulo;
  private boolean disponible; //false => prestado
  
  public Libro (String inputTitulo) {
    this.titulo = inputTitulo;
    this.disponible = true;
  }

  public String getTitulo() {
    return titulo;
  }

  public boolean isDisponible() {
    return disponible;
  }

  public void setDisponible(boolean isDisponible) {
    this.disponible = isDisponible;
  }
  
  
}
