/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.jgc.ejercicios.procesos.refuerzo.ejercicio2;

import java.io.BufferedReader;
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
public class ManejoProyectos {
  private String projectFile;
  
  public ManejoProyectos (String inputProjectFile) {
    this.projectFile = inputProjectFile;
  }
  
  public List<String> readProjectFile () {
    List<String> sourceFiles = new ArrayList<>();
    
    try (BufferedReader bReader = new BufferedReader(new FileReader(this.projectFile))) {
      String line;
      
      while((line = bReader.readLine()) != null) {
        sourceFiles.add(line.trim());
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(ManejoProyectos.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
      Logger.getLogger(ManejoProyectos.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return sourceFiles;
  }
}
