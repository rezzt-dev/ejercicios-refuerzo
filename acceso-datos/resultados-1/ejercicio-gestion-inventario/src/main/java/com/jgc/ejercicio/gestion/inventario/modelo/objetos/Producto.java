/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo.objetos;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class Producto {
  private long id;
  private String nombre;
  private double precio;
  private int cantStock;

  public Producto () {
    
  }
  
  public Producto (long id, String nombre, double precio, int cantStock) {
    this.id = id;
    this.nombre = nombre;
    this.precio = precio;
    this.cantStock = cantStock;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public int getCantStock() {
    return cantStock;
  }

  public void setCantStock(int cantStock) {
    this.cantStock = cantStock;
  }
  
  @Override
  public String toString() {
    return "Producto {ID: " + this.id + " | Nombre: " + this.nombre + " | Precio: " + this.precio + " | Stock: " + this.cantStock + "}";
  }
}
