/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class ManejoCompilacion {
  private static final String OUTPUT_DIR = "output";
  private static final Random RANDOM = new Random();
  
  public ManejoCompilacion () {
    createOutputDirectory();
  }
  
  private void createOutputDirectory() {
    File outputDir = new File(OUTPUT_DIR);
    if (!outputDir.exists()) {
      outputDir.mkdir();
    }
  }
  
  public ResultadosCompilacion compileFile (String sourceFile) {
    System.out.println("Compiling " + sourceFile + "...");
    int compilationTime = RANDOM.nextInt(2000) + 1000;
    
    try {
      Thread.sleep(compilationTime);
    } catch (InterruptedException ex) {
      Logger.getLogger(ManejoCompilacion.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    String objectFile = OUTPUT_DIR + File.separator + sourceFile.replace(".java", ".o");
    int fileSize = RANDOM.nextInt(10000) + 1000;
    
    try (FileOutputStream fileOS = new FileOutputStream(objectFile)) {
      fileOS.write(new byte[fileSize]);
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ManejoCompilacion.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ManejoCompilacion.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    System.out.println("Compiled " + sourceFile);
    return new ResultadosCompilacion(sourceFile, objectFile, compilationTime, fileSize);
  }
  
  public long link(List<ResultadosCompilacion> results) {
    int linkingTime = RANDOM.nextInt(2000) + 1000; // 1-3 seconds
    try {
      Thread.sleep(linkingTime);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return linkingTime;
  }
  
  public void printSummary(List<ResultadosCompilacion> results, long compilationTime, long linkingTime) {
    System.out.println("\nCompilation Summary:");
    System.out.println("---------------------");
    for (ResultadosCompilacion result : results) {
        System.out.printf("%-20s | Time: %4d ms | Size: %5d bytes\n",
                result.getSourceFile(), result.getCompilationTime(), result.getFileSize());
    }
    System.out.println("---------------------");
    System.out.printf("Total Compilation Time: %d ms\n", compilationTime);
    System.out.printf("Linking Time: %d ms\n", linkingTime);
    System.out.printf("Total Time: %d ms\n", compilationTime + linkingTime);
  }
}
