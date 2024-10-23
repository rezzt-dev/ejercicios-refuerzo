/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio2;

/**
 * 
 * @author JGC by Juan Garcia Cazallas
 * version 1.0
 * created on 23 oct 2024
 */
public class ResultadosCompilacion {
  private String sourceFile;
  private String objectFile;
  private int compilationTime;
  private int fileSize;

  public ResultadosCompilacion(String sourceFile, String objectFile, int compilationTime, int fileSize) {
    this.sourceFile = sourceFile;
    this.objectFile = objectFile;
    this.compilationTime = compilationTime;
    this.fileSize = fileSize;
  }

  public String getSourceFile() {
    return sourceFile;
  }

  public void setSourceFile(String sourceFile) {
    this.sourceFile = sourceFile;
  }

  public String getObjectFile() {
    return objectFile;
  }

  public void setObjectFile(String objectFile) {
    this.objectFile = objectFile;
  }

  public int getCompilationTime() {
    return compilationTime;
  }

  public void setCompilationTime(int compilationTime) {
    this.compilationTime = compilationTime;
  }

  public int getFileSize() {
    return fileSize;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }
  
  
}
