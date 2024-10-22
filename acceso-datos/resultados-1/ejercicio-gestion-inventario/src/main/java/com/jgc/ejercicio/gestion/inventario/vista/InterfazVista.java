/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.vista;

import com.jgc.ejercicio.gestion.inventario.controlador.ControlFormat;
import com.jgc.ejercicio.gestion.inventario.controlador.ControlProduct;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public interface InterfazVista {

  static final String CREAR_PRODUCTO = "crea un producto al final del fichero";
  static final String LEER_PRODUCTO_ID = "lee un producto pasandole la id";
  static final String LEER_PRODUCTOS = "leer los productos de un fichero";
  static final String MODIFICAR_PRODUCTO_ID = "modifica el precio y stock de un producto";
  static final String BORRAR_PRODUCTO = "realiza borrado logico de un producto dada su id";
  static final String CONVERTIR_BINARY_XML = "convierte un fichero binario a xml";
  static final String CONVERTIR_XML_BINARY = "convierte un fichero xml a binario";
  
  public void setControladorProducto(ControlProduct inputControladorProducto);
  public void setControladorFormato(ControlFormat inputControladorFormato);

  public void arranca();

  public void operacionExitosa();

  public void escribeResultado(String cadenaTexto);

  public String getRuta();
  
  public long getIdentificador();
  public String getNombre();
  public double getPrecio();
  public int getCantStock();

}
