/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo.objetos;

import java.io.File;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class FileProduct {
  private final int LONGITUD_LONG = 8;
  private final int LONGITUD_DOUBLE = 8;
  private final int LONGITUD_INT = 4;
  private final int LONGITUD_CHAR = 2;
  
  private final int CHARS_NOMBRE = 10;
  
  protected final int LONG_ID = LONGITUD_LONG;
  protected final int LONG_NOMBRE = (CHARS_NOMBRE * LONGITUD_CHAR);
  protected final int LONG_PRECIO = LONGITUD_DOUBLE;
  protected final int LONG_CANT_STOCK = LONGITUD_INT;
  
  protected final int LONG_TOTAL = (LONG_ID + LONG_NOMBRE + LONG_PRECIO + LONG_CANT_STOCK);
  
  private File path;
  
  public FileProduct (String inputPath) {
    this.path = new File(inputPath);
  }
  
  public int getCharsName () {
    return CHARS_NOMBRE;
  }
  
  public int getLongName () {
    return LONG_NOMBRE;
  }
  
  public int getLongTotal () {
    return LONG_TOTAL;
  }
  
  public File getPath () {
    return this.path;
  }
  
  public void setPath (String inputPath) {
    this.path = new File(inputPath);
  }
}
