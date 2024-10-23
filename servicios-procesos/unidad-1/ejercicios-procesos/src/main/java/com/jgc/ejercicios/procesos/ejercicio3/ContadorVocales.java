/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class ContadorVocales {
  public static void lanzarContador (String inputFile) {
    String ficheroEntrada = inputFile;
    char[] vocales = {'a', 'e', 'i', 'o', 'u'};
    List<Process> procesos = new ArrayList<>();

    for (char vocal : vocales) {
      try {
        List<String> command = new ArrayList<>();
        String clase = "com.jgc.ejercicios.procesos.ejercicio3.ContarVocal";
        String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";

        command.add("java");
        command.add("-cp");
        command.add(classPath);
        command.add(clase);
        command.add(inputFile);
        command.add(String.valueOf(vocal));
        command.add(vocal + "_resultado.txt");

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();

        Process proceso = pb.start();
        procesos.add(proceso);
      } catch (IOException ex) {
        Logger.getLogger(ContadorVocales.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    for (Process proceso : procesos) {
      try {
        proceso.waitFor();
      } catch (InterruptedException e) {
        System.err.println("Proceso interrumpido: " + e.getMessage());
      }
    }

    int totalVocales = 0;
    for (char vocal : vocales) {
      String nombreResultado = vocal + "_resultado.txt";
      try (BufferedReader reader = new BufferedReader(new FileReader(nombreResultado))) {
        totalVocales += Integer.parseInt(reader.readLine().trim());
      } catch (IOException | NumberFormatException e) {
        System.err.println("ERROR! Al leer el resultado de " + vocal + ": " + e.getMessage());
      }
    }

    System.out.println("Total de vocales en el fichero: " + totalVocales);
  }
  
  public static void main (String[] args) {
    String ficheroEntrada = "./vocales/cuenta.txt";
    lanzarContador(ficheroEntrada);
  }
}
