/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.jgc.ejercicios.hilos;

import com.jgc.ejercicios.hilos.ejercicio2.PrincipalProcess;

/**
 *
 * @author JGC by Juan Garcia Cazallas
 */
public class EjerciciosHilos {
  public static void main(String[] args) {
    String fileName = "./directory";
    String palabraBuscar = "block";
    
    String[] inputArgumentos = {fileName, palabraBuscar};
    PrincipalProcess.main(inputArgumentos);
  }
}
