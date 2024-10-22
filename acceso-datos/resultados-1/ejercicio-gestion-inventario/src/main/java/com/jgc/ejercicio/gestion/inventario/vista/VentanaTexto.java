/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.vista;

import com.jgc.ejercicio.gestion.inventario.controlador.ControlFormat;
import com.jgc.ejercicio.gestion.inventario.controlador.ControlProduct;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class VentanaTexto implements InterfazVista {

  private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private ControlProduct controladorProducts;
  private ControlFormat controladorFormat;
  private String ruta;
  
  public VentanaTexto() {
    super();
  }
  
  private String leerString() {
    try {
      return in.readLine();
    } catch (IOException e) {
      System.out.println("ERROR! Introduce correctamente la cadena.");
      return null;
    }
  }
  
  private int leerOpcion() {
    try {
      String opcion = in.readLine();
      return Integer.parseInt(opcion);
    } catch (IOException | NumberFormatException e) {
      opcionInvalida();
      return 0;
    }
  }
  
  private void mostrarMenu () {
    System.out.println(" > Introduce la operacion a realizar:");
    System.out.println("  1. Crear Producto.");
    System.out.println("  2. Leer Productos.");
    System.out.println("  3. Leer Producto Concreto.");
    System.out.println("  4. Modificar el Precio y Stock de un Producto.");
    System.out.println("  5. Borrar Producto.");
    System.out.println("  6. Exportar Productos a XML.");
    System.out.println("  7. Importar Productos a Binary.");
    
    System.out.println("\n 0. Salir.");
  }
  
  private void opcionInvalida () {
    System.out.println("!La opcion seleccionada no es valida.");
  }
  
  private void procesarNuevaOperacion() {
    mostrarMenu();
    int opcion;
    
    System.out.println(" > Introduce una opcion:");
    opcion = leerOpcion();
    
    switch (opcion) {
      case 0 -> {
        System.out.println("\n");
        System.exit(0);
      }
      
      case 1 -> {
        this.controladorProducts.actionPerformed(new ActionEvent(this, opcion, CREAR_PRODUCTO));
      }
      case 2 -> {
        this.controladorProducts.actionPerformed(new ActionEvent(this, opcion, LEER_PRODUCTOS));
      }
      case 3 -> {
        this.controladorProducts.actionPerformed(new ActionEvent(this, opcion, LEER_PRODUCTO_ID));
      }
      
      case 4 -> {
        this.controladorProducts.actionPerformed(new ActionEvent(this, opcion, MODIFICAR_PRODUCTO_ID));
      }
      case 5 -> {
        this.controladorProducts.actionPerformed(new ActionEvent(this, opcion, BORRAR_PRODUCTO));
      }
      
      case 6 -> {
        this.controladorFormat.actionPerformed(new ActionEvent(this, opcion, CONVERTIR_BINARY_XML));
      }
      
      case 7 -> {
        this.controladorFormat.actionPerformed(new ActionEvent(this, opcion, CONVERTIR_XML_BINARY));
      } 
    }
    
    procesarNuevaOperacion();
  }
  
  @Override
  public void arranca() {
    procesarNuevaOperacion();
  }
  
  @Override
  public void operacionExitosa() {
    System.out.println("Operacion realizada con Exito!\n\n");
  }
  
  @Override
  public void escribeResultado(String inputCadena) {
    System.out.println(inputCadena);
  }
  
  @Override
  public void setControladorProducto(ControlProduct inputControladorProducto) {
    this.controladorProducts = inputControladorProducto;
  }
  
  @Override
  public void setControladorFormato(ControlFormat inputControladorFormato) {
    this.controladorFormat = inputControladorFormato;
  }
  
  @Override
  public String getRuta() {
    System.out.print(" > Introduce la ruta: ");
    return leerString();
  }

  @Override
  public long getIdentificador() {
    System.out.print(" > Introduce el ID: ");
    
    try {
      String tempId = in.readLine();
      return Long.parseLong(tempId);
    } catch (IOException | NumberFormatException e) {
      opcionInvalida();
      return 0;
    }
  }

  @Override
  public String getNombre() {
    System.out.print(" > Introduce el Nombre: ");
    return leerString();
  }

  @Override
  public double getPrecio() {
    System.out.print(" > Introduce el Precio: ");
    
    try {
      String tempPrecio = in.readLine();
      return Double.parseDouble(tempPrecio);
    } catch (IOException | NumberFormatException e) {
      opcionInvalida();
      return 0.0;
    }
  }

  @Override
  public int getCantStock() {
    System.out.println(" > Introduce el Stock: ");
    return leerOpcion();
  }
}
