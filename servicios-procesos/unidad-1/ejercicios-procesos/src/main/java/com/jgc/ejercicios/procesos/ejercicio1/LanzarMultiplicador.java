/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.ejercicio1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class LanzarMultiplicador {
  public static void lanzadorMultiplicador (List<String> params, String outputFile) {
    try {
      String clase = "com.jgc.ejercicios.procesos.ejercicio1.Multiplicador";
      String classPath = System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
      
      new File("files").mkdirs();
      
      List<String> command = new ArrayList<>();
      command.add("java");
      command.add("-cp");
      command.add(classPath);
      command.add(clase);
      command.addAll(params);
      
      ProcessBuilder pb = new ProcessBuilder(command);
      File errorFile = new File("files" + File.separator + "error-" + System.currentTimeMillis() + ".log");
      File outputFileObj = new File("files" + File.separator + outputFile);
      
      pb.redirectError(errorFile);
      pb.redirectOutput(outputFileObj);
      
      Process proceso = pb.start();
      boolean completado = proceso.waitFor(10, TimeUnit.SECONDS);
      
      if (completado && proceso.exitValue() == 0){
        System.out.println("Proceso completado exitosamente. Archivo de salida: " + outputFile);
        System.out.println("Resultado guardado en output file: ");
        System.out.println(new String(java.nio.file.Files.readAllBytes(new File("files/" + outputFile).toPath())));
      } else {
        System.out.println("Proceso fallido o tiempo de espera agotado. Código de salida: " + proceso.exitValue());
        System.out.println("Contenido del archivo de error:");
        System.out.println(new String(Files.readAllBytes(errorFile.toPath())));
      }
    } catch (IOException ex) {
      Logger.getLogger(LanzarMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InterruptedException ex) {
      Logger.getLogger(LanzarMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public static void main (String[] args) {
    List<String> params = new ArrayList<>();
    params.add("5");
    params.add("7");
    
    String outputFile = "result.txt";
    lanzadorMultiplicador(params, outputFile);
  }
}
