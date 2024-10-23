/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class LectorFicheros {
  private static String crearArchivoTemporal () throws IOException {
    File temp = File.createTempFile("busqueda_", ".tmp");
    return temp.getPath();
  }
  
  private static Process crearProcesoHijo (String inputFileInput, String inputPalabraBuscar, String inputFileOutput) {
    Process proceso = null;
    
    try {
      List<String> command = new ArrayList<>();
      String clase = "com.jgc.ejercicios.procesos.refuerzo.ejercicio1.ContPalabras";
      String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
      
      command.add("java");
      command.add("-cp");
      command.add(classPath);
      command.add(clase);
      command.add(inputFileInput);
      command.add(inputPalabraBuscar);
      command.add(inputFileOutput);
      
      ProcessBuilder pb = new ProcessBuilder(command);
      proceso = pb.start();
      
    } catch (IOException ex) {
      Logger.getLogger(LectorFicheros.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return proceso;
  }
  
  private static int leerResultados (String inputArchivoTemporal) {
    int contPalabra = 0;
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(inputArchivoTemporal))) {
      String linea = bReader.readLine();
      return linea != null ? Integer.parseInt(linea.trim()) : 0;
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LectorFicheros.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(LectorFicheros.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return contPalabra;
  }
  
  public static void lanzarLector (String inputFile, String inputPalabraBuscar) {
    File inputDirectory = new File(inputFile);
    String palabraBuscar = inputPalabraBuscar;
    
    
    try {
      File[] archivos = inputDirectory.listFiles((d, name) -> name.endsWith(".txt"));
      
      if (archivos == null || archivos.length == 0) {
        System.out.println(" > No se encuentran archivos txt en el directorio");
        return;
      }
      
      List<Process> procesos = new ArrayList<>();
      List<String> archivosTemporales = new ArrayList<>();
      
      for (File archivo : archivos) {
        String archivoTemporal = crearArchivoTemporal();
        Process proceso = crearProcesoHijo(archivo.getPath(), palabraBuscar, archivoTemporal);
        procesos.add(proceso);
      }
      
      for (Process proceso : procesos) {
        proceso.waitFor();
      }
      
      int totalOcurrencias = 0;
      for (String archivoTemporal : archivosTemporales) {
        totalOcurrencias += leerResultados(archivoTemporal);
        new File(archivoTemporal).delete();
      }
      
      System.out.println("Total de ocurrencias de '" + palabraBuscar + "': " + totalOcurrencias);
      
    } catch (IOException ex) {
      Logger.getLogger(LectorFicheros.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) {
      Logger.getLogger(LectorFicheros.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void main (String[] args) {
    String dirInput = "resourcesEJ1/";
    String palabra = "hola";
    lanzarLector(dirInput, palabra);
  }
}
