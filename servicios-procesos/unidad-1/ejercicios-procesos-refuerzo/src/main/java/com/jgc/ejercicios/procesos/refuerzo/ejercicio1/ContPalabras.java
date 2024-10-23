/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class ContPalabras {
  public static void main (String[] args) {
    if (args.length != 3) {
      System.out.println(" > Introduce exactamente 3 parametros. ficheroInput | palabra | ficheroOutput");
      System.exit(1);
    }
    
    String fileInput = args[0];
    String palabraBuscar = args[1];
    String fileOutput = args[2];
    
    int contPalabra = contarPalabra(fileInput, palabraBuscar);
    printResultados(fileOutput, contPalabra);
  }
  
  private static int contarPalabra (String inputFileInput, String inputPalabraBuscar) {
    int contador = 0;
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(inputFileInput))) {
      String linea;
      while((linea = bReader.readLine()) != null) {
        String[] listaPalabras = linea.toLowerCase().split(" ");
        for (String testPalabra : listaPalabras) {
          if (inputPalabraBuscar.equalsIgnoreCase(testPalabra)) {
            contador++;
          }
        }
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ContPalabras.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ContPalabras.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return contador;
  }
  
  private static void printResultados (String inputFileOutput, int inputContador) {
    try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(inputFileOutput))) {
      bWriter.write(String.valueOf(inputContador));
    } catch (IOException ex) {
      Logger.getLogger(ContPalabras.class.getName()).log(Level.SEVERE, null, ex);
    }
  } 
}
