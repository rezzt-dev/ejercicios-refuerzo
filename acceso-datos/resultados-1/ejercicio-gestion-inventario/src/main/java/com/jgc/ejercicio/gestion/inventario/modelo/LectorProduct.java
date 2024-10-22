/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo;

import com.jgc.ejercicio.gestion.inventario.modelo.objetos.FileProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.objetos.Producto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class LectorProduct extends FileProduct {
  public LectorProduct (String inputPath) {
    super(inputPath);
  }
  
  private String readString (RandomAccessFile inputFile, int inputLength) throws IOException {
    char[] charArray = new char[inputLength];
    
    for (int i=0; i<charArray.length; i++) {
      charArray[i] = inputFile.readChar();
    }
    
    return new String(charArray).trim();
  }
  
  public List<String> readProducts () {
    List<String> listProducts = new ArrayList<>();
    
    try (RandomAccessFile randomFile = new RandomAccessFile(getPath(), "r")) {
      while (randomFile.getFilePointer() < randomFile.length()) {
        long tempId = randomFile.readLong();
        String tempNombre = readString(randomFile, getCharsName());
        double tempPrecio = randomFile.readDouble();
        int tempCantStock = randomFile.readInt();
        
        if (tempId != 0) {
          listProducts.add("ID: " + tempId + ", Nombre: " + tempNombre + ", Precio: " + tempPrecio + ", Stock: " + tempCantStock);
        }
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return listProducts;
  }
  
  public Producto readProduct (long inputId) {
    RandomAccessFile randomFile = null;
    long pos = 0;
    Producto tempProduct = new Producto();
    
    byte[] cadena = new byte[getLongName()];
    
    try {
      randomFile = new RandomAccessFile(getPath(), "r");
      pos = (inputId - 1) * getLongTotal();
      
      if (pos < randomFile.length()) {
        randomFile.seek(pos);
        tempProduct.setId(randomFile.readLong());
        
        randomFile.read(cadena);
        tempProduct.setNombre(new String(cadena));
        
        tempProduct.setPrecio(randomFile.readDouble());
        tempProduct.setCantStock(randomFile.readInt());
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
      } catch (IOException ex) {
        Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    return tempProduct;
  }
  
  public List<Producto> returnListProduct () {
    List<Producto> listProducts = new ArrayList<>();
    RandomAccessFile randomFile = null;
    try {
      randomFile = new RandomAccessFile(getPath(), "r");
      while (randomFile.getFilePointer() < randomFile.length()) {
        Producto tempProduct = new Producto();
        tempProduct.setId(randomFile.readLong());
        tempProduct.setNombre(readString(randomFile, getCharsName()));
        tempProduct.setPrecio(randomFile.readDouble());
        tempProduct.setCantStock(randomFile.readInt());
        
        listProducts.add(tempProduct);
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LectorProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return listProducts;
  }
}
