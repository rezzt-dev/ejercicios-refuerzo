/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.ejercicio3;

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
public class ContarVocal {
  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("Uso: java ContarVocal <inputFile> <vocal> <outputFile>");
      System.exit(1);
    }

    String inputFile = args[0];
    char vocalBuscar = args[1].toLowerCase().charAt(0);
    String outputFile = args[2];

    int contVocal = countVocal(inputFile, vocalBuscar);
    printResultado(outputFile, contVocal);
  }
  
  private static int countVocal (String inputInputFile, char inputVocalBuscar) {
    int contador = 0;
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(inputInputFile))) {
      String line;
      
      while((line = bReader.readLine()) != null) {
        contador += line.toLowerCase().chars().filter(ch -> ch == inputVocalBuscar).count();
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ContarVocal.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ContarVocal.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return contador;
  }
  
  public static void printResultado (String inputOuputFile, int inputContador) {
    try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(inputOuputFile))) {
      bWriter.write(String.valueOf(inputContador));
    } catch (IOException ex) {
      Logger.getLogger(ContarVocal.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
