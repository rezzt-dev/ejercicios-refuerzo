/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.hilos.ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class PrincipalProcess {
  public static void main(String[] args) {
    boolean programaTermina = false;
    
    while (programaTermina == false) {
      if (args.length != 2) {
        System.out.println(" > Numero de argumentos invalido!");
        programaTermina = true;
      }

      String directory = args[0];
      String palabraBuscada = args[1];

      File dir = new File(directory);
      if (!dir.isDirectory()) {
        System.out.println(" > El repositorio no es un directorio!");
        programaTermina = true;
      }
      
      List<Future<FileData>> resultados = new ArrayList<>();
      ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
      
      File[] archivos = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".txt"));
      if (archivos != null) {
        for (File archivo : archivos) {
          Callable<FileData> tarea = new WordFileReader(archivo.getPath(), palabraBuscada);
          resultados.add(executor.submit(tarea));
        }
      }
      
      executor.shutdown();
      
      try {
        executor.awaitTermination(1, TimeUnit.HOURS);
        escribirResultados(resultados);
      } catch (InterruptedException ex) {
        Logger.getLogger(PrincipalProcess.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  private static void escribirResultados (List<Future<FileData>> resultados) {
    try (BufferedWriter bWriter = new BufferedWriter(new FileWriter("resultados_busqueda.txt"))) {
      for (Future<FileData> futuro : resultados) {
        FileData datos;
        try {
          datos = futuro.get();
          bWriter.write(datos.getNumOcurrencias() + " ocurrencias en el fichero " + datos.getNombreArchivo() + "\n");
          
        } catch (InterruptedException | ExecutionException ex) {
          Logger.getLogger(PrincipalProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(PrincipalProcess.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(PrincipalProcess.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
