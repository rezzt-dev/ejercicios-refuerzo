/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class CompiladorDistribuido {
  public static void main (String[] args) {
    ManejoProyectos projectManager = new ManejoProyectos("project.txt");
    List<String> sourceFiles = projectManager.readProjectFile();
    
    if (sourceFiles.isEmpty()) {
      System.out.println("No source files found in the project file.");
      return;
    }
    
    ManejoCompilacion compilationManager = new ManejoCompilacion();
    List<ResultadosCompilacion> resultados = new ArrayList<>();
    
    long startTime = System.currentTimeMillis();
    
    for (String sourceFile : sourceFiles) {
      ResultadosCompilacion resultado = compilationManager.compileFile(sourceFile);
      resultados.add(resultado);
    }
    
    long compilationTime = System.currentTimeMillis();
    System.out.println("\nLinking object files...");
    
    long linkingTime = compilationManager.link(resultados);
    compilationManager.printSummary(resultados, compilationTime, linkingTime);
  }
}
