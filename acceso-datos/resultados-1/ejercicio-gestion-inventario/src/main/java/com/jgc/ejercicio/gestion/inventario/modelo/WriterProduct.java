/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicio.gestion.inventario.modelo;

import com.jgc.ejercicio.gestion.inventario.modelo.objetos.FileProduct;
import com.jgc.ejercicio.gestion.inventario.modelo.objetos.Producto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on Oct 22, 2024
 */
public class WriterProduct extends FileProduct {
  public WriterProduct (String inputPath) {
    super(inputPath);
  }
  
  private void writeString (RandomAccessFile inputFile, String inputString, int inputLength) {
    try {
      StringBuffer buffer = new StringBuffer(inputString);
      buffer.setLength(inputLength);
      inputFile.writeChars(buffer.toString());
    } catch (IOException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public Producto createProduct (long inputId, String inputNombre, double inputPrecio, int inputCantStock) {
    return new Producto(inputId, inputNombre, inputPrecio, inputCantStock);
  }
  
  public void saveProduct (Producto inputProduct) {
    long pos = 0;
    StringBuffer buffer = null;
    RandomAccessFile randomFile = null;
    
    try {
      randomFile = new RandomAccessFile(getPath(), "rw");
      
      if (randomFile.length() != 0) {
        pos = randomFile.length();
      }
      
      randomFile.seek(pos);
      randomFile.writeLong((pos/super.getLongTotal()) + 1);
      
      writeString(randomFile, inputProduct.getNombre(), getCharsName());
      randomFile.writeDouble(inputProduct.getPrecio());
      randomFile.writeInt(inputProduct.getCantStock());
      
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
      } catch (IOException ex) {
        Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  public void modifyProduct (long inputId, double inputNewPrecio, int inputNewStock) {
    RandomAccessFile randomFile = null;
    
    try {
      randomFile = new RandomAccessFile(getPath(), "rw");
      boolean productFound = false;
      
      while (productFound == false) {
        long pos = randomFile.getFilePointer();
        long actualId = randomFile.readLong();
        
        if (actualId == inputId) {
          randomFile.seek(pos + LONG_ID + LONG_NOMBRE);
          randomFile.writeDouble(inputNewPrecio);
          randomFile.writeInt(inputNewStock);
          
          productFound = true;
        }
        
        randomFile.skipBytes(LONG_TOTAL - LONG_ID);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
      } catch (IOException ex) {
        Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }
  }
  
  public void deleteProduct (long inputId) {
    RandomAccessFile randomFile = null;
    
    try {
      randomFile = new RandomAccessFile(getPath(), "rw");
      boolean productFound = false;
      
      while (productFound == false && randomFile.getFilePointer() < randomFile.length()) {
        long pos = randomFile.getFilePointer();
        long actualId = randomFile.readLong();
        
        if (actualId == inputId) {
          randomFile.seek(pos);
          randomFile.writeLong(0);
          productFound = true;
        }
      }
      
      randomFile.skipBytes(LONG_TOTAL - LONG_ID);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      try {
        randomFile.close();
      } catch (IOException ex) {
        Logger.getLogger(WriterProduct.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
